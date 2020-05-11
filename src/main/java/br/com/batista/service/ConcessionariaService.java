package br.com.batista.service;

import java.sql.SQLException;
import java.util.List;

import br.com.batista.dao.ConcessionariaDAO;
import br.com.batista.model.Concessionaria;

public class ConcessionariaService {

	public void saveConcessionaria(Concessionaria concessionaria) throws SQLException {
		ConcessionariaDAO.create(concessionaria);
	}

	public void updateConcessionaria(Concessionaria concessionaria) throws SQLException {
		ConcessionariaDAO.update(concessionaria);
	}

	public void deleteConcessionaria(Concessionaria concessionaria) throws SQLException {
		ConcessionariaDAO.delete(concessionaria.getId());
	}

	public void deleteConcessionaria(int idConcessionaria) throws SQLException {
		ConcessionariaDAO.delete(idConcessionaria);
	}

	public Concessionaria getById(int idConcessionaria) throws SQLException {
		return ConcessionariaDAO.getById(idConcessionaria);
	}

	public List<Concessionaria> getAll() throws SQLException {
		return ConcessionariaDAO.getAll();
	}
}
