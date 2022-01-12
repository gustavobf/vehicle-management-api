package br.com.batista.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "carro")
public class Carro implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne()
	@JoinColumn(name = "modelo_id")
	private Modelo modelo;

	@ManyToOne()
	@JoinColumn(name = "marca_id")
	private Marca marca;

	@ManyToOne()
	@JoinColumn(name = "concessionaria_id")
	private Concessionaria concessionaria;

	private String cor;
	private int potencia;
	private int portas;
	private int ano;
	private String placa;
	private String nome;

	public Carro() {
	}

	public Carro(Long id, String cor, int potencia, int portas, int ano, String placa, String nome) {
		super();
		this.id = id;
		this.cor = cor;
		this.potencia = potencia;
		this.portas = portas;
		this.ano = ano;
		this.placa = placa;
		this.nome = nome;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	public int getPotencia() {
		return potencia;
	}

	public void setPotencia(int potencia) {
		this.potencia = potencia;
	}

	public int getPortas() {
		return portas;
	}

	public void setPortas(int portas) {
		this.portas = portas;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
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
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Carro other = (Carro) obj;
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
