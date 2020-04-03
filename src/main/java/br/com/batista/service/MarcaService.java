package br.com.batista.service;

import java.util.List;

import br.com.batista.dao.MarcaDAO;
import br.com.batista.model.Marca;

public class MarcaService {

	public void saveMarca(Marca marca) {
		MarcaDAO.create(marca);
	}

	public void updateMarca(Marca marca) {
		MarcaDAO.update(marca);
	}

	public void deleteMarca(Marca marca) {
		MarcaDAO.delete(marca.getIdMarca());
	}

	public void deleteMarca(int idMarca) {
		MarcaDAO.delete(idMarca);
	}

	public Marca getById(int idMarca) {
		return MarcaDAO.getById(idMarca);
	}

	public List<Marca> getAll() {
		return MarcaDAO.getAll();
	}

}
