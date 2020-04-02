package br.com.batista.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.batista.model.Carro;

public class CarroDAO {

	private static List<Carro> CARROSREPO = new ArrayList<Carro>();
	private static int contadorCarro = 0;

	public static void create(Carro carro) {
		carro.setIdCarro(contadorCarro);
		CARROSREPO.add(carro);
		contadorCarro++;
	}

	public static Carro getById(int idCarro) {
		for (Carro carro : CARROSREPO) {
			if (idCarro == carro.getIdCarro()) {
				return carro;
			}
		}
		return null;
	}

	public static void delete(int idCarro) {
		try {
			for (Carro carro : CARROSREPO) {
				if (idCarro == carro.getIdCarro()) {
					CARROSREPO.remove(getById(idCarro));
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static List<Carro> getAll() {
		return CARROSREPO;
	}

	public static void update(Carro carro) {
		Carro carroAntigo = CarroDAO.getById(carro.getIdCarro());
		CARROSREPO.remove(carroAntigo);
		CARROSREPO.add(carro);
	}

}
