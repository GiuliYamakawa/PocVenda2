package br.univel.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Banco {
	
	static String user = "admin";
	static String pass = "admin";
	static String database = "PocVenda";
	static String host = "localhost";
	static String porta = "5432";
	private static Connection conexao;

	public static synchronized Connection conectar(){
		if (conexao == null)
			try {
				Class.forName("org.postgresql.Driver");
				conexao = DriverManager.getConnection("jdbc:postgresql://" + host + ":" + porta + "/" + database, user, pass);
			} catch(Exception e){
				e.printStackTrace();
				System.exit(0);
			}
			return conexao;
	}
	
	public static void fechar(){
		try {
			conexao.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
}
