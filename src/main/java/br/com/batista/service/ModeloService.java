package br.com.batista.service;

import br.com.batista.dao.ModeloDAO;
import br.com.batista.model.Modelo;

public class ModeloService {

	public static void saveModelo(Modelo modelo) {
		ModeloDAO.create(modelo);
	}

	public static void updateModelo(Modelo modelo) {
		ModeloDAO.update(modelo);
	}

	public static void deleteModelo(Modelo modelo) {
		ModeloDAO.delete(modelo.getIdModelo());
	}

	public static void deleteModelo(int idModelo) {
		ModeloDAO.delete(idModelo);
	}

	public static void getById(int idModelo) {
		ModeloDAO.getById(idModelo);
	}

	public static void getAll() {
		ModeloDAO.getAll();
	}

}
