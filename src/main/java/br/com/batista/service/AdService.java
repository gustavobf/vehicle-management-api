package br.com.batista.service;

import br.com.batista.dto.ad.request.*;
import br.com.batista.dto.ad.response.*;
import br.com.batista.dto.api.response.*;
import br.com.batista.entity.*;
import org.springframework.data.domain.*;

public interface AdService {
    PageResponse<AdResponse> getAll (Pageable pageable);

    PageResponse<AdResponse> getAllActive (Pageable pageable);

    Ad getEntityById (Long id);

    AdResponse getById (Long id);

    AdResponse create (CreateAdRequest adDTO);

    AdResponse update (UpdateAdRequest adDTO);

    void delete (Long id);
}