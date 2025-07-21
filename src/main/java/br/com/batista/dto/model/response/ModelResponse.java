package br.com.batista.dto.model.response;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Model response DTO")
public record ModelResponse(

        @Schema(description = "Model ID", example = "1") Long id,

        @Schema(description = "Name of the model", example = "Camry") String name

) {
}
