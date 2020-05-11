package br.com.batista.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.batista.ConnectionFactory;
import br.com.batista.model.Marca;

public class MarcaDAO {

	public static void create(Marca marca) throws SQLException {

		String sql = "INSERT INTO marca(nome, pais) VALUES (?, ?)";

		try (PreparedStatement pstm = ConnectionFactory.getConnection().prepareStatement(sql)) {

			pstm.setString(1, marca.getNome());
			pstm.setString(2, marca.getPais());

			pstm.execute();

		}

	}

	public static Marca getById(int id) throws SQLException {

		String sql = "SELECT * FROM marca WHERE id = ?";

		try (PreparedStatement pstm = ConnectionFactory.getConnection().prepareStatement(sql)) {

			pstm.setInt(1, id);

			pstm.execute();

			try (ResultSet rst = pstm.getResultSet()) {
				while (rst.next()) {
					int idMarca = rst.getInt(1);
					if (idMarca == id) {
						Marca marca = new Marca(rst.getString(2), rst.getString(3));
						marca.setId(rst.getInt(1));
						return marca;
					} else {
						return null;
					}
				}
			}
		}
		return null;
	}

	public static void delete(int id) throws SQLException {

		String sql = "DELETE FROM marca WHERE id = ?";

		try (PreparedStatement pstm = ConnectionFactory.getConnection().prepareStatement(sql)) {

			pstm.setInt(1, id);

			pstm.execute();

		}
	}

	public static List<Marca> getAll() throws SQLException {

		List<Marca> marcas = new ArrayList<>();

		String sql = "SELECT * FROM marca";

		try (PreparedStatement pstm = ConnectionFactory.getConnection().prepareStatement(sql,
				Statement.RETURN_GENERATED_KEYS)) {

			pstm.execute();

			try (ResultSet rst = pstm.getResultSet()) {
				while (rst.next()) {
					Marca marca = new Marca(rst.getString(2), rst.getString(3));
					marca.setId(rst.getInt(1));
					marcas.add(marca);
				}
			}

		}
		return marcas;

	}

	public static void update(Marca marca) throws SQLException {

		String sql = "UPDATE marca SET nome = ?, pais = ? WHERE id = ?";

		try (PreparedStatement pstm = ConnectionFactory.getConnection().prepareStatement(sql)) {

			pstm.setString(1, marca.getNome());
			pstm.setString(2, marca.getPais());
			pstm.setInt(3, marca.getId());

			pstm.execute();
		}

	}

}
