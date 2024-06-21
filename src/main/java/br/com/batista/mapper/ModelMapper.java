package br.com.batista.mapper;

import br.com.batista.dto.ModelDTO;
import br.com.batista.entity.Model;

public class ModelMapper {

	public static ModelDTO mapToModelDto(Model model, ModelDTO modelDTO) {
		if (model != null) {
			modelDTO.setId(model.getId());
			modelDTO.setName(model.getName());
			return modelDTO;
		} else {
			return null;
		}
	}

	public static Model mapToModel(ModelDTO modelDTO, Model model) {
		model.setId(modelDTO.getId());
		model.setName(modelDTO.getName());
		return model;
	}

}
