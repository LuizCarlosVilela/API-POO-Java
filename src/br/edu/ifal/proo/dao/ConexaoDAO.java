package br.edu.ifal.proo.dao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class ConexaoDAO {
	
	public Connection getConexao() {
		Connection conexao = null;
		
		try {
			Properties props = new Properties();
	        FileInputStream file = new FileInputStream("./resource/application.properties");
	        props.load(file);
	        
	        conexao = DriverManager.getConnection(props.getProperty("datasource.url"), props.getProperty("datasource.username"), props.getProperty("datasource.password"));
	        
	        return conexao;
	        
		} catch (Exception e) {
			return null;
		}
	}

}
