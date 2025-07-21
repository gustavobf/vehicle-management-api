package br.com.batista.mapper;

import br.com.batista.dto.dealership.base.*;
import br.com.batista.dto.dealership.request.*;
import br.com.batista.dto.dealership.response.*;
import br.com.batista.entity.*;

import java.util.*;

public class DealershipMapper {

    public static DealershipResponse mapToDealershipResponseDto (Dealership dealership) {
        return Optional.ofNullable(dealership).map(d -> new DealershipResponse(d.getId(), d.getCnpj(), d.getName()))
                .orElse(null);
    }


    public static Dealership mapToDealership (DealershipBase dealershipBase) {
        if (dealershipBase == null) {
            return null;
        }

        Dealership dealership = new Dealership();
        dealership.setCnpj(dealershipBase.getCnpj());
        dealership.setName(dealershipBase.getName());

        if (dealershipBase instanceof UpdateDealershipRequest dto) {
            dealership.setId(dto.getId());
        }

        return dealership;
    }

}
