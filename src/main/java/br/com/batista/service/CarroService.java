package br.com.batista.service;

import java.util.List;

import br.com.batista.dao.CarroDAO;
import br.com.batista.dao.ConcessionariaDAO;
import br.com.batista.dao.MarcaDAO;
import br.com.batista.dao.ModeloDAO;
import br.com.batista.model.Carro;

public class CarroService {

	public void saveCar(Carro carro, int idConcessionaria, int idMarca, int idModelo) {

		carro.setIdConcessionaria_fk(ConcessionariaDAO.getById(idConcessionaria));
		carro.setIdMarca_fk(MarcaDAO.getById(idMarca));
		carro.setIdModelo_fk(ModeloDAO.getById(idModelo));

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
