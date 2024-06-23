package br.com.batista.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public class DealershipDTO {

	@Schema(description = "Dealership ID", example = "1")
	private Long id;

	@Schema(description = "CNPJ number of the dealership", example = "12345678000195")
	private String cnpj;

	@Schema(description = "Name of the dealership", example = "Auto City Dealership")
	private String name;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}