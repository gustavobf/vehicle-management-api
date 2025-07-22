package br.com.batista.dto.model.request;

import br.com.batista.dto.model.base.*;
import io.swagger.v3.oas.annotations.media.*;
import jakarta.validation.constraints.*;

public class UpdateModelRequest implements ModelBase {

    @NotNull(message = "ID is required")
    @Schema(description = "Model ID", example = "123", required = true)
    private Long id;

    private String name;

    public Long getId () {
        return id;
    }

    public void setId (Long id) {
        this.id = id;
    }

    @Override
    public String getName () {
        return name;
    }

    public void setName (String name) {
        this.name = name;
    }
}
