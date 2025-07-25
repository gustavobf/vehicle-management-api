package br.com.batista.dto.dealership.request;

import br.com.batista.dto.dealership.base.*;
import io.swagger.v3.oas.annotations.media.*;
import jakarta.validation.constraints.NotNull;

public class UpdateDealershipRequest implements DealershipBase {

    @NotNull(message = "ID is required")
    @Schema(description = "Dealership ID", example = "123", required = true)
    private Long id;

    private String cnpj;
    private String name;

    public Long getId () {
        return id;
    }

    public void setId (Long id) {
        this.id = id;
    }

    @Override
    public String getCnpj () {
        return cnpj;
    }

    public void setCnpj (String cnpj) {
        this.cnpj = cnpj;
    }

    @Override
    public String getName () {
        return name;
    }

    public void setName (String name) {
        this.name = name;
    }
}
