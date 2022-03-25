package br.com.batista.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.batista.dto.ModeloDTO;
import br.com.batista.model.Modelo;
import br.com.batista.repositories.ModeloRepository;

@Service
public class ModeloService {

	@Autowired
	private ModeloRepository modeloRepository;

	@Autowired
	private ModelMapper modelMapper;

	public List<ModeloDTO> getAll() {
		List<Modelo> lista = modeloRepository.findAll();
		return lista.stream().map(this::convertToDTO).collect(Collectors.toList());
	}

	public Optional<ModeloDTO> getById(Long id) {
		Optional<Modelo> modelo = modeloRepository.findById(id);
		ModeloDTO modeloDTO = this.convertToDTO(modelo.get());
		return Optional.of(modeloDTO);
	}

	public ModeloDTO create(ModeloDTO modeloDTO) {
		Modelo modelo = this.convertToEntity(modeloDTO);
		Modelo modeloSalvo = modeloRepository.save(modelo);
		ModeloDTO dto = this.convertToDTO(modeloSalvo);
		return dto;
	}

	public void delete(Long id) {
		modeloRepository.deleteById(id);
	}

	public ModeloDTO update(Long id, ModeloDTO modeloDTO) {
		modeloDTO.setId(id);
		Modelo modelo = this.convertToEntity(modeloDTO);
		Modelo modeloSalvo = modeloRepository.save(modelo);
		ModeloDTO dto = this.convertToDTO(modeloSalvo);
		return dto;
	}

	public ModeloDTO convertToDTO(Modelo modelo) {
		ModeloDTO modeloDTO = modelMapper.map(modelo, ModeloDTO.class);
		return modeloDTO;
	}

	public Modelo convertToEntity(ModeloDTO modeloDTO) {
		Modelo modelo = modelMapper.map(modeloDTO, Modelo.class);
		return modelo;
	}

}
