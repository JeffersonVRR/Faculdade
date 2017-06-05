package projeto;

import java.sql.DriverManager;

import com.mysql.jdbc.Connection;

public class Conexao {
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/banco_projeto";
	static final String usuario = "root";
	static final String senha = "12345";
	
	public static Connection abrir() throws Exception{
		
		Class.forName(JDBC_DRIVER);
		Connection conexao = (Connection) DriverManager.getConnection(DB_URL, usuario, senha);
		return conexao;
	}
}
