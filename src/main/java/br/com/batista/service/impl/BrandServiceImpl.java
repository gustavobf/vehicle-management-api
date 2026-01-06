package br.com.batista.service.impl;

import br.com.batista.dto.api.response.*;
import br.com.batista.dto.brand.request.*;
import br.com.batista.dto.brand.response.*;
import br.com.batista.entity.*;
import br.com.batista.exception.*;
import br.com.batista.mapper.*;
import br.com.batista.repository.*;
import br.com.batista.service.*;
import jakarta.transaction.*;
import lombok.*;
import org.slf4j.*;
import org.springframework.data.domain.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
@RequiredArgsConstructor
public class BrandServiceImpl implements BrandService {

    private static final Logger logger = LoggerFactory.getLogger(BrandServiceImpl.class);

    private final BrandRepository brandRepository;
    private final CarRepository carRepository;

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

    @Transactional
    public BrandResponse create (final CreateBrandRequest brandDTO) {
        try {
            final Brand brand = BrandMapper.mapToBrand(brandDTO);
            final Brand savedBrand = brandRepository.save(brand);
            logger.info("Created Brand: {}", savedBrand);
            return BrandMapper.mapToBrandResponseDto(savedBrand);
        } catch (Exception e) {
            logger.error("Error creating Brand: {}", e.getMessage());
            throw e;
        }
    }

    @Transactional
    public void delete (final Long id) {
        try {
            Brand brand = brandRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Brand", String.valueOf(id), "id"));

            List<Car> cars = carRepository.findByBrand(brand);

            if (!cars.isEmpty()) {
                carRepository.deleteAll(cars);
            }

            brandRepository.deleteById(brand.getId());
        } catch (Exception e) {
            logger.error("Error deleting Brand with id {}: {}", id, e.getMessage());
            throw e;
        }
    }

    @Transactional
    public BrandResponse update (final UpdateBrandRequest brandDTO) {
        try {
            final Brand brand = BrandMapper.mapToBrand(brandDTO);
            final Brand savedBrand = brandRepository.save(brand);
            return BrandMapper.mapToBrandResponseDto(savedBrand);
        } catch (Exception e) {
            logger.error("Error updating Brand: {}", e.getMessage());
            throw e;
        }
    }

}
