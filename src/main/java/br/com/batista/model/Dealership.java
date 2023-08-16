package br.com.batista.model;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "dealership")
public class Dealership implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String cnpj;
	private String nome;

	public Dealership() {
	}

	public Dealership(final Long id, final String cnpj, final String nome) {
		super();
		this.id = id;
		this.cnpj = cnpj;
		this.nome = nome;
	}

	public Long getId() {
		return id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(final String cnpj) {
		this.cnpj = cnpj;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(final String nome) {
		this.nome = nome;
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
		final Dealership other = (Dealership) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Concessionaria [id=" + id + ", cnpj=" + cnpj + ", nome=" + nome + "]";
	}

	//	public String toCsv() {
	//		return "Nome: " + this.getNome() + ", CNPJ: " + this.getCnpj() + ", Id: " + super.getId();
	//	}

}
