package br.com.batista.mapper;

import br.com.batista.dto.ad.response.*;
import br.com.batista.entity.*;

import java.util.*;

public class AdMapper {

    public static AdResponse mapToAdResponseDto (Ad ad) {
        return Optional.ofNullable(ad)
                .map(e -> new AdResponse(e.getId(), CarMapper.mapToCarResponseDTO(e.getCar()), e.getDescription(), e.getPrice(),
                        e.getUser().getUsername())).orElse(null);
    }

}
