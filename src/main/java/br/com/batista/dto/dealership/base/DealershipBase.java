package br.com.batista.dto.dealership.base;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

public interface DealershipBase {

    @Schema(description = "CNPJ number of the dealership", example = "12345678000195")
    @NotBlank(message = "CNPJ is required")
    @Size(min = 14, max = 14, message = "CNPJ must have exactly 14 characters")
    String getCnpj ();

    @Schema(description = "Name of the dealership", example = "Auto City Dealership")
    @NotBlank(message = "Name is required")
    @Size(max = 100, message = "Name must be at most 100 characters")
    String getName ();
}
