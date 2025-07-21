package br.com.batista.dto.model.request;

import br.com.batista.dto.model.base.*;

public class CreateModelRequest implements ModelBase {

    private String name;

    @Override
    public String getName() { return name; }

    public void setName(String name) { this.name = name; }
}
