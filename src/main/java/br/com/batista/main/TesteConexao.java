package br.com.batista.main;

import java.sql.Connection;
import java.sql.SQLException;

import br.com.batista.ConnectionFactory;

public class TesteConexao {
	
	public static void main(String[] args) throws SQLException {
		
		Connection connection = ConnectionFactory.getConnection();
		
		System.out.println("Closing Connection");
		
		connection.close();
		
		
	}
	
}
