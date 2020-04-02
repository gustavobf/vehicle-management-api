package br.com.batista.model;

import java.util.ArrayList;
import java.util.List;

public class Marca {

	private int idMarca;
	private String nome;
	private String pais;

	public int getIdMarca() {
		return idMarca;
	}

	public void setIdMarca(int idMarca) {
		this.idMarca = idMarca;
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
		return "Marca [idMarca=" + idMarca + ", nome=" + nome + ", pais=" + pais + "]";
	}

}
