package br.com.batista.service.impl;

import br.com.batista.dto.api.response.*;
import br.com.batista.dto.car.request.*;
import br.com.batista.dto.car.response.*;
import br.com.batista.entity.*;
import br.com.batista.exception.*;
import br.com.batista.mapper.*;
import br.com.batista.repository.*;
import br.com.batista.service.*;
import jakarta.validation.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.data.domain.*;
import org.springframework.stereotype.*;

@Service
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;
    private final ModelService modelService;
    private final BrandService brandService;
    private final DealershipService dealershipService;

    @Autowired
    public CarServiceImpl (CarRepository carRepository, ModelService modelService, BrandService brandService,
                           DealershipService dealershipService) {
        this.carRepository = carRepository;
        this.modelService = modelService;
        this.brandService = brandService;
        this.dealershipService = dealershipService;
    }

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

    public CarResponse create (final @Valid CreateCarRequest carDTO) {
        final Car car = CarMapper.mapToCar(carDTO);

        Brand brand = brandService.getEntityById(carDTO.getBrandId());

        Model model = modelService.getEntityById(carDTO.getModelId());

        Dealership dealership = dealershipService.getEntityById(carDTO.getDealershipId());

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
