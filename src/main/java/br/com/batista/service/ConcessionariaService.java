package br.com.batista.service;

import java.util.List;

import br.com.batista.dao.ConcessionariaDAO;
import br.com.batista.model.Concessionaria;

public class ConcessionariaService {

	public void saveConcessionaria(Concessionaria concessionaria) {
		ConcessionariaDAO.create(concessionaria);
	}

	public void updateConcessionaria(Concessionaria concessionaria) {
		ConcessionariaDAO.update(concessionaria);
	}

	public void deleteConcessionaria(Concessionaria concessionaria) {
		ConcessionariaDAO.delete(concessionaria.getIdConcessionaria());
	}

	public void deleteConcessionaria(int idConcessionaria) {
		ConcessionariaDAO.delete(idConcessionaria);
	}

	public Concessionaria getById(int idConcessionaria) {
		return ConcessionariaDAO.getById(idConcessionaria);
	}

	public List<Concessionaria> getAll() {
		return ConcessionariaDAO.getAll();
	}
}
