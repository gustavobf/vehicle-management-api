package br.com.batista.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.batista.dto.CarDTO;
import br.com.batista.dto.RequestCarDTO;
import br.com.batista.dto.ResponseCarDTO;
import br.com.batista.entity.Brand;
import br.com.batista.entity.Car;
import br.com.batista.entity.Dealership;
import br.com.batista.entity.Model;
import br.com.batista.exception.ResourceNotFoundException;
import br.com.batista.mapper.CarMapper;
import br.com.batista.repository.BrandRepository;
import br.com.batista.repository.CarRepository;
import br.com.batista.repository.DealershipRepository;
import br.com.batista.repository.ModelRepository;

@Service
public class CarService {

	private CarRepository carRepository;
	private ModelRepository modelrepository;
	private BrandRepository brandRepository;
	private DealershipRepository dealershipRepository;

	@Autowired
	public CarService(CarRepository carRepository, ModelRepository modelrepository, BrandRepository brandRepository,
			DealershipRepository dealershipRepository) {
		this.carRepository = carRepository;
		this.modelrepository = modelrepository;
		this.brandRepository = brandRepository;
		this.dealershipRepository = dealershipRepository;
	}

	public Page<ResponseCarDTO> getAll(Pageable pageable) {
		return carRepository.findAll(pageable)
				.map(entity -> CarMapper.mapToCarResponseDTO(entity, new ResponseCarDTO()));
	}

	public ResponseCarDTO getById(final Long id) {
		final Optional<Car> car = carRepository.findById(id);
		final ResponseCarDTO carDTO = CarMapper.mapToCarResponseDTO(car.get(), new ResponseCarDTO());
		return carDTO;
	}

	public ResponseCarDTO create(final RequestCarDTO carDTO) {
		final Car car = CarMapper.mapToCar(carDTO, new Car());

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
		final ResponseCarDTO dto = CarMapper.mapToCarResponseDTO(savedCar, new ResponseCarDTO());
		return dto;
	}

	public void delete(final Long id) {
		Car savedCar = carRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Car", String.valueOf(id), "id"));
		carRepository.deleteById(savedCar.getId());
	}

	public CarDTO update(final CarDTO carDTO) {
		final Car car = CarMapper.mapToCar(carDTO, new Car());
		final Car savedCar = carRepository.save(car);
		final CarDTO dto = CarMapper.mapToCarDTO(savedCar, new CarDTO());
		return dto;
	}

}
