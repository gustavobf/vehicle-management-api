package br.com.batista.service.impl;

import br.com.batista.dto.api.response.*;
import br.com.batista.dto.dealership.request.*;
import br.com.batista.dto.dealership.response.*;
import br.com.batista.entity.*;
import br.com.batista.exception.*;
import br.com.batista.mapper.*;
import br.com.batista.repository.*;
import br.com.batista.service.*;
import jakarta.transaction.*;
import lombok.*;
import org.slf4j.*;
import org.springframework.data.domain.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
@RequiredArgsConstructor
public class DealershipServiceImpl implements DealershipService {

    private static final Logger logger = LoggerFactory.getLogger(DealershipServiceImpl.class);

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
        return dealershipRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Dealership", String.valueOf(id), "id"));
    }

    @Transactional
    public DealershipResponse create (final CreateDealershipRequest dealershipDTO) {
        try {
            final Dealership dealership = DealershipMapper.mapToDealership(dealershipDTO);
            final Dealership savedDealership = dealershipRepository.save(dealership);
            return DealershipMapper.mapToDealershipResponseDto(savedDealership);
        } catch (Exception e) {
            logger.error("Error creating Dealership: {}", e.getMessage());
            throw e;
        }
    }

    @Transactional
    public void delete (final Long id) {
        try {
            Dealership dealership = dealershipRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Dealership", String.valueOf(id), "id"));

            List<Car> cars = carRepository.findByDealership(dealership);

            if (!cars.isEmpty()) {
                carRepository.deleteAll(cars);
            }

            dealershipRepository.deleteById(id);
        } catch (Exception e) {
            logger.error("Error deleting Dealership: {}", e.getMessage());
            throw e;
        }
    }

    @Transactional
    public DealershipResponse update (final UpdateDealershipRequest dealershipDTO) {
        try {
            final Dealership dealership = DealershipMapper.mapToDealership(dealershipDTO);
            final Dealership savedDealership = dealershipRepository.save(dealership);
            return DealershipMapper.mapToDealershipResponseDto(savedDealership);
        } catch (Exception e) {
            logger.error("Error updating Dealership: {}", e.getMessage());
            throw e;
        }
    }

}
