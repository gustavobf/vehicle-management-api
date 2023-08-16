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
	@JoinColumn(name = "modelo_id")
	private Model model;

	@ManyToOne()
	@JoinColumn(name = "marca_id")
	private Brand brand;

	@ManyToOne()
	@JoinColumn(name = "concessionaria_id")
	private Dealership dealership;

	private String cor;
	private int potencia;
	private int portas;
	private int ano;
	private String placa;
	private String nome;

	public Car() {
	}

	public Car(final Long id, final String cor, final int potencia, final int portas, final int ano, final String placa, final String nome) {
		super();
		this.id = id;
		this.cor = cor;
		this.potencia = potencia;
		this.portas = portas;
		this.ano = ano;
		this.placa = placa;
		this.nome = nome;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public String getCor() {
		return cor;
	}

	public void setCor(final String cor) {
		this.cor = cor;
	}

	public int getPotencia() {
		return potencia;
	}

	public void setPotencia(final int potencia) {
		this.potencia = potencia;
	}

	public int getPortas() {
		return portas;
	}

	public void setPortas(final int portas) {
		this.portas = portas;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(final int ano) {
		this.ano = ano;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(final String placa) {
		this.placa = placa;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(final String nome) {
		this.nome = nome;
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
		return "Carro [id=" + id + ", cor=" + cor + ", potencia=" + potencia + ", portas=" + portas + ", ano=" + ano
				+ ", placa=" + placa + ", nome=" + nome + "]";
	}

	//	public String toCsv() {
	//		return "Nome: " + this.getNome() + ", Cor: " + this.getCor() + ", Placa: " + this.getPlaca() + ", qtdPortas: "
	//				+ this.getPortas() + ", Potencia: " + this.getPotencia() + ", Ano: " + this.getAno() + ", Id: "
	//				+ super.getId();
	//	}
}
