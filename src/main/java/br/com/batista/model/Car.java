package br.com.batista.model;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "car")
public class Car implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne()
	@JoinColumn(name = "model_id")
	private Model model;

	@ManyToOne()
	@JoinColumn(name = "brand_id")
	private Brand brand;

	@ManyToOne()
	@JoinColumn(name = "dealership_id")
	private Dealership dealership;

	private String color;
	private int power;
	private int door;
	private int manufacturing;
	private String plate;
	private String name;

	public Car() {
	}

	public Car(final Long id, final String color, final int power, final int door, final int manufacturing,
			final String plate, final String name) {
		super();
		this.id = id;
		this.color = color;
		this.power = power;
		this.door = door;
		this.manufacturing = manufacturing;
		this.plate = plate;
		this.name = name;
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

	public Long getId() {
		return id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final Car other = (Car) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Car [id=" + id + ", model=" + model + ", brand=" + brand + ", dealership=" + dealership + ", color="
				+ color + ", power=" + power + ", door=" + door + ", manufacturing=" + manufacturing + ", plate="
				+ plate + ", name=" + name + "]";
	}

}
