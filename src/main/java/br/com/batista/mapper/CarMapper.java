package br.com.batista.mapper;

import br.com.batista.dto.BrandDTO;
import br.com.batista.dto.CarDTO;
import br.com.batista.dto.DealershipDTO;
import br.com.batista.dto.ModelDTO;
import br.com.batista.dto.ResponseCarDTO;
import br.com.batista.entity.Car;

public class CarMapper {

	public static CarDTO mapToCarDTO(Car car, CarDTO carDTO) {
		carDTO.setId(car.getId());
		carDTO.setName(car.getName());
		carDTO.setColor(car.getColor());
		carDTO.setDoor(car.getDoor());
		carDTO.setManufacturing(car.getManufacturing());
		carDTO.setPlate(car.getPlate());
		carDTO.setPower(car.getPower());
		return carDTO;
	}

	public static Car mapToCar(CarDTO carDTO, Car car) {
		car.setId(carDTO.getId());
		car.setName(carDTO.getName());
		car.setColor(carDTO.getColor());
		car.setDoor(carDTO.getDoor());
		car.setManufacturing(carDTO.getManufacturing());
		car.setPlate(carDTO.getPlate());
		car.setPower(carDTO.getPower());
		return car;
	}

	public static ResponseCarDTO mapToCarResponseDTO(Car car, ResponseCarDTO carDTO) {
		carDTO.setId(car.getId());
		carDTO.setName(car.getName());
		carDTO.setColor(car.getColor());
		carDTO.setDoor(car.getDoor());
		carDTO.setManufacturing(car.getManufacturing());
		carDTO.setPlate(car.getPlate());
		carDTO.setPower(car.getPower());
		carDTO.setBrand(BrandMapper.mapToBrandDto(car.getBrand(), new BrandDTO()));
		carDTO.setModel(ModelMapper.mapToModelDto(car.getModel(), new ModelDTO()));
		carDTO.setDealership(DealershipMapper.mapToDealershipDto(car.getDealership(), new DealershipDTO()));
		return carDTO;
	}

}
