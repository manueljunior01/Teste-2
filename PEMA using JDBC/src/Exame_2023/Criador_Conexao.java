package Exame_2023;

import java.sql.*;

public class Criador_Conexao {

	public static Connection getConnection() {
		try {
			String url="jdbc:mysql://localhost/GestaoPessoal";
			String user = "root";
			String password="M@nuel123";
			
			Connection connection = DriverManager.getConnection(url,user,password);
			return connection;
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
