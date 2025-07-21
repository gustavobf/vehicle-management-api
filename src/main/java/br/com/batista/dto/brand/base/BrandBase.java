package br.com.batista.dto.brand.base;

import io.swagger.v3.oas.annotations.media.*;
import jakarta.validation.constraints.*;

public interface BrandBase {

    @Schema(description = "Brand name", example = "Toyota")
    @NotBlank(message = "Name cannot be blank")
    @Size(max = 100, message = "Name must be at most 100 characters")
    String getName();

    @Schema(description = "Country of origin", example = "Japan")
    @NotBlank(message = "Country cannot be blank")
    @Size(max = 100, message = "Country must be at most 100 characters")
    String getCountry();
}
