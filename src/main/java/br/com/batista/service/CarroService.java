package br.com.batista.service;

import br.com.batista.dao.CarroDAO;
import br.com.batista.model.Carro;

public class CarroService {

	public static void saveCar(Carro carro) {
		CarroDAO.create(carro);
	}

	public static void updateCar(Carro carro) {
		CarroDAO.update(carro);
	}

	public static void deleteCar(Carro carro) {
		CarroDAO.delete(carro.getIdCarro());
	}

	public static void deleteCar(int idCarro) {
		CarroDAO.delete(idCarro);
	}

	public static void getById(int idCarro) {
		CarroDAO.getById(idCarro);
	}

	public static void getAll() {
		CarroDAO.getAll();
	}
}
