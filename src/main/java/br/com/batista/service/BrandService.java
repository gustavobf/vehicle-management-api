package br.com.batista.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.batista.dto.BrandDTO;
import br.com.batista.model.Marca;
import br.com.batista.repositories.BrandRepository;

@Service
public class BrandService {

	@Autowired
	private BrandRepository marcaRepository;

	public List<BrandDTO> getAll() {
		final List<Marca> lista = marcaRepository.findAll();
		return lista.stream().map(this::convertToDTO).collect(Collectors.toList());
	}

	public Optional<BrandDTO> getById(final Long id) {
		final Optional<Marca> marca = marcaRepository.findById(id);
		final BrandDTO marcaDTO = convertToDTO(marca.get());
		return Optional.of(marcaDTO);
	}

	public BrandDTO create(final BrandDTO marcaDTO) {
		final Marca marca = convertToEntity(marcaDTO);
		final Marca marcaSalva = marcaRepository.save(marca);
		return convertToDTO(marcaSalva);
	}

	public void delete(final Long id) {
		marcaRepository.deleteById(id);
	}

	public BrandDTO update(final BrandDTO marcaDTO) {
		final Marca marca = convertToEntity(marcaDTO);
		final Marca marcaSalva = marcaRepository.save(marca);
		final BrandDTO dto = convertToDTO(marcaSalva);
		return dto;
	}

	public BrandDTO convertToDTO(final Marca marca) {
		final BrandDTO marcaDTO = new BrandDTO();
		marcaDTO.setId(marca.getId());
		marcaDTO.setNome(marca.getNome());
		marcaDTO.setPais(marca.getPais());
		return marcaDTO;
	}

	public Marca convertToEntity(final BrandDTO marcaDTO) {
		final Marca marca = new Marca();
		marca.setId(marcaDTO.getId());
		marca.setNome(marcaDTO.getNome());
		marca.setPais(marcaDTO.getPais());
		return marca;
	}


}
