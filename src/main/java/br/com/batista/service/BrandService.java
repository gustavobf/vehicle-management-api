package br.com.batista.service;

import java.util.List;

import br.com.batista.dto.api.response.*;
import br.com.batista.dto.brand.*;
import br.com.batista.dto.brand.request.*;
import br.com.batista.dto.brand.response.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.batista.entity.Brand;
import br.com.batista.entity.Car;
import br.com.batista.exception.ResourceNotFoundException;
import br.com.batista.mapper.BrandMapper;
import br.com.batista.repository.BrandRepository;
import br.com.batista.repository.CarRepository;

@Service
public class BrandService {

	private final BrandRepository brandRepository;
	private final CarRepository carRepository;

	@Autowired
	public BrandService(BrandRepository brandRepository, CarRepository carRepository) {
		this.brandRepository = brandRepository;
		this.carRepository = carRepository;
	}

	public PageResponse<BrandResponse> getAll(Pageable pageable) {
		Page<Brand> page = brandRepository.findAll(pageable);
		return new PageResponse<>(page.map(BrandMapper::mapToBrandResponseDto));
	}

	public BrandResponse getById (final Long id) {
		final Brand brand = brandRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Brand", String.valueOf(id), "id"));
		return BrandMapper.mapToBrandResponseDto(brand);
	}

	public BrandResponse create(final CreateBrandRequest brandDTO) {
		final Brand brand = BrandMapper.mapToBrand(brandDTO);
		final Brand savedBrand = brandRepository.save(brand);
		return BrandMapper.mapToBrandResponseDto(savedBrand);
	}

	public void delete(final Long id) {
		Brand brand = brandRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Brand", String.valueOf(id), "id"));

		List<Car> cars = carRepository.findByBrand(brand);

		if (!cars.isEmpty()) {
			carRepository.deleteAll(cars);
		}

		brandRepository.deleteById(brand.getId());
	}

	public BrandResponse update(final UpdateBrandRequest brandDTO) {
		final Brand brand = BrandMapper.mapToBrand(brandDTO);
		final Brand savedBrand = brandRepository.save(brand);
		return BrandMapper.mapToBrandResponseDto(savedBrand);
	}

}
