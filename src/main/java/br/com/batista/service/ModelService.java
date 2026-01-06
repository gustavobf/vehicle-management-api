package br.com.batista.service;

import br.com.batista.dto.api.response.*;
import br.com.batista.dto.model.request.*;
import br.com.batista.dto.model.response.*;
import br.com.batista.entity.*;
import org.springframework.data.domain.*;

public interface ModelService {

    PageResponse<ModelResponse> getAll (Pageable pageable);

    Model getEntityById (final Long id);

    ModelResponse getById (final Long id);

    ModelResponse create (final CreateModelRequest modelDTO);

    void delete (final Long id);

    ModelResponse update (final UpdateModelRequest modelDTO);

}
