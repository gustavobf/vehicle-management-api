package br.com.batista.model;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "brand")
public class Brand implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nome;
	private String pais;

	public Brand() {
	}

	public Brand(final Long id, final String nome, final String pais) {
		super();
		this.id = id;
		this.nome = nome;
		this.pais = pais;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(final String nome) {
		this.nome = nome;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(final String pais) {
		this.pais = pais;
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
		final Brand other = (Brand) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Marca [id=" + id + ", nome=" + nome + ", pais=" + pais + "]";
	}

	//	public String toCsv() {
	//		return "Nome: " + this.getNome() + ", Pais: " + this.getPais() + ", Id: " + super.getId();
	//	}

}
