package br.com.batista.mapper;

import br.com.batista.dto.car.base.*;
import br.com.batista.dto.car.request.*;
import br.com.batista.dto.car.response.*;
import br.com.batista.entity.*;

import java.util.*;

public class CarMapper {

    public static Car mapToCar (CarBase carBase) {
        if (carBase == null) {
            return null;
        }
        Car car = new Car();
        car.setName(carBase.getName());
        car.setColor(carBase.getColor());
        car.setDoor(carBase.getDoor());
        car.setManufacturing(carBase.getManufacturing());
        car.setPlate(carBase.getPlate());
        car.setPower(carBase.getPower());

        if (carBase instanceof UpdateCarRequest dto) {
            car.setId(dto.getId());
        }

        return car;
    }

    public static CarResponse mapToCarResponseDTO (Car car) {
        return Optional.ofNullable(car)
                .map(c -> new CarResponse(c.getId(), c.getColor(), c.getPower(), c.getDoor(), c.getManufacturing(),
                        c.getPlate(), c.getName(),
                        Optional.ofNullable(c.getBrand()).map(BrandMapper::mapToBrandResponseDto).orElse(null),
                        Optional.ofNullable(c.getModel()).map(ModelMapper::mapToModelResponseDto).orElse(null),
                        Optional.ofNullable(c.getDealership()).map(DealershipMapper::mapToDealershipResponseDto)
                                .orElse(null))).orElse(null);
    }


}
