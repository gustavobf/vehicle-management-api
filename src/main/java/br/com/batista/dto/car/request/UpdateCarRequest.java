package br.com.batista.dto.car.request;

import br.com.batista.dto.car.base.*;
import io.swagger.v3.oas.annotations.media.*;
import jakarta.validation.constraints.*;

public class UpdateCarRequest implements CarBase {

    @NotNull(message = "ID is required")
    @Schema(description = "Car ID", example = "123", required = true)
    private Long id;

    private String color;
    private int power;
    private int door;
    private int manufacturing;
    private String plate;
    private String name;

    public UpdateCarRequest () {
    }

    public Long getId () {
        return id;
    }

    public void setId (Long id) {
        this.id = id;
    }

    @Override
    public String getColor () {
        return color;
    }

    public void setColor (String color) {
        this.color = color;
    }

    @Override
    public int getPower () {
        return power;
    }

    public void setPower (int power) {
        this.power = power;
    }

    @Override
    public int getDoor () {
        return door;
    }

    public void setDoor (int door) {
        this.door = door;
    }

    @Override
    public int getManufacturing () {
        return manufacturing;
    }

    public void setManufacturing (int manufacturing) {
        this.manufacturing = manufacturing;
    }

    @Override
    public String getPlate () {
        return plate;
    }

    public void setPlate (String plate) {
        this.plate = plate;
    }

    @Override
    public String getName () {
        return name;
    }

    public void setName (String name) {
        this.name = name;
    }
}
