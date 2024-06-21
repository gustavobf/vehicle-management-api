package br.com.batista.dto;

public class RequestCarDTO extends CarDTO {

	private Long brandId;
	private Long modelId;
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
