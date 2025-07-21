package br.com.batista.service;

import br.com.batista.dto.api.response.*;
import br.com.batista.dto.car.*;
import br.com.batista.dto.car.request.*;
import br.com.batista.dto.car.response.*;
import br.com.batista.entity.*;
import br.com.batista.exception.*;
import br.com.batista.mapper.*;
import br.com.batista.repository.*;
import jakarta.validation.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.data.domain.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
public class CarService {

    private final CarRepository carRepository;
    private final ModelRepository modelrepository;
    private final BrandRepository brandRepository;
    private final DealershipRepository dealershipRepository;

    @Autowired
    public CarService (CarRepository carRepository, ModelRepository modelrepository, BrandRepository brandRepository,
                       DealershipRepository dealershipRepository) {
        this.carRepository = carRepository;
        this.modelrepository = modelrepository;
        this.brandRepository = brandRepository;
        this.dealershipRepository = dealershipRepository;
    }

    public PageResponse<CarResponse> getAll (Pageable pageable) {
        Page<Car> page = carRepository.findAll(pageable);
        return new PageResponse<>(page.map(CarMapper::mapToCarResponseDTO));
    }

    public CarResponse getById (final Long id) {
        final Optional<Car> car = carRepository.findById(id);
        return CarMapper.mapToCarResponseDTO(car.get());
    }

    public CarResponse create (final @Valid CreateCarRequest carDTO) {
        final Car car = CarMapper.mapToCar(carDTO);

        Brand brand = brandRepository.findById(carDTO.getBrandId())
                .orElseThrow(() -> new ResourceNotFoundException("Brand", String.valueOf(carDTO.getBrandId()), "id"));

        Model model = modelrepository.findById(carDTO.getModelId())
                .orElseThrow(() -> new ResourceNotFoundException("Model", String.valueOf(carDTO.getModelId()), "id"));

        Dealership dealership = dealershipRepository.findById(carDTO.getDealershipId()).orElseThrow(
                () -> new ResourceNotFoundException("Dealership", String.valueOf(carDTO.getDealershipId()), "id"));

        car.setBrand(brand);
        car.setModel(model);
        car.setDealership(dealership);

        final Car savedCar = carRepository.save(car);
        return CarMapper.mapToCarResponseDTO(savedCar);
    }

    public void delete (final Long id) {
        Car savedCar = carRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Car", String.valueOf(id), "id"));
        carRepository.deleteById(savedCar.getId());
    }

    public CarResponse update (final UpdateCarRequest carDTO) {
        final Car car = CarMapper.mapToCar(carDTO);
        final Car savedCar = carRepository.save(car);
        return CarMapper.mapToCarResponseDTO(savedCar);
    }

}
