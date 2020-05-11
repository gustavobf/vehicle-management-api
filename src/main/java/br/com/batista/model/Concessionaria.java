package br.com.batista.model;

import java.util.ArrayList;
import java.util.List;

public class Concessionaria extends Entidade {

	private String cnpj;
	private String nome;

	public Concessionaria(String cnpj, String nome) {
		this.cnpj = cnpj;
		this.nome = nome;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	List<Carro> concessionaria = new ArrayList<Carro>();

	@Override
	public String toString() {
		return "Concessionaria [idConcessionaria=" + super.getId() + ", cnpj=" + cnpj + ", nome=" + nome + "]";
	}

	public String toCsv() {
		return "Nome: " + this.getNome() + ", CNPJ: " + this.getCnpj() + ", Id: " + super.getId();
	}

}
