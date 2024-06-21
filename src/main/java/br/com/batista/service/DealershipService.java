package br.com.batista.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.batista.dto.DealershipDTO;
import br.com.batista.entity.Dealership;
import br.com.batista.mapper.DealershipMapper;
import br.com.batista.repository.DealershipRepository;

@Service
public class DealershipService {

	private DealershipRepository dealershipRepository;

	@Autowired
	public DealershipService(DealershipRepository dealershipRepository) {
		this.dealershipRepository = dealershipRepository;
	}

	public Page<DealershipDTO> getAll(Pageable pageable) {
		return dealershipRepository.findAll(pageable)
				.map(entity -> DealershipMapper.mapToDealershipDto(entity, new DealershipDTO()));
	}

	public DealershipDTO getById(final Long id) {
		final Optional<Dealership> dealership = dealershipRepository.findById(id);
		final DealershipDTO dealershipDTO = DealershipMapper.mapToDealershipDto(dealership.get(), new DealershipDTO());
		return dealershipDTO;
	}

	public DealershipDTO create(final DealershipDTO dealershipDTO) {
		final Dealership dealership = DealershipMapper.mapToDealership(dealershipDTO, new Dealership());
		final Dealership savedDealership = dealershipRepository.save(dealership);
		final DealershipDTO dto = DealershipMapper.mapToDealershipDto(savedDealership, new DealershipDTO());
		return dto;
	}

	public void delete(final Long id) {
		dealershipRepository.deleteById(id);
	}

	public DealershipDTO update(final DealershipDTO dealershipDTO) {
		final Dealership dealership = DealershipMapper.mapToDealership(dealershipDTO, new Dealership());
		final Dealership savedDealership = dealershipRepository.save(dealership);
		final DealershipDTO dto = DealershipMapper.mapToDealershipDto(savedDealership, new DealershipDTO());
		return dto;
	}

}
