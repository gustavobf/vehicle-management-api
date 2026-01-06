package br.com.batista.service.impl;

import br.com.batista.dto.api.response.*;
import br.com.batista.dto.car.request.*;
import br.com.batista.dto.car.response.*;
import br.com.batista.entity.*;
import br.com.batista.exception.*;
import br.com.batista.mapper.*;
import br.com.batista.repository.*;
import br.com.batista.service.*;
import jakarta.transaction.*;
import jakarta.validation.*;
import lombok.*;
import org.slf4j.*;
import org.springframework.data.domain.*;
import org.springframework.stereotype.*;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {

    private static final Logger logger = LoggerFactory.getLogger(CarServiceImpl.class);

    private final CarRepository carRepository;
    private final ModelService modelService;
    private final BrandService brandService;
    private final DealershipService dealershipService;

    public PageResponse<CarResponse> getAll (Pageable pageable) {
        Page<Car> page = carRepository.findAll(pageable);
        return new PageResponse<>(page.map(CarMapper::mapToCarResponseDTO));
    }

    public CarResponse getById (final Long id) {
        return CarMapper.mapToCarResponseDTO(this.getEntityById(id));
    }

    public Car getEntityById (final Long id) {
        return carRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Car", String.valueOf(id), "id"));
    }

    @Transactional
    public CarResponse create (final @Valid CreateCarRequest carDTO) {
        try {
            final Car car = CarMapper.mapToCar(carDTO);

            Brand brand = brandService.getEntityById(carDTO.getBrandId());

            Model model = modelService.getEntityById(carDTO.getModelId());

            Dealership dealership = dealershipService.getEntityById(carDTO.getDealershipId());

            car.setBrand(brand);
            car.setModel(model);
            car.setDealership(dealership);

            final Car savedCar = carRepository.save(car);
            return CarMapper.mapToCarResponseDTO(savedCar);
        } catch (Exception e) {
            logger.error("Error creating Car: {}", e.getMessage());
            throw e;
        }
    }

    @Transactional
    public void delete (final Long id) {
        try {
            Car savedCar = carRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Car", String.valueOf(id), "id"));
            carRepository.deleteById(savedCar.getId());
        } catch (Exception e) {
            logger.error("Error deleting Car: {}", e.getMessage());
            throw e;
        }
    }

    @Transactional
    public CarResponse update (final UpdateCarRequest carDTO) {
        try {
            final Car car = CarMapper.mapToCar(carDTO);
            final Car savedCar = carRepository.save(car);
            return CarMapper.mapToCarResponseDTO(savedCar);
        } catch (Exception e) {
            logger.error("Error updating Car: {}", e.getMessage());
            throw e;
        }
    }

}
