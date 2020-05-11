package br.com.batista.main;

import java.sql.SQLException;

import br.com.batista.model.Marca;
import br.com.batista.service.MarcaService;

public class TesteMarca {

	public static void main(String[] args) throws SQLException {

		MarcaService service = new MarcaService();

		// Criando Marcas
		Marca m1 = new Marca("Ford", "Roma");

		Marca m2 = new Marca("GM", "EUA");

		Marca m3 = new Marca("Fiat", "Grecia");

		service.saveMarca(m1);
		service.saveMarca(m2);
		service.saveMarca(m3);

		System.out.println("--------------------------------------------");
		System.out.println("Lista de marcas antes do delete:");
		service.getAll().forEach(marca -> {
			System.out.println(marca.toString());
		});
		System.out.println("--------------------------------------------");

		// Deletando Marca
		service.deleteMarca(56);

		// Listando Marca apos o delete
		System.out.println("--------------------------------------------");
		System.out.println("Lista de marcas depois do delete:");
		service.getAll().forEach(marca -> {
			System.out.println(marca.toString());
		});
		System.out.println("--------------------------------------------");

		// Alterando Marca
		service.updateMarca(m1);

		// Listando Marca apos o update
		System.out.println("--------------------------------------------");
		System.out.println("Lista de marca depois do update:");
		service.getAll().forEach(marca -> {
			System.out.println(marca.toString());
		});
		System.out.println("--------------------------------------------");

		// Lista final de Marca
		System.out.println("Lista final de marcas:");
		service.getAll().forEach(marca -> {
			System.out.println(marca.toString());
		});

	}
}
