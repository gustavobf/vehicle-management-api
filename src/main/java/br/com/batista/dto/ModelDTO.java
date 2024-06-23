package br.com.batista.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public class ModelDTO {

	@Schema(description = "Model ID", example = "1")
	private Long id;

	@Schema(description = "Name of the model", example = "Camry")
	private String name;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}