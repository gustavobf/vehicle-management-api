package br.com.batista.main;

import br.com.batista.dao.CarroDAO;
import br.com.batista.model.Carro;
import br.com.batista.model.Concessionaria;
import br.com.batista.model.Marca;
import br.com.batista.model.Modelo;
import br.com.batista.service.CarroService;
import br.com.batista.service.ConcessionariaService;
import br.com.batista.service.MarcaService;
import br.com.batista.service.ModeloService;

public class TesteCarro {

	public static void main(String[] args) {

		// Criando Carros
		CarroService carService = new CarroService();
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

		// Criando Concessionarias
		ConcessionariaService conService = new ConcessionariaService();
		Concessionaria con1 = new Concessionaria();
		con1.setNome("Volks");
		con1.setCnpj("0260522660");
		con1.setIdConcessionaria(0);

		Concessionaria con2 = new Concessionaria();
		con2.setNome("Dinamo");
		con2.setCnpj("1260722660");
		con2.setIdConcessionaria(2);

		Concessionaria con3 = new Concessionaria();
		con3.setNome("Caer");
		con3.setCnpj("7610122669");
		con3.setIdConcessionaria(3);

		// Criando Marcas
		MarcaService marcaService = new MarcaService();
		Marca m1 = new Marca();
		m1.setNome("Ford");
		m1.setPais("Roma");
		m1.setIdMarca(0);

		Marca m2 = new Marca();
		m2.setNome("GM");
		m2.setPais("EUA");
		m2.setIdMarca(1);

		Marca m3 = new Marca();
		m3.setNome("Fiat");
		m3.setPais("Grecia");
		m3.setIdMarca(2);

		// Criando Modelos
		ModeloService modeloService = new ModeloService();
		Modelo mode1 = new Modelo();
		mode1.setNome("Sedan");
		mode1.setIdModelo(0);

		Modelo mode2 = new Modelo();
		mode2.setNome("Hatch");
		mode2.setIdModelo(1);

		Modelo mode3 = new Modelo();
		mode3.setNome("Sport");
		mode3.setIdModelo(2);

		// Salvando Concessionarias
		conService.saveConcessionaria(con1);
		conService.saveConcessionaria(con2);
		conService.saveConcessionaria(con3);

		// Salvando Marcas
		marcaService.saveMarca(m1);
		marcaService.saveMarca(m2);
		marcaService.saveMarca(m3);

		// Salvando Modelos
		modeloService.saveModelo(mode1);
		modeloService.saveModelo(mode2);
		modeloService.saveModelo(mode3);

		// Salvando Carros
		carService.saveCar(c1, 0, 2, 2);
		carService.saveCar(c2, 1, 1, 0);
		carService.saveCar(c3, 2, 0, 1);

		// Listando Carros
		System.out.println("Lista de carros:");
		CarroDAO.getAll().forEach(carro -> {
			System.out.println(carro.toString());
		});

	}

}
