package br.com.batista.main;

import br.com.batista.model.Marca;
import br.com.batista.service.MarcaService;

public class TesteMarca {

	public static void main(String[] args) {

		MarcaService service = new MarcaService();

		// Criando Marcas
		Marca m1 = new Marca();
		m1.setNome("Ford");
		m1.setPais("Roma");

		Marca m2 = new Marca();
		m2.setIdMarca(0);
		m2.setNome("GM");
		m2.setPais("EUA");

		Marca m3 = new Marca();
		m3.setIdMarca(1);
		m3.setNome("Fiat");
		m3.setPais("Grecia");

		service.saveMarca(m1);
		service.saveMarca(m3);

		System.out.println("Lista de marcas antes do update:");
		service.getAll().forEach(marca -> {
			System.out.println(marca.toString());
		});

		service.updateMarca(m2);

		System.out.println("---------------------");
		System.out.println("Lista de marca depois do update: ");
		service.getAll().forEach(marca -> {
			System.out.println(marca.toString());
		});
		System.out.println("---------------------");

		System.out.println("Lista final de marcas:");
		service.getAll().forEach(marca -> {
			System.out.println(marca.toString());
		});

	}

}
