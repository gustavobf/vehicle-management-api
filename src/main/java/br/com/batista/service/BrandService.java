package br.com.batista.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.batista.dto.BrandDTO;
import br.com.batista.entity.Brand;
import br.com.batista.entity.Car;
import br.com.batista.exception.ResourceNotFoundException;
import br.com.batista.mapper.BrandMapper;
import br.com.batista.repository.BrandRepository;
import br.com.batista.repository.CarRepository;

@Service
public class BrandService {

	private BrandRepository brandRepository;
	private CarRepository carRepository;

	@Autowired
	public BrandService(BrandRepository brandRepository, CarRepository carRepository) {
		this.brandRepository = brandRepository;
		this.carRepository = carRepository;
	}

	public Page<BrandDTO> getAll(Pageable pageable) {
		return brandRepository.findAll(pageable).map(entity -> BrandMapper.mapToBrandDto(entity, new BrandDTO()));
	}

	public BrandDTO getById(final Long id) {
		final Brand brand = brandRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Brand", String.valueOf(id), "id"));
		final BrandDTO brandDTO = BrandMapper.mapToBrandDto(brand, new BrandDTO());
		return brandDTO;
	}

	public BrandDTO create(final BrandDTO brandDTO) {
		final Brand brand = BrandMapper.mapToBrand(brandDTO, new Brand());
		final Brand savedBrand = brandRepository.save(brand);
		return BrandMapper.mapToBrandDto(savedBrand, new BrandDTO());
	}

	public void delete(final Long id) {
		Brand brand = brandRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Brand", String.valueOf(id), "id"));

		List<Car> cars = carRepository.findByBrand(brand);

		if (!cars.isEmpty()) {
			carRepository.deleteAll(cars);
		}

		brandRepository.deleteById(brand.getId());
	}

	public BrandDTO update(final BrandDTO brandDTO) {
		final Brand brand = BrandMapper.mapToBrand(brandDTO, new Brand());
		final Brand savedBrand = brandRepository.save(brand);
		final BrandDTO dto = BrandMapper.mapToBrandDto(savedBrand, new BrandDTO());
		return dto;
	}

}
