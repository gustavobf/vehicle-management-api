package br.com.batista.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.batista.model.Concessionaria;

public class ConcessionariaDAO {

	private static List<Concessionaria> CONCESSIONARIAREPO = new ArrayList<Concessionaria>();
	private static int contadorConcessionaria = 0;

	public static void create(Concessionaria concessionaria) {
		concessionaria.setIdConcessionaria(contadorConcessionaria);
		CONCESSIONARIAREPO.add(concessionaria);
		contadorConcessionaria++;
	}

	public static Concessionaria getById(int idConcesssionaria) {
		for (Concessionaria concessionaria : CONCESSIONARIAREPO) {
			if (idConcesssionaria == concessionaria.getIdConcessionaria()) {
				return concessionaria;
			}
		}
		return null;
	}

	public static void delete(int idConcessionaria) {
		try {
			for (Concessionaria concessionaria : CONCESSIONARIAREPO) {
				if (idConcessionaria == concessionaria.getIdConcessionaria()) {
					CONCESSIONARIAREPO.remove(getById(idConcessionaria));
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static List<Concessionaria> getAll() {
		return CONCESSIONARIAREPO;
	}

	public static void update(Concessionaria concessionaria) {
		Concessionaria concessionariaAntiga = ConcessionariaDAO.getById(concessionaria.getIdConcessionaria());
		CONCESSIONARIAREPO.remove(concessionariaAntiga);
		CONCESSIONARIAREPO.add(concessionaria);
	}

}
