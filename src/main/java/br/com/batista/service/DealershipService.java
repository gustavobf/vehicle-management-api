package br.com.batista.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.batista.dto.DealershipDTO;
import br.com.batista.model.Dealership;
import br.com.batista.repositories.DealershipRepository;

@Service
public class DealershipService {

	@Autowired
	private DealershipRepository dealershipRepository;

	public List<DealershipDTO> getAll() {
		return dealershipRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
	}

	public Optional<DealershipDTO> getById(final Long id) {
		final Optional<Dealership> dealership = dealershipRepository.findById(id);
		final DealershipDTO dealershipDTO = convertToDTO(dealership.get());
		return Optional.of(dealershipDTO);
	}

	public DealershipDTO create(final DealershipDTO dealershipDTO) {
		final Dealership dealership = convertToEntity(dealershipDTO);
		final Dealership savedDealership = dealershipRepository.save(dealership);
		final DealershipDTO dto = convertToDTO(savedDealership);
		return dto;
	}

	public void delete(final Long id) {
		dealershipRepository.deleteById(id);
	}

	public DealershipDTO update(final DealershipDTO dealershipDTO) {
		final Dealership dealership = convertToEntity(dealershipDTO);
		final Dealership savedDealership = dealershipRepository.save(dealership);
		final DealershipDTO dto = convertToDTO(savedDealership);
		return dto;
	}

	public DealershipDTO convertToDTO(final Dealership dealership) {
		final DealershipDTO dealershipDTO = new DealershipDTO();
		dealershipDTO.setId(dealership.getId());
		dealershipDTO.setCnpj(dealership.getCnpj());
		dealershipDTO.setName(dealership.getName());
		return dealershipDTO;
	}

	public Dealership convertToEntity(final DealershipDTO dealershipDTO) {
		final Dealership dealership = new Dealership();
		dealership.setId(dealershipDTO.getId());
		dealership.setCnpj(dealershipDTO.getCnpj());
		dealership.setName(dealershipDTO.getName());
		return dealership;
	}

}
