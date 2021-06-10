package br.wagner.util;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

public class ConexaoMySQL {

	final private static String url = "jdbc:mysql://localhost/produto";
	final private static String usuario = "root";
	final private static String senha = "a@88654645";

	private static Connection conn = null;
	
	public static Connection getConnection() {
		
		conn = null;
		
		try {
			conn = DriverManager.getConnection(url, usuario, senha);

		} catch (SQLException ex) {
			System.out.println("Erro: ConexaoMySQL.getConnection() -->" + ex.getMessage());

		}
		return conn;
	}

	public static void remove() {
		try {
			conn.close();
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
	}

}
