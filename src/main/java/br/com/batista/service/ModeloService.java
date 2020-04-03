package br.com.batista.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.batista.dao.ModeloDAO;
import br.com.batista.model.Modelo;

@Service
public class ModeloService {

	public void saveModelo(Modelo modelo) {
		ModeloDAO.create(modelo);
	}

	public void updateModelo(Modelo modelo) {
		ModeloDAO.update(modelo);
	}

	public void deleteModelo(Modelo modelo) {
		ModeloDAO.delete(modelo.getIdModelo());
	}

	public void deleteModelo(int idModelo) {
		ModeloDAO.delete(idModelo);
	}

	public Modelo getById(int idModelo) {
		return ModeloDAO.getById(idModelo);
	}

	public List<Modelo> getAll() {
		return ModeloDAO.getAll();
	}

}
