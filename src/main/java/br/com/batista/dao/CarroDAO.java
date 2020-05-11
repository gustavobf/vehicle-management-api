package br.com.batista.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.batista.ConnectionFactory;
import br.com.batista.model.Carro;

public class CarroDAO {

	public static void create(Carro carro) throws SQLException {

		String sql = "INSERT INTO carro(cor, potencia, portas, ano, placa, nome) VALUES(?, ?, ?, ?, ?, ?);";

		try (PreparedStatement pstm = ConnectionFactory.getConnection().prepareStatement(sql,
				Statement.RETURN_GENERATED_KEYS)) {

			pstm.setString(1, carro.getCor());
			pstm.setInt(2, carro.getPotencia());
			pstm.setInt(3, carro.getPortas());
			pstm.setInt(4, carro.getAno());
			pstm.setString(5, carro.getPlaca());
			pstm.setString(6, carro.getNome());
			pstm.execute();

			try (ResultSet rst = pstm.getGeneratedKeys()) {
				if (rst.next()) {
					carro.setId(rst.getInt(1));
				}

			}

		}

	}

	public static Carro getById(int id) throws SQLException {

		String sql = "SELECT * FROM carro WHERE id = ?";

		try (PreparedStatement pstm = ConnectionFactory.getConnection().prepareStatement(sql)) {

			pstm.setInt(1, id);

			pstm.execute();

			try (ResultSet rst = pstm.getResultSet()) {
				while (rst.next()) {
					Integer idCarro = rst.getInt(1);
					if (idCarro == id) {
						Carro carro = new Carro(rst.getString(4), rst.getInt(5), rst.getInt(6), rst.getInt(7),
								rst.getString(8), rst.getString(9));
						carro.setId(id);
						return carro;
					} else {
						return null;
					}
				}
			}
		}
		return null;

	}

	public static void delete(int id) throws SQLException {

		String sql = "DELETE FROM carro WHERE id  = ?";

		try (PreparedStatement pstm = ConnectionFactory.getConnection().prepareStatement(sql)) {

			pstm.setInt(1, id);

			pstm.execute();

		}

	}

	public static List<Carro> getAll() throws SQLException {

		List<Carro> carros = new ArrayList<>();

		String sql = "SELECT * FROM carro";

		try (PreparedStatement pstm = ConnectionFactory.getConnection().prepareStatement(sql,
				Statement.RETURN_GENERATED_KEYS)) {

			pstm.execute();

			try (ResultSet rst = pstm.getResultSet()) {
				while (rst.next()) {
					Carro carro = new Carro(rst.getString(5), rst.getInt(6), rst.getInt(7), rst.getInt(8),
							rst.getString(9), rst.getString(10));
					carro.setId(rst.getInt(1));
					carros.add(carro);

				}
			}

		}
		return carros;

	}

	public static void update(Carro carro) throws SQLException {

		String sql = "UPDATE carro SET cor = ?, potencia = ?, portas = ?, ano = ?, placa = ?, nome = ? WHERE id = ?";

		try (PreparedStatement pstm = ConnectionFactory.getConnection().prepareStatement(sql)) {

			pstm.setString(1, carro.getCor());
			pstm.setInt(2, carro.getPotencia());
			pstm.setInt(3, carro.getPortas());
			pstm.setInt(4, carro.getAno());
			pstm.setString(5, carro.getPlaca());
			pstm.setString(6, carro.getNome());
			pstm.setInt(7, carro.getId());

			pstm.execute();

		}

	}

}
