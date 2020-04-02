package br.com.batista.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.batista.model.Modelo;

public class ModeloDAO {

	private static List<Modelo> MODELOREPO = new ArrayList<Modelo>();
	private static int contadorModelo = 0;

	public static void create(Modelo modelo) {
		modelo.setIdModelo(contadorModelo);
		MODELOREPO.add(modelo);
		contadorModelo++;
	}

	public static Modelo getById(int idModelo) {
		for (Modelo modelo : MODELOREPO) {
			if (idModelo == modelo.getIdModelo()) {
				return modelo;
			}
		}
		return null;
	}

	public static void delete(int idModelo) {
		try {
			for (Modelo modelo : MODELOREPO) {
				if (idModelo == modelo.getIdModelo()) {
					MODELOREPO.remove(getById(idModelo));
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static List<Modelo> getAll() {
		return MODELOREPO;
	}

	public static void update(Modelo modelo) {
		Modelo modeloAntigo = ModeloDAO.getById(modelo.getIdModelo());
		MODELOREPO.remove(modeloAntigo);
		MODELOREPO.add(modelo);
	}
}
