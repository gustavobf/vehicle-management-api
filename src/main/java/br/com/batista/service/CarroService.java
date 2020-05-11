package br.com.batista.service;

import java.sql.SQLException;
import java.util.List;

import br.com.batista.dao.CarroDAO;
import br.com.batista.model.Carro;

public class CarroService {

	public void saveCar(Carro carro) throws SQLException {
		CarroDAO.create(carro);

	}

	public void updateCar(Carro carro) throws SQLException {
		CarroDAO.update(carro);
	}

	public void deleteCar(Carro carro) throws SQLException {
		CarroDAO.delete(carro.getId());
	}

	public void deleteCar(int idCarro) throws SQLException {
		CarroDAO.delete(idCarro);
	}

	public Carro getById(int idCarro) throws SQLException {
		return CarroDAO.getById(idCarro);
	}

	public List<Carro> getAll() throws SQLException {
		return CarroDAO.getAll();
	}
}
