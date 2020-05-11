package br.com.batista.service;

import java.sql.SQLException;
import java.util.List;

import br.com.batista.dao.MarcaDAO;
import br.com.batista.model.Marca;

public class MarcaService {

	public void saveMarca(Marca marca) throws SQLException {
		MarcaDAO.create(marca);
	}

	public void updateMarca(Marca marca) throws SQLException {
		MarcaDAO.update(marca);
	}

	public void deleteMarca(Marca marca) throws SQLException {
		MarcaDAO.delete(marca.getId());
	}

	public void deleteMarca(int id) throws SQLException {
		MarcaDAO.delete(id);
	}

	public Marca getById(int id) throws SQLException {
		return MarcaDAO.getById(id);
	}

	public List<Marca> getAll() throws SQLException {
		return MarcaDAO.getAll();
	}

}
