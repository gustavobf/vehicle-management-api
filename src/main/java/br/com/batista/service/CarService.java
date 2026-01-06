package br.com.batista.service;

import br.com.batista.dto.api.response.*;
import br.com.batista.dto.car.request.*;
import br.com.batista.dto.car.response.*;
import br.com.batista.entity.*;
import jakarta.validation.*;
import org.springframework.data.domain.*;

public interface CarService {

    PageResponse<CarResponse> getAll (Pageable pageable);

    CarResponse getById (final Long id);

    Car getEntityById (final Long id);

    CarResponse create (final @Valid CreateCarRequest carDTO);

    void delete (final Long id);

    CarResponse update (final UpdateCarRequest carDTO);

}
