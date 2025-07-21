package br.com.batista.mapper;

import br.com.batista.dto.brand.base.*;
import br.com.batista.dto.brand.request.*;
import br.com.batista.dto.brand.response.*;
import br.com.batista.entity.*;

import java.util.*;

public class BrandMapper {

    public static BrandResponse mapToBrandResponseDto (Brand entity) {
        return Optional.ofNullable(entity).map(e -> new BrandResponse(e.getId(), e.getName(), e.getCountry()))
                .orElse(null);
    }

    public static Brand mapToBrand (BrandBase brandBase) {
        if (brandBase == null) {
            return null;
        }

        Brand brand = new Brand();
        brand.setName(brandBase.getName());
        brand.setCountry(brandBase.getCountry());

        if (brandBase instanceof UpdateBrandRequest dto) {
            brand.setId(dto.getId());
        }

        return brand;
    }

}
