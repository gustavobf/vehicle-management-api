package br.com.batista.dto.car;

import io.swagger.v3.oas.annotations.media.Schema;

public class CarDTO {

	@Schema(description = "Car ID", example = "1")
	private Long id;

	@Schema(description = "Color of the car", example = "Red")
	private String color;

	@Schema(description = "Power of the car (in horsepower)", example = "200")
	private int power;

	@Schema(description = "Number of doors", example = "4")
	private int door;

	@Schema(description = "Year of manufacturing", example = "2023")
	private int manufacturing;

	@Schema(description = "License plate number", example = "ABC1234")
	private String plate;

	@Schema(description = "Name of the car model", example = "Toyota Camry")
	private String name;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public int getPower() {
		return power;
	}

	public void setPower(int power) {
		this.power = power;
	}

	public int getDoor() {
		return door;
	}

	public void setDoor(int door) {
		this.door = door;
	}

	public int getManufacturing() {
		return manufacturing;
	}

	public void setManufacturing(int manufacturing) {
		this.manufacturing = manufacturing;
	}

	public String getPlate() {
		return plate;
	}

	public void setPlate(String plate) {
		this.plate = plate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}