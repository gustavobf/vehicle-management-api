package br.com.batista.dto.car.response;

import br.com.batista.dto.brand.response.*;
import br.com.batista.dto.dealership.response.*;
import br.com.batista.dto.model.response.*;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Car response DTO")
public record CarResponse(

        @Schema(description = "Car ID", example = "1") Long id,

        @Schema(description = "Color of the car", example = "Red") String color,

        @Schema(description = "Power of the car (in horsepower)", example = "200") int power,

        @Schema(description = "Number of doors", example = "4") int door,

        @Schema(description = "Year of manufacturing", example = "2023") int manufacturing,

        @Schema(description = "License plate number", example = "ABC1234") String plate,

        @Schema(description = "Name of the car model", example = "Toyota Camry") String name,

        @Schema(description = "Brand info") BrandResponse brand,

        @Schema(description = "Model info") ModelResponse model,

        @Schema(description = "Dealership info") DealershipResponse dealership) {
}
