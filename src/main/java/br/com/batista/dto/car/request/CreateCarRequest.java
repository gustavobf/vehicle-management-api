package br.com.batista.dto.car.request;

import br.com.batista.dto.car.base.*;
import io.swagger.v3.oas.annotations.media.*;
import jakarta.validation.constraints.*;

public class CreateCarRequest implements CarBase {

    private String color;
    private int power;
    private int door;
    private int manufacturing;
    private String plate;
    private String name;

    @NotNull(message = "Brand ID is required")
    @Schema(description = "ID of the brand", example = "5", required = true)
    private Long brandId;

    @NotNull(message = "Model ID is required")
    @Schema(description = "ID of the model", example = "10", required = true)
    private Long modelId;

    @NotNull(message = "Dealership ID is required")
    @Schema(description = "ID of the dealership", example = "3", required = true)
    private Long dealershipId;

    public CreateCarRequest() {
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

    public Long getBrandId () {
        return brandId;
    }

    public void setBrandId (Long brandId) {
        this.brandId = brandId;
    }

    public Long getModelId () {
        return modelId;
    }

    public void setModelId (Long modelId) {
        this.modelId = modelId;
    }

    public Long getDealershipId () {
        return dealershipId;
    }

    public void setDealershipId (Long dealershipId) {
        this.dealershipId = dealershipId;
    }
}
