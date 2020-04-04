package br.com.batista.model;

public class Modelo {

	private int idModelo;
	private String nome;

	public int getIdModelo() {
		return idModelo;
	}

	public void setIdModelo(int idModelo) {
		this.idModelo = idModelo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public String toString() {
		return "Modelo [idModelo=" + idModelo + ", nome=" + nome + "]";
	}

	public String toCsv() {
		return "Nome: " + this.getNome() + ", Id: " + this.getIdModelo();
	}

}
