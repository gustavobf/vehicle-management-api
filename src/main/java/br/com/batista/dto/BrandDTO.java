package br.com.batista.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public class BrandDTO {
	private Long id;
	private String name;

	//TODO analyze
	@Schema(description = "first name of the user", name = "firstName", type = "string", example = "Vatsal")
	private String country;

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(final String country) {
		this.country = country;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
