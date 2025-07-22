package br.com.batista.dto.ad.response;

import br.com.batista.dto.car.response.*;
import io.swagger.v3.oas.annotations.media.*;

import java.math.*;

public record AdResponse(@Schema(description = "Ad ID", example = "1") Long id,

                         @Schema(description = "Car associated with the ad") CarResponse car,

                         @Schema(description = "Description of the ad", example = "Well maintained, single owner") String description,

                         @Schema(description = "Price of the car in the ad", example = "25000.00") double price,

                         @Schema(description = "User who created the ad") String username) {
}
