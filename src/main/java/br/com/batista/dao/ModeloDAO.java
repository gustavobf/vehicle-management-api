package br.com.batista.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.batista.ConnectionFactory;
import br.com.batista.model.Modelo;

public class ModeloDAO {
	public static void create(Modelo modelo) throws SQLException {

		String sql = "INSERT INTO modelo(nome) VALUES(?);";

		try (PreparedStatement pstm = ConnectionFactory.getConnection().prepareStatement(sql,
				Statement.RETURN_GENERATED_KEYS)) {

			pstm.setString(1, modelo.getNome());
			pstm.execute();

			try (ResultSet rst = pstm.getGeneratedKeys()) {
				if (rst.next()) {
					modelo.setId(rst.getInt(1));
				}

			}

		}

	}

	public static Modelo getById(int id) throws SQLException {

		String sql = "SELECT * FROM modelo WHERE id = ?";

		try (PreparedStatement pstm = ConnectionFactory.getConnection().prepareStatement(sql)) {

			pstm.setInt(1, id);

			pstm.execute();

			try (ResultSet rst = pstm.getResultSet()) {
				while (rst.next()) {
					Integer idModelo = rst.getInt(1);
					if (idModelo == id) {
						Modelo modelo = new Modelo(rst.getString(2));
						modelo.setId(id);
						return modelo;
					} else {
						return null;
					}
				}
			}
		}
		return null;

	}

	public static void delete(int id) throws SQLException {

		String sql = "DELETE FROM modelo WHERE id  = ?";

		try (PreparedStatement pstm = ConnectionFactory.getConnection().prepareStatement(sql)) {

			pstm.setInt(1, id);

			pstm.execute();

		}

	}

	public static List<Modelo> getAll() throws SQLException {

		List<Modelo> modelos = new ArrayList<>();

		String sql = "SELECT * FROM modelo";

		try (PreparedStatement pstm = ConnectionFactory.getConnection().prepareStatement(sql,
				Statement.RETURN_GENERATED_KEYS)) {

			pstm.execute();

			try (ResultSet rst = pstm.getResultSet()) {
				while (rst.next()) {
					Modelo modelo = new Modelo(rst.getString(2));
					modelo.setId(rst.getInt(1));
					modelos.add(modelo);

				}
			}

		}
		return modelos;

	}

	public static void update(Modelo modelo) throws SQLException {

		String sql = "UPDATE modelo SET nome = ? WHERE id = ?";

		try (PreparedStatement pstm = ConnectionFactory.getConnection().prepareStatement(sql)) {

			pstm.setString(1, modelo.getNome());
			pstm.setInt(2, modelo.getId());

			pstm.execute();

		}

	}
}
