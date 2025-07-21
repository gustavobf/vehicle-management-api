package br.com.batista.dto.brand.request;

import br.com.batista.dto.brand.base.*;

public class CreateBrandRequest implements BrandBase {

    private String name;
    private String country;

    public CreateBrandRequest() {
    }

    @Override
    public String getName () {
        return name;
    }

    public void setName (String name) {
        this.name = name;
    }

    @Override
    public String getCountry () {
        return country;
    }

    public void setCountry (String country) {
        this.country = country;
    }
}
