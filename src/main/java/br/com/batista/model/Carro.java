package br.com.batista.model;

public class Carro {

	private int idCarro;
	private int idModelo_fk;
	private int idMarca_fk;
	private String cor;
	private int potencia;
	private int portas;
	private int ano;
	private String placa;
	private String nome;

	public int getIdCarro() {
		return idCarro;
	}

	public void setIdCarro(int idCarro) {
		this.idCarro = idCarro;
	}

	public int getIdModelo_fk() {
		return idModelo_fk;
	}

	public void setIdModelo_fk(int idModelo_fk) {
		this.idModelo_fk = idModelo_fk;
	}

	public int getIdMarca_fk() {
		return idMarca_fk;
	}

	public void setIdMarca_fk(int idMarca_fk) {
		this.idMarca_fk = idMarca_fk;
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

	@Override
	public String toString() {
		return "Carro [idCarro=" + idCarro + ", idModelo_fk=" + idModelo_fk + ", idMarca_fk=" + idMarca_fk + ", cor="
				+ cor + ", potencia=" + potencia + ", portas=" + portas + ", ano=" + ano + ", placa=" + placa
				+ ", nome=" + nome + "]";
	}
	
	

}
