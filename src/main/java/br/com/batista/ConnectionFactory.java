package br.com.batista;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public abstract class ConnectionFactory {

	private static DataSource dataSource;

	public static Connection getConnection() throws SQLException {

		if (dataSource == null) {
			ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
			comboPooledDataSource.setJdbcUrl("jdbc:mysql://localhost/CRUDCarros?useTimezone=true&serverTimezone=UTC");
			comboPooledDataSource.setUser("root");
			comboPooledDataSource.setPassword("batista");
			dataSource = comboPooledDataSource;
		}

		return dataSource.getConnection();
	}

}
