package br.com.batista.dto;

public class CarDTO {
	private Long id;
	private String color;
	private int power;
	private int door;
	private int manufacturing;
	private String plate;
	private String name;

	public Long getId() {
		return id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public String getColor() {
		return color;
	}

	public void setColor(final String color) {
		this.color = color;
	}

	public int getPower() {
		return power;
	}

	public void setPower(final int power) {
		this.power = power;
	}

	public int getDoor() {
		return door;
	}

	public void setDoor(final int door) {
		this.door = door;
	}

	public int getManufacturing() {
		return manufacturing;
	}

	public void setManufacturing(final int manufacturing) {
		this.manufacturing = manufacturing;
	}

	public String getPlate() {
		return plate;
	}

	public void setPlate(final String plate) {
		this.plate = plate;
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

}
