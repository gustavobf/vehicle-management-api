package br.com.batista.main;

import java.sql.SQLException;

import br.com.batista.model.Carro;
import br.com.batista.service.CarroService;

public class TesteCarro {

	public static void main(String[] args) throws SQLException {

		CarroService carService = new CarroService();

		// Criando Carros
		Carro c1 = new Carro("Vermelho", 1000, 2, 2005, "SOJ-9437V", "Celta");

		Carro c2 = new Carro("Azul", 1500, 4, 2007, "GJH-4363", "Fox");

		Carro c3 = new Carro("Prata", 1200, 2, 2009, "POS-9587", "Gol");

		carService.saveCar(c1);
		carService.saveCar(c2);
		carService.saveCar(c3);

		System.out.println("--------------------------------------------");
		System.out.println("Lista de carros antes do delete:");
		carService.getAll().forEach(carro -> {
			System.out.println(carro.toString());
		});
		System.out.println("--------------------------------------------");

		// Deletando Carro
		carService.deleteCar(5);

		// Listando Carro apos o delete
		System.out.println("--------------------------------------------");
		System.out.println("Lista de carros depois do delete:");
		carService.getAll().forEach(carro -> {
			System.out.println(carro.toString());
		});
		System.out.println("--------------------------------------------");

		// Alterando Carro
		Carro ca1 = new Carro("preto", 5000, 2, 1990, "XDevil", "laukuro");
		ca1.setId(4);
		carService.updateCar(ca1);

		// Listando Carro apos o update
		System.out.println("--------------------------------------------");
		System.out.println("Lista de carro depois do update:");
		carService.getAll().forEach(carro -> {
			System.out.println(carro.toString());
		});
		System.out.println("--------------------------------------------");

		// Lista final de Cocessionarias
		System.out.println("Lista final de carros:");
		carService.getAll().forEach(carro -> {
			System.out.println(carro.toString());
		});

	}

}
