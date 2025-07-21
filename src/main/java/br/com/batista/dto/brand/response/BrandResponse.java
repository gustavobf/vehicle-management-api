package br.com.batista.dto.brand.response;

import io.swagger.v3.oas.annotations.media.*;

public record BrandResponse(@Schema(description = "Brand ID", example = "1") Long id,

                            @Schema(description = "Brand name", example = "Toyota") String name,

                            @Schema(description = "Country of origin", example = "Japan") String country) {
}