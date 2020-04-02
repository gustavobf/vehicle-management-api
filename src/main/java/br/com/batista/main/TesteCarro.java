package br.com.batista.main;

import br.com.batista.dao.CarroDAO;
import br.com.batista.model.Carro;
import br.com.batista.service.CarroService;

public class TesteCarro {

	public static void main(String[] args) {

		CarroService service = new CarroService();

		Carro c1 = new Carro();

		c1.setNome("Celta");
		c1.setAno(2005);
		c1.setPotencia(1000);
		c1.setCor("Vermelho");
		c1.setPortas(2);
		c1.setPlaca("SOJ-9437");

		Carro c2 = new Carro();

		c2.setNome("Fox");
		c2.setAno(2007);
		c2.setPotencia(1500);
		c2.setIdCarro(0);
		c2.setCor("Azul");
		c2.setPortas(4);
		c2.setPlaca("GJH-4363");

		Carro c3 = new Carro();

		c3.setNome("Gol");
		c3.setAno(2009);
		c3.setPotencia(1200);
		c3.setIdCarro(1);
		c3.setCor("Prata");
		c3.setPortas(2);
		c3.setPlaca("POS-9587");

		CarroService.saveCar(c1);
		CarroService.saveCar(c3);

		System.out.println("Lista de carros antes do update:");

		CarroDAO.getAll().forEach(carro -> {
			System.out.println(carro.toString());
		});

		System.out.println("---------------------");
		CarroService.updateCar(c2);

		System.out.println("Lista de carros depois do update:");
		CarroDAO.getAll().forEach(carro -> {
			System.out.println(carro.toString());
		});

		System.out.println("---------------------");

		System.out.println("Lista de final de carros: ");
		CarroDAO.getAll().forEach(carro -> {
			System.out.println(carro.toString());
		});

	}

}
