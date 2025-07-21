package br.com.batista.dto.brand.request;

import br.com.batista.dto.brand.base.*;
import io.swagger.v3.oas.annotations.media.*;
import jakarta.validation.constraints.*;

public class UpdateBrandRequest implements BrandBase {

    @Schema(description = "Brand ID", example = "1")
    @NotNull(message = "ID cannot be null")
    private Long id;

    private String name;
    private String country;

    public UpdateBrandRequest() {
    }

    public Long getId () {
        return id;
    }

    public void setId (Long id) {
        this.id = id;
    }

    public String getName () {
        return name;
    }

    public void setName (String name) {
        this.name = name;
    }

    public String getCountry () {
        return country;
    }

    public void setCountry (String country) {
        this.country = country;
    }
}
