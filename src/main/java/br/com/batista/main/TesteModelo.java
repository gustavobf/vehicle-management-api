package br.com.batista.main;

import br.com.batista.dao.ModeloDAO;
import br.com.batista.model.Modelo;
import br.com.batista.service.ModeloService;

public class TesteModelo {

	public static void main(String[] args) {

		ModeloService service = new ModeloService();

		Modelo m1 = new Modelo();

		m1.setNome("Sedan");

		Modelo m2 = new Modelo();

		m2.setIdModelo(0);
		m2.setNome("Hatch");

		Modelo m3 = new Modelo();

		m3.setIdModelo(1);
		m3.setNome("Sport");

		System.out.println("Lista de modelo antes do update: ");

		ModeloDAO.getAll().forEach(modelo -> {
			System.out.println(modelo.toString());
		});

		System.out.println("---------------------");

		System.out.println("Lista de modelo depois do update:");

		ModeloDAO.getAll().forEach(modelo -> {
			System.out.println(modelo.toString());
		});

		System.out.println("---------------------");

		System.out.println("Lista final de modelos: ");

		ModeloDAO.getAll().forEach(modelo -> {
			System.out.println(modelo.toString());
		});

	}

}
