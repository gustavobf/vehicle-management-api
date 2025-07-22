package br.com.batista.dto.order.base;

import io.swagger.v3.oas.annotations.media.*;
import jakarta.validation.constraints.*;

public interface OrderBase {

    @NotNull(message = "Ad ID is required")
    @Schema(description = "ID of the advertisement associated with the order", example = "42", required = true)
    Long getAdId ();
}
