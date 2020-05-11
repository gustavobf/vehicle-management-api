package br.com.batista.main;

import java.sql.SQLException;

import br.com.batista.model.Modelo;
import br.com.batista.service.ModeloService;

public class TesteModelo {

	public static void main(String[] args) throws SQLException {

		ModeloService service = new ModeloService();

		// Criando Modelos
		Modelo m1 = new Modelo("Sedan");

		Modelo m2 = new Modelo("Hatch");

		Modelo m3 = new Modelo("Sport");

		service.saveModelo(m1);
		service.saveModelo(m2);
		service.saveModelo(m3);

		System.out.println("--------------------------------------------");
		System.out.println("Lista de modelos antes do delete:");
		service.getAll().forEach(modelo -> {
			System.out.println(modelo.toString());
		});
		System.out.println("--------------------------------------------");

		// Deletando Modelo
		service.deleteModelo(5);

		// Listando Modelo apos o delete
		System.out.println("--------------------------------------------");
		System.out.println("Lista de modelos depois do delete:");
		service.getAll().forEach(modelo -> {
			System.out.println(modelo.toString());
		});
		System.out.println("--------------------------------------------");

		// Alterando Modelo
		service.updateModelo(m1);

		// Listando Modelo apos o update
		System.out.println("--------------------------------------------");
		System.out.println("Lista de modelo depois do update:");
		service.getAll().forEach(modelo -> {
			System.out.println(modelo.toString());
		});
		System.out.println("--------------------------------------------");

		// Lista final de Cocessionarias
		System.out.println("Lista final de modelos:");
		service.getAll().forEach(modelo -> {
			System.out.println(modelo.toString());
		});

	}

}