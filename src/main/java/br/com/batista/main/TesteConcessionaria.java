package br.com.batista.main;

import java.sql.SQLException;

import br.com.batista.model.Concessionaria;
import br.com.batista.service.ConcessionariaService;

public class TesteConcessionaria {

	public static void main(String[] args) throws SQLException {

		ConcessionariaService service = new ConcessionariaService();

		// Criando Concessionarias
		Concessionaria c1 = new Concessionaria("Volks", "0260522660");

		Concessionaria c2 = new Concessionaria("Dinamo", "1260722660");

		Concessionaria c3 = new Concessionaria("Caer", "7610122669");

		service.saveConcessionaria(c1);
		service.saveConcessionaria(c2);
		service.saveConcessionaria(c3);

		System.out.println("--------------------------------------------");
		System.out.println("Lista de concessionarias antes do delete:");
		service.getAll().forEach(concessionaria -> {
			System.out.println(concessionaria.toString());
		});
		System.out.println("--------------------------------------------");

		// Deletando Concessionaria
		service.deleteConcessionaria(56);

		// Listando Concessionaria apos o delete
		System.out.println("--------------------------------------------");
		System.out.println("Lista de concessionarias depois do delete:");
		service.getAll().forEach(concessionaria -> {
			System.out.println(concessionaria.toString());
		});
		System.out.println("--------------------------------------------");

		// Alterando Concessionaria
		service.updateConcessionaria(c1);

		// Listando Concessionaria apos o update
		System.out.println("--------------------------------------------");
		System.out.println("Lista de concessionaria depois do update:");
		service.getAll().forEach(concessionaria -> {
			System.out.println(concessionaria.toString());
		});
		System.out.println("--------------------------------------------");

		// Lista final de Cocessionarias
		System.out.println("Lista final de concessionarias:");
		service.getAll().forEach(concessionaria -> {
			System.out.println(concessionaria.toString());
		});

	}

}
