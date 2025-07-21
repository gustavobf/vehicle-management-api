package br.com.batista.dto.dealership.request;

import br.com.batista.dto.dealership.base.*;

public class CreateDealershipRequest implements DealershipBase {

    private String cnpj;
    private String name;

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
