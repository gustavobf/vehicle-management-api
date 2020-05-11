package br.com.batista.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.batista.ConnectionFactory;
import br.com.batista.model.Concessionaria;

public class ConcessionariaDAO {

	public static void create(Concessionaria concessionaria) throws SQLException {

		String sql = "INSERT INTO concessionaria(cnpj, nome) VALUES(?, ?);";

		try (PreparedStatement pstm = ConnectionFactory.getConnection().prepareStatement(sql,
				Statement.RETURN_GENERATED_KEYS)) {

			pstm.setString(1, concessionaria.getCnpj());
			pstm.setString(2, concessionaria.getNome());
			pstm.execute();

			try (ResultSet rst = pstm.getGeneratedKeys()) {
				if (rst.next()) {
					concessionaria.setId(rst.getInt(1));
				}

			}

		}

	}

	public static Concessionaria getById(int id) throws SQLException {

		String sql = "SELECT * FROM concessionaria WHERE id = ?";

		try (PreparedStatement pstm = ConnectionFactory.getConnection().prepareStatement(sql)) {

			pstm.setInt(1, id);

			pstm.execute();

			try (ResultSet rst = pstm.getResultSet()) {
				while (rst.next()) {
					Integer idConcessionaria = rst.getInt(1);
					if (idConcessionaria == id) {
						Concessionaria concessionaria = new Concessionaria(rst.getString(2), rst.getString(3));
						concessionaria.setId(id);
						return concessionaria;
					} else {
						return null;
					}
				}
			}
		}
		return null;

	}

	public static void delete(int id) throws SQLException {

		String sql = "DELETE FROM concessionaria WHERE id  = ?";

		try (PreparedStatement pstm = ConnectionFactory.getConnection().prepareStatement(sql)) {

			pstm.setInt(1, id);

			pstm.execute();

		}

	}

	public static List<Concessionaria> getAll() throws SQLException {

		List<Concessionaria> concessionarias = new ArrayList<>();

		String sql = "SELECT * FROM concessionaria";

		try (PreparedStatement pstm = ConnectionFactory.getConnection().prepareStatement(sql,
				Statement.RETURN_GENERATED_KEYS)) {

			pstm.execute();

			try (ResultSet rst = pstm.getResultSet()) {
				while (rst.next()) {
					Concessionaria concessionaria = new Concessionaria(rst.getString(2), rst.getString(3));
					concessionaria.setId(rst.getInt(1));
					concessionarias.add(concessionaria);

				}
			}

		}
		return concessionarias;

	}

	public static void update(Concessionaria concessionaria) throws SQLException {

		String sql = "UPDATE concessionaria SET cnpj = ?, nome = ? WHERE id = ?";

		try (PreparedStatement pstm = ConnectionFactory.getConnection().prepareStatement(sql)) {

			pstm.setString(1, concessionaria.getCnpj());
			pstm.setString(2, concessionaria.getNome());
			pstm.setInt(3, concessionaria.getId());

			pstm.execute();

		}

	}

}
