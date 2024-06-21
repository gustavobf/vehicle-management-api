package br.com.batista.mapper;

import br.com.batista.dto.DealershipDTO;
import br.com.batista.entity.Dealership;

public class DealershipMapper {

	public static DealershipDTO mapToDealershipDto(Dealership dealership, DealershipDTO dealershipDTO) {
		if (dealership != null) {
			dealershipDTO.setId(dealership.getId());
			dealershipDTO.setCnpj(dealership.getCnpj());
			dealershipDTO.setName(dealership.getName());
			return dealershipDTO;
		} else {
			return null;
		}
	}

	public static Dealership mapToDealership(DealershipDTO dealershipDTO, Dealership dealership) {
		dealership.setId(dealershipDTO.getId());
		dealership.setCnpj(dealershipDTO.getCnpj());
		dealership.setName(dealershipDTO.getName());
		return dealership;
	}

}
