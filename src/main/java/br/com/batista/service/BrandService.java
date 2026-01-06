package br.com.batista.service;

import br.com.batista.dto.api.response.*;
import br.com.batista.dto.brand.request.*;
import br.com.batista.dto.brand.response.*;
import br.com.batista.entity.*;
import org.springframework.data.domain.*;

public interface BrandService {

    PageResponse<BrandResponse> getAll (Pageable pageable);

    Brand getEntityById (final Long id);

    BrandResponse getById (final Long id);

    BrandResponse create (final CreateBrandRequest brandDTO);

    void delete (final Long id);

    BrandResponse update (final UpdateBrandRequest brandDTO);

}
