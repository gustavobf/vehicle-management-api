package br.com.batista.service;

import br.com.batista.dto.api.response.*;
import br.com.batista.dto.dealership.request.*;
import br.com.batista.dto.dealership.response.*;
import br.com.batista.entity.*;
import org.springframework.data.domain.*;

public interface DealershipService {

    PageResponse<DealershipResponse> getAll (Pageable pageable);

    DealershipResponse getById (final Long id);

    Dealership getEntityById (final Long id);

    DealershipResponse create (final CreateDealershipRequest dealershipDTO);

    void delete (final Long id);

    DealershipResponse update (final UpdateDealershipRequest dealershipDTO);

}
