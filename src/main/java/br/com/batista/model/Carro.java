package br.com.batista.model;

public class Carro extends Entidade {

	private Modelo idModelo_fk;
	private Marca idMarca_fk;
	private Concessionaria idConcessionaria_fk;
	private String cor;
	private int potencia;
	private int portas;
	private int ano;
	private String placa;
	private String nome;

	public Carro(String cor, int potencia, int portas, int ano, String placa, String nome) {
		this.cor = cor;
		this.potencia = potencia;
		this.portas = portas;
		this.ano = ano;
		this.placa = placa;
		this.nome = nome;
	}

	public Carro(String cor, int potencia, int portas, int ano, String nome) {
		this.cor = cor;
		this.potencia = potencia;
		this.portas = portas;
		this.ano = ano;
		this.nome = nome;
	}

	public Modelo getIdModelo_fk() {
		return idModelo_fk;
	}

	public void setIdModelo_fk(Modelo idModelo_fk) {
		this.idModelo_fk = idModelo_fk;
	}

	public Marca getIdMarca_fk() {
		return idMarca_fk;
	}

	public void setIdMarca_fk(Marca idMarca_fk) {
		this.idMarca_fk = idMarca_fk;
	}

	public Concessionaria getIdConcessionaria_fk() {
		return idConcessionaria_fk;
	}

	public void setIdConcessionaria_fk(Concessionaria idConcessionaria_fk) {
		this.idConcessionaria_fk = idConcessionaria_fk;
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
		return "this [idthis=" + super.getId() + ", idModelo_fk=" + idModelo_fk + ", idMarca_fk=" + idMarca_fk
				+ ", idConcessionaria_fk=" + idConcessionaria_fk + ", cor=" + cor + ", potencia=" + potencia
				+ ", portas=" + portas + ", ano=" + ano + ", placa=" + placa + ", nome=" + nome + "]";
	}

	public String toCsv() {
		return "Nome: " + this.getNome() + ", Cor: " + this.getCor() + ", Placa: " + this.getPlaca() + ", qtdPortas: "
				+ this.getPortas() + ", Potencia: " + this.getPotencia() + ", Ano: " + this.getAno() + ", Id: "
				+ super.getId();
	}
}
