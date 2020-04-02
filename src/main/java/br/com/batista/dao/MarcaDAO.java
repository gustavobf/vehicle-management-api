package br.com.batista.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.batista.model.Marca;

public class MarcaDAO {

	private static List<Marca> MARCAREPO = new ArrayList<Marca>();
	private static int contadorMarca = 0;

	public static void create(Marca marca) {
		marca.setIdMarca(contadorMarca);
		MARCAREPO.add(marca);
		contadorMarca++;
	}

	public static Marca getById(int idMarca) {
		for (Marca marca : MARCAREPO) {
			if (idMarca == marca.getIdMarca()) {
				return marca;
			}
		}
		return null;
	}

	public static void delete(int idMarca) {
		try {
			for (Marca marca : MARCAREPO) {
				if (idMarca == marca.getIdMarca()) {
					MARCAREPO.remove(getById(idMarca));
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	public static List<Marca> getAll() {
		return MARCAREPO;
	}

	public static void update(Marca marca) {
		Marca marcaAntiga = MarcaDAO.getById(marca.getIdMarca());
		MARCAREPO.remove(marcaAntiga);
		MARCAREPO.add(marca);
	}

}
