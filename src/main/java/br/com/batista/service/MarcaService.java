package br.com.batista.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.batista.dto.MarcaDTO;
import br.com.batista.model.Marca;
import br.com.batista.repositories.MarcaRepository;

@Service
public class MarcaService {

	@Autowired
	private MarcaRepository marcaRepository;
	
	@Autowired
	private ModelMapper modelMapper;

	public List<MarcaDTO> getAll() {
		List<Marca> lista = marcaRepository.findAll();
		return lista.stream().map(this::convertToDTO).toList();
	}

	public Optional<MarcaDTO> getById(Long id) {
		Optional<Marca> marca = marcaRepository.findById(id);
		MarcaDTO marcaDTO = this.convertToDTO(marca.get());
		return Optional.of(marcaDTO);
	}

	public MarcaDTO create(MarcaDTO marcaDTO) {
		Marca marca = this.convertToEntity(marcaDTO);
		Marca marcaSalva = marcaRepository.save(marca);
		MarcaDTO dto = this.convertToDTO(marcaSalva);
		return dto;
	}

	public void delete(Long id) {
		marcaRepository.deleteById(id);
	}

	public MarcaDTO update(Long id, MarcaDTO marcaDTO) {
		marcaDTO.setId(id);
		Marca marca = this.convertToEntity(marcaDTO);
		Marca marcaSalva = marcaRepository.save(marca);
		MarcaDTO dto = this.convertToDTO(marcaSalva);
		return dto;
	}
	
	public MarcaDTO convertToDTO(Marca marca) {
		MarcaDTO marcaDTO = modelMapper.map(marca, MarcaDTO.class);
		return marcaDTO;
	}
	
	public Marca convertToEntity(MarcaDTO marcaDTO) {
		Marca marca = modelMapper.map(marcaDTO, Marca.class);
		return marca;
	}
	

}
