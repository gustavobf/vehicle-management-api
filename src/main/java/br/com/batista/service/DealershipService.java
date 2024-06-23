package br.com.batista.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.batista.dto.DealershipDTO;
import br.com.batista.entity.Car;
import br.com.batista.entity.Dealership;
import br.com.batista.exception.ResourceNotFoundException;
import br.com.batista.mapper.DealershipMapper;
import br.com.batista.repository.CarRepository;
import br.com.batista.repository.DealershipRepository;

@Service
public class DealershipService {

	private DealershipRepository dealershipRepository;
	private CarRepository carRepository;

	@Autowired
	public DealershipService(DealershipRepository dealershipRepository, CarRepository carRepository) {
		this.dealershipRepository = dealershipRepository;
		this.carRepository = carRepository;
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

		Dealership dealership = dealershipRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Dealership", String.valueOf(id), "id"));

		List<Car> cars = carRepository.findByDealership(dealership);

		if (!cars.isEmpty()) {
			carRepository.deleteAll(cars);
		}

		dealershipRepository.deleteById(id);
	}

	public DealershipDTO update(final DealershipDTO dealershipDTO) {
		final Dealership dealership = DealershipMapper.mapToDealership(dealershipDTO, new Dealership());
		final Dealership savedDealership = dealershipRepository.save(dealership);
		final DealershipDTO dto = DealershipMapper.mapToDealershipDto(savedDealership, new DealershipDTO());
		return dto;
	}

}
