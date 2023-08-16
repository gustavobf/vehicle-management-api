package br.com.batista.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.batista.dto.ModelDTO;
import br.com.batista.model.Modelo;
import br.com.batista.repositories.ModelRepository;

@Service
public class ModelService {

	@Autowired
	private ModelRepository modeloRepository;

	public List<ModelDTO> getAll() {
		final List<Modelo> lista = modeloRepository.findAll();
		return lista.stream().map(this::convertToDTO).collect(Collectors.toList());
	}

	public Optional<ModelDTO> getById(final Long id) {
		final Optional<Modelo> modelo = modeloRepository.findById(id);
		final ModelDTO modeloDTO = convertToDTO(modelo.get());
		return Optional.of(modeloDTO);
	}

	public ModelDTO create(final ModelDTO modeloDTO) {
		final Modelo modelo = convertToEntity(modeloDTO);
		final Modelo modeloSalvo = modeloRepository.save(modelo);
		final ModelDTO dto = convertToDTO(modeloSalvo);
		return dto;
	}

	public void delete(final Long id) {
		modeloRepository.deleteById(id);
	}

	public ModelDTO update(final Long id, final ModelDTO modeloDTO) {
		modeloDTO.setId(id);
		final Modelo modelo = convertToEntity(modeloDTO);
		final Modelo modeloSalvo = modeloRepository.save(modelo);
		final ModelDTO dto = convertToDTO(modeloSalvo);
		return dto;
	}

	public ModelDTO convertToDTO(final Modelo modelo) {
		final ModelDTO modeloDTO = new ModelDTO();
		modeloDTO.setId(modelo.getId());
		modeloDTO.setNome(modelo.getNome());
		return modeloDTO;
	}

	public Modelo convertToEntity(final ModelDTO modeloDTO) {
		final Modelo modelo = new Modelo();
		modelo.setId(modeloDTO.getId());
		modelo.setNome(modeloDTO.getNome());
		return modelo;
	}

}
