package br.com.batista.model;

public class Modelo extends Entidade {

	private String nome;

	public Modelo(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public String toString() {
		return "Modelo [idModelo=" + super.getId() + ", nome=" + nome + "]";
	}

	public String toCsv() {
		return "Nome: " + this.getNome() + ", Id: " + super.getId();
	}

}
