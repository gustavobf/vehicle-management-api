package br.com.batista.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.batista.dao.CarroDAO;
import br.com.batista.model.Carro;

@Service
public class CarroService {

	public void saveCar(Carro carro) {
		CarroDAO.create(carro);
	}

	public void updateCar(Carro carro) {
		CarroDAO.update(carro);
	}

	public void deleteCar(Carro carro) {
		CarroDAO.delete(carro.getIdCarro());
	}

	public void deleteCar(int idCarro) {
		CarroDAO.delete(idCarro);
	}

	public Carro getById(int idCarro) {
		return CarroDAO.getById(idCarro);
	}

	public List<Carro> getAll() {
		return CarroDAO.getAll();
	}
}
