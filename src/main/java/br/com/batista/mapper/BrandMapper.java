package br.com.batista.mapper;

import br.com.batista.dto.BrandDTO;
import br.com.batista.entity.Brand;

public class BrandMapper {

	public static BrandDTO mapToBrandDto(Brand brand, BrandDTO brandDTO) {
		if (brand != null) {
			brandDTO.setId(brand.getId());
			brandDTO.setName(brand.getName());
			brandDTO.setCountry(brand.getCountry());
			return brandDTO;
		} else {
			return null;
		}

	}

	public static Brand mapToBrand(BrandDTO brandDTO, Brand brand) {
		brand.setId(brandDTO.getId());
		brand.setName(brandDTO.getName());
		brand.setCountry(brandDTO.getCountry());
		return brand;
	}

}
