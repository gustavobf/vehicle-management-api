package br.com.batista.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public class BrandDTO {

	@Schema(description = "Brand ID", example = "1")
	private Long id;

	@Schema(description = "Brand name", example = "Toyota")
	private String name;

	@Schema(description = "Country of origin", example = "Japan")
	private String country;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}