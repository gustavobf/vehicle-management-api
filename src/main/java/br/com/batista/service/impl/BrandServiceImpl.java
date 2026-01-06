package br.com.batista.service.impl;

import br.com.batista.dto.api.response.*;
import br.com.batista.dto.brand.request.*;
import br.com.batista.dto.brand.response.*;
import br.com.batista.entity.*;
import br.com.batista.exception.*;
import br.com.batista.mapper.*;
import br.com.batista.repository.*;
import br.com.batista.service.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.data.domain.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
public class BrandServiceImpl implements BrandService {

    private final BrandRepository brandRepository;
    private final CarRepository carRepository;

    @Autowired
    public BrandServiceImpl (BrandRepository brandRepository, CarRepository carRepository) {
        this.brandRepository = brandRepository;
        this.carRepository = carRepository;
    }

    public PageResponse<BrandResponse> getAll (Pageable pageable) {
        Page<Brand> page = brandRepository.findAll(pageable);
        return new PageResponse<>(page.map(BrandMapper::mapToBrandResponseDto));
    }

    public Brand getEntityById (final Long id) {
        return brandRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Brand", String.valueOf(id), "id"));
    }

    public BrandResponse getById (final Long id) {
        return BrandMapper.mapToBrandResponseDto(this.getEntityById(id));
    }

    public BrandResponse create (final CreateBrandRequest brandDTO) {
        final Brand brand = BrandMapper.mapToBrand(brandDTO);
        final Brand savedBrand = brandRepository.save(brand);
        return BrandMapper.mapToBrandResponseDto(savedBrand);
    }

    public void delete (final Long id) {
        Brand brand = brandRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Brand", String.valueOf(id), "id"));

        List<Car> cars = carRepository.findByBrand(brand);

        if (!cars.isEmpty()) {
            carRepository.deleteAll(cars);
        }

        brandRepository.deleteById(brand.getId());
    }

    public BrandResponse update (final UpdateBrandRequest brandDTO) {
        final Brand brand = BrandMapper.mapToBrand(brandDTO);
        final Brand savedBrand = brandRepository.save(brand);
        return BrandMapper.mapToBrandResponseDto(savedBrand);
    }

}
