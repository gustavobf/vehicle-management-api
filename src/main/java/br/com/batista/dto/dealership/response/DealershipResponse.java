package br.com.batista.dto.dealership.response;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Dealership response DTO")
public record DealershipResponse(

        @Schema(description = "Dealership ID", example = "1")
        Long id,

        @Schema(description = "CNPJ number of the dealership", example = "12345678000195")
        String cnpj,

        @Schema(description = "Name of the dealership", example = "Auto City Dealership")
        String name

) {}
