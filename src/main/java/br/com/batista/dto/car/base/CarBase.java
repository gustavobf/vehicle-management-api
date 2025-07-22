package br.com.batista.dto.car.base;

import io.swagger.v3.oas.annotations.media.*;
import jakarta.validation.constraints.*;

public interface CarBase {

    @Schema(description = "Color of the car", example = "Red", required = true)
    @NotBlank(message = "Color is required")
    String getColor ();

    @Schema(description = "Power of the car (in horsepower)", example = "200", required = true)
    @Min(value = 1, message = "Power must be greater than zero")
    int getPower ();

    @Schema(description = "Number of doors", example = "4", required = true)
    @Min(value = 1, message = "There must be at least one door")
    int getDoor ();

    @Schema(description = "Year of manufacturing", example = "2023", required = true)
    @Min(value = 1886, message = "Manufacturing year is invalid")
    int getManufacturing ();

    @Schema(description = "License plate number", example = "ABC1234", required = true)
    @NotBlank(message = "Plate is required")
    String getPlate ();

    @Schema(description = "Name of the car model", example = "Toyota Camry", required = true)
    @NotBlank(message = "Car name is required")
    String getName ();

}
