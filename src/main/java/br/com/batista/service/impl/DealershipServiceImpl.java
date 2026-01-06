package br.com.batista.service.impl;

import br.com.batista.dto.api.response.*;
import br.com.batista.dto.dealership.request.*;
import br.com.batista.dto.dealership.response.*;
import br.com.batista.entity.*;
import br.com.batista.exception.*;
import br.com.batista.mapper.*;
import br.com.batista.repository.*;
import br.com.batista.service.*;
import lombok.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.data.domain.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
@RequiredArgsConstructor
public class DealershipServiceImpl implements DealershipService {

    private final DealershipRepository dealershipRepository;
    private final CarRepository carRepository;

    public PageResponse<DealershipResponse> getAll (Pageable pageable) {
        Page<Dealership> page = dealershipRepository.findAll(pageable);
        return new PageResponse<>(page.map(DealershipMapper::mapToDealershipResponseDto));
    }

    public DealershipResponse getById (final Long id) {
        return DealershipMapper.mapToDealershipResponseDto(this.getEntityById(id));
    }

    public Dealership getEntityById (final Long id) {
        return dealershipRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Dealership", String.valueOf(id), "id"));
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
