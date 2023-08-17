package br.com.batista.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.batista.dto.BrandDTO;
import br.com.batista.model.Brand;
import br.com.batista.repositories.BrandRepository;

@Service
public class BrandService {

	@Autowired
	private BrandRepository brandRepository;

	public List<BrandDTO> getAll() {
		return brandRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
	}

	public Optional<BrandDTO> getById(final Long id) {
		final Optional<Brand> brand = brandRepository.findById(id);
		final BrandDTO brandDTO = convertToDTO(brand.get());
		return Optional.of(brandDTO);
	}

	public BrandDTO create(final BrandDTO brandDTO) {
		final Brand brand = convertToEntity(brandDTO);
		final Brand savedBrand = brandRepository.save(brand);
		return convertToDTO(savedBrand);
	}

	public void delete(final Long id) {
		brandRepository.deleteById(id);
	}

	public BrandDTO update(final BrandDTO brandDTO) {
		final Brand brand = convertToEntity(brandDTO);
		final Brand savedBrand = brandRepository.save(brand);
		final BrandDTO dto = convertToDTO(savedBrand);
		return dto;
	}

	public BrandDTO convertToDTO(final Brand brand) {
		final BrandDTO brandDTO = new BrandDTO();
		brandDTO.setId(brand.getId());
		brandDTO.setName(brand.getName());
		brandDTO.setCountry(brand.getCountry());
		return brandDTO;
	}

	public Brand convertToEntity(final BrandDTO brandDTO) {
		final Brand brand = new Brand();
		brand.setId(brandDTO.getId());
		brand.setName(brandDTO.getName());
		brand.setCountry(brandDTO.getCountry());
		return brand;
	}

}
