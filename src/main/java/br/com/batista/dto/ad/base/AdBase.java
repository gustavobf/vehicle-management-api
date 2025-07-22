package br.com.batista.dto.ad.base;

import io.swagger.v3.oas.annotations.media.*;
import jakarta.validation.constraints.*;

public interface AdBase {

    @NotNull(message = "Car ID is required")
    @Schema(description = "ID of the car related to the ad", example = "15", required = true)
    Long getCarId ();

    @NotBlank(message = "Description is required")
    @Schema(description = "Description of the advertisement", example = "Well maintained, single owner, low mileage", required = true)
    String getDescription ();

    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than zero")
    @Schema(description = "Price of the ad, must be greater than zero", example = "25000.00", required = true)
    double getPrice ();

    @NotNull(message = "User ID is required")
    @Schema(description = "ID of the user creating the ad", example = "3", required = true)
    Long getUserId ();
}