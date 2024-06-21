package br.com.batista.dto;

public class ResponseCarDTO extends CarDTO {

	private BrandDTO brand;
	private ModelDTO model;
	private DealershipDTO dealership;

	public BrandDTO getBrand() {
		return brand;
	}

	public void setBrand(BrandDTO brand) {
		this.brand = brand;
	}

	public ModelDTO getModel() {
		return model;
	}

	public void setModel(ModelDTO model) {
		this.model = model;
	}

	public DealershipDTO getDealership() {
		return dealership;
	}

	public void setDealership(DealershipDTO dealership) {
		this.dealership = dealership;
	}

}
