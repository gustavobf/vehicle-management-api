package br.com.batista.model;

import java.util.ArrayList;
import java.util.List;

public class Concessionaria {

	private int idConcessionaria;
	private String cnpj;
	private String nome;

	public int getIdConcessionaria() {
		return idConcessionaria;
	}

	public void setIdConcessionaria(int idConcessionaria) {
		this.idConcessionaria = idConcessionaria;
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
		return "Concessionaria [idConcessionaria=" + idConcessionaria + ", cnpj=" + cnpj + ", nome=" + nome + "]";
	}

}
