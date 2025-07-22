package br.com.batista.mapper;

import br.com.batista.dto.model.base.*;
import br.com.batista.dto.model.request.*;
import br.com.batista.dto.model.response.*;
import br.com.batista.entity.*;

import java.util.*;

public class ModelMapper {

    public static ModelResponse mapToModelResponseDto (Model model) {
        return Optional.ofNullable(model).map(m -> new ModelResponse(m.getId(), m.getName())).orElse(null);
    }

    public static Model mapToModel (ModelBase modelBase) {
        if (modelBase == null) {
            return null;
        }

        Model model = new Model();
        model.setName(modelBase.getName());

        if (modelBase instanceof UpdateModelRequest dto) {
            model.setId(dto.getId());
        }

        return model;
    }

}
