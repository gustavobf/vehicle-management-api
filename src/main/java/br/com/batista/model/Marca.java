package br.com.batista.model;

import java.util.ArrayList;
import java.util.List;

public class Marca extends Entidade {

	private String nome;
	private String pais;

	public Marca(String nome, String pais) {
		this.nome = nome;
		this.pais = pais;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	List<Carro> marca = new ArrayList<Carro>();

	@Override
	public String toString() {
		return "Marca [idMarca=" + super.getId() + ", nome=" + nome + ", pais=" + pais + "]";
	}

	public String toCsv() {
		return "Nome: " + this.getNome() + ", Pais: " + this.getPais() + ", Id: " + super.getId();
	}

}
