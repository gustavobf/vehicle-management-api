package br.com.batista.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.batista.dto.ModelDTO;
import br.com.batista.model.Model;
import br.com.batista.repositories.ModelRepository;

@Service
public class ModelService {

	@Autowired
	private ModelRepository modelRepository;

	public List<ModelDTO> getAll() {
		return modelRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
	}

	public Optional<ModelDTO> getById(final Long id) {
		final Optional<Model> model = modelRepository.findById(id);
		final ModelDTO modelDTO = convertToDTO(model.get());
		return Optional.of(modelDTO);
	}

	public ModelDTO create(final ModelDTO modelDTO) {
		final Model model = convertToEntity(modelDTO);
		final Model savedModel = modelRepository.save(model);
		final ModelDTO dto = convertToDTO(savedModel);
		return dto;
	}

	public void delete(final Long id) {
		modelRepository.deleteById(id);
	}

	public ModelDTO update(final ModelDTO modelDTO) {
		final Model model = convertToEntity(modelDTO);
		final Model savedModel = modelRepository.save(model);
		final ModelDTO dto = convertToDTO(savedModel);
		return dto;
	}

	public ModelDTO convertToDTO(final Model model) {
		final ModelDTO modelDTO = new ModelDTO();
		modelDTO.setId(model.getId());
		modelDTO.setName(model.getNome());
		return modelDTO;
	}

	public Model convertToEntity(final ModelDTO modelDTO) {
		final Model model = new Model();
		model.setId(modelDTO.getId());
		model.setName(modelDTO.getName());
		return model;
	}

}
