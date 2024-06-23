package br.com.batista.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public class RequestCarDTO extends CarDTO {

	@Schema(description = "ID of the brand", example = "1")
	private Long brandId;

	@Schema(description = "ID of the model", example = "1")
	private Long modelId;

	@Schema(description = "ID of the dealership", example = "1")
	private Long dealershipId;

	public Long getBrandId() {
		return brandId;
	}

	public void setBrandId(Long brandId) {
		this.brandId = brandId;
	}

	public Long getModelId() {
		return modelId;
	}

	public void setModelId(Long modelId) {
		this.modelId = modelId;
	}

	public Long getDealershipId() {
		return dealershipId;
	}

	public void setDealershipId(Long dealershipId) {
		this.dealershipId = dealershipId;
	}
}