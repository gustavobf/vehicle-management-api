package br.com.batista.service;

import br.com.batista.dao.ConcessionariaDAO;
import br.com.batista.model.Concessionaria;

public class ConcessionariaService {

	public static void saveConcessionaria(Concessionaria concessionaria) {
		ConcessionariaDAO.create(concessionaria);
	}

	public static void updateConcessionaria(Concessionaria concessionaria) {
		ConcessionariaDAO.update(concessionaria);
	}

	public static void deleteConcessionaria(Concessionaria concessionaria) {
		ConcessionariaDAO.delete(concessionaria.getIdConcessionaria());
	}

	public static void deleteConcessionaria(int idConcessionaria) {
		ConcessionariaDAO.delete(idConcessionaria);
	}

	public static void getById(int idConcessionaria) {
		ConcessionariaDAO.getById(idConcessionaria);
	}

	public static void getAll() {
		ConcessionariaDAO.getAll();
	}
}
