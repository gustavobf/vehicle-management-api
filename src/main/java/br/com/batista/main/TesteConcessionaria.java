package br.com.batista.main;

import br.com.batista.model.Concessionaria;
import br.com.batista.service.ConcessionariaService;

public class TesteConcessionaria {

	public static void main(String[] args) {

		ConcessionariaService service = new ConcessionariaService();

		// Criando Concessionarias
		Concessionaria c1 = new Concessionaria();
		c1.setNome("Volks");
		c1.setCnpj("0260522660");

		Concessionaria c2 = new Concessionaria();
		c2.setNome("Dinamo");
		c2.setCnpj("1260722660");
		c2.setIdConcessionaria(0);

		Concessionaria c3 = new Concessionaria();
		c3.setNome("Caer");
		c3.setCnpj("7610122669");
		c2.setIdConcessionaria(1);

		service.saveConcessionaria(c1);
		service.saveConcessionaria(c3);

		System.out.println("Lista de concessionarias antes do update:");
		service.getAll().forEach(concessionaria -> {
			System.out.println(concessionaria.toString());
		});

		service.updateConcessionaria(c2);

		System.out.println("---------------------");
		System.out.println("Lista de concessionaria depois do update:");
		service.getAll().forEach(concessionaria -> {
			System.out.println(concessionaria.toString());
		});
		System.out.println("---------------------");

		System.out.println("Lista final de concessionarias:");
		service.getAll().forEach(concessionaria -> {
			System.out.println(concessionaria.toString());
		});

	}

}
