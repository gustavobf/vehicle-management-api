package br.com.batista.service;

import br.com.batista.dao.MarcaDAO;
import br.com.batista.model.Marca;

public class MarcaService {

	public static void saveMarca(Marca marca) {
		MarcaDAO.create(marca);
	}

	public static void updateMarca(Marca marca) {
		MarcaDAO.update(marca);
	}

	public static void deleteMarca(Marca marca) {
		MarcaDAO.delete(marca.getIdMarca());
	}

	public static void deleteMarca(int idMarca) {
		MarcaDAO.delete(idMarca);
	}

	public static void getById(int idMarca) {
		MarcaDAO.getById(idMarca);
	}

	public static void getAll() {
		MarcaDAO.getAll();
	}

}
