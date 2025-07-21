package br.com.batista.service;

import br.com.batista.dto.api.response.*;
import br.com.batista.dto.dealership.*;
import br.com.batista.dto.dealership.request.*;
import br.com.batista.dto.dealership.response.*;
import br.com.batista.entity.*;
import br.com.batista.exception.*;
import br.com.batista.mapper.*;
import br.com.batista.repository.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.data.domain.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
public class DealershipService {

    private final DealershipRepository dealershipRepository;
    private final CarRepository carRepository;

    @Autowired
    public DealershipService (DealershipRepository dealershipRepository, CarRepository carRepository) {
        this.dealershipRepository = dealershipRepository;
        this.carRepository = carRepository;
    }

    public PageResponse<DealershipResponse> getAll (Pageable pageable) {
        Page<Dealership> page = dealershipRepository.findAll(pageable);
        return new PageResponse<>(page.map(DealershipMapper::mapToDealershipResponseDto));
    }

    public DealershipResponse getById (final Long id) {
        final Optional<Dealership> dealership = dealershipRepository.findById(id);
        return DealershipMapper.mapToDealershipResponseDto(dealership.get());
    }

    public DealershipResponse create (final CreateDealershipRequest dealershipDTO) {
        final Dealership dealership = DealershipMapper.mapToDealership(dealershipDTO);
        final Dealership savedDealership = dealershipRepository.save(dealership);
        return DealershipMapper.mapToDealershipResponseDto(savedDealership);
    }

    public void delete (final Long id) {

        Dealership dealership = dealershipRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Dealership", String.valueOf(id), "id"));

        List<Car> cars = carRepository.findByDealership(dealership);

        if (!cars.isEmpty()) {
            carRepository.deleteAll(cars);
        }

        dealershipRepository.deleteById(id);
    }

    public DealershipResponse update (final UpdateDealershipRequest dealershipDTO) {
        final Dealership dealership = DealershipMapper.mapToDealership(dealershipDTO);
        final Dealership savedDealership = dealershipRepository.save(dealership);
        return DealershipMapper.mapToDealershipResponseDto(savedDealership);
    }

}
