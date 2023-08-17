package br.com.batista.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.batista.dto.CarDTO;
import br.com.batista.model.Car;
import br.com.batista.repositories.CarRepository;

@Service
public class CarService {

	@Autowired
	private CarRepository carRepository;

	public List<CarDTO> getAll() {
		return carRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
	}

	public Optional<CarDTO> getById(final Long id) {
		final Optional<Car> car = carRepository.findById(id);
		final CarDTO carDTO = convertToDTO(car.get());
		return Optional.of(carDTO);
	}

	public CarDTO create(final CarDTO carDTO) {
		final Car car = convertToEntity(carDTO);
		final Car savedCar = carRepository.save(car);
		final CarDTO dto = convertToDTO(savedCar);
		return dto;
	}

	public void delete(final Long id) {
		carRepository.deleteById(id);
	}

	public CarDTO update(final CarDTO carDTO) {
		final Car car = convertToEntity(carDTO);
		final Car savedCar = carRepository.save(car);
		final CarDTO dto = convertToDTO(savedCar);
		return dto;
	}

	public CarDTO convertToDTO(final Car car) {
		final CarDTO carDTO = new CarDTO();
		carDTO.setId(car.getId());
		carDTO.setManufacturing(car.getManufacturing());
		carDTO.setColor(car.getColor());
		carDTO.setName(car.getName());
		carDTO.setPlate(car.getPlate());
		carDTO.setDoor(car.getDoor());
		carDTO.setPower(car.getPower());
		return carDTO;
	}

	public Car convertToEntity(final CarDTO carDTO) {
		final Car car = new Car();
		car.setId(carDTO.getId());
		car.setManufacturing(carDTO.getManufacturing());
		car.setColor(carDTO.getColor());
		car.setName(carDTO.getName());
		car.setPlate(carDTO.getPlate());
		car.setDoor(carDTO.getDoor());
		car.setPower(carDTO.getPower());
		return car;
	}

}
