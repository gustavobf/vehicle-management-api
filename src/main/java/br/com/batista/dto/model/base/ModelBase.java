package br.com.batista.dto.model.base;

import io.swagger.v3.oas.annotations.media.*;
import jakarta.validation.constraints.*;

public interface ModelBase {

    @Schema(description = "Name of the model", example = "Camry", required = true)
    @NotBlank(message = "Model name is required")
    @Size(max = 100, message = "Model name must be at most 100 characters")
    String getName ();
}
