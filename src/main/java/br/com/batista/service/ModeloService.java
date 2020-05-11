package br.com.batista.service;

import java.sql.SQLException;
import java.util.List;

import br.com.batista.dao.ModeloDAO;
import br.com.batista.model.Modelo;

public class ModeloService {

	public void saveModelo(Modelo modelo) throws SQLException {
		ModeloDAO.create(modelo);
	}

	public void updateModelo(Modelo modelo) throws SQLException {
		ModeloDAO.update(modelo);
	}

	public void deleteModelo(Modelo modelo) throws SQLException {
		ModeloDAO.delete(modelo.getId());
	}

	public void deleteModelo(int id) throws SQLException {
		ModeloDAO.delete(id);
	}

	public Modelo getById(int id) throws SQLException {
		return ModeloDAO.getById(id);
	}

	public List<Modelo> getAll() throws SQLException {
		return ModeloDAO.getAll();
	}

}
