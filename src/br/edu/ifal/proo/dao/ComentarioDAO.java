package br.edu.ifal.proo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import br.edu.ifal.proo.modelo.Comentario;

public class ComentarioDAO {
	
	public String insert(Comentario c ) {
		
		ConexaoDAO con = new ConexaoDAO();
		Connection conexao = con.getConexao();
		
		PreparedStatement prep = null;
		ResultSet r = null;
		String sql = "insert into tbl_comentario (conteudo, id_post, login) values (?,?,?)";
		
		try {
			prep = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			prep.setString(1, c.getConteudo());
			prep.setString(2, c.getId_post());
			prep.setString(3, c.getLogin());
			
			prep.execute();
			
			r = prep.getGeneratedKeys();
			
			if(r.next()) {
				
				
				int id = r.getInt(1);
				
				String id1 = Integer.toString(id);
				return id1;
			}
			else {
				return null;
			}
			
		} catch (Exception e) {
//			System.out.println(e.getMessage());
			return null;
		}finally {
			try {
				r.close();
				conexao.close();
				prep.close();
			} catch (Exception e2) {
//				System.out.println(e2.getMessage());
			}
		}
	}
	
	public boolean checkId(String id ) {
		
		ConexaoDAO con = new ConexaoDAO();
		Connection conexao = con.getConexao();
		
		PreparedStatement prep = null;
		ResultSet r = null;
		String sql = "select * from tbl_comentario where id_comentario = ?";
		
		try {
			prep = conexao.prepareStatement(sql);
			
			prep.setString(1, id);
			
			r = prep.executeQuery();
			
			while(r.next()) {
				return true;
			}
			return false;
			
		} catch (Exception e) {
//			System.out.println(e.getMessage());
			return false;
		}finally {
			try {
				r.close();
				conexao.close();
				prep.close();
			} catch (Exception e2) {
//				System.out.println(e2.getMessage());
			}
		}
	}
	
	public String getConteudo(String id ) {
		
		ConexaoDAO con = new ConexaoDAO();
		Connection conexao = con.getConexao();
		
		PreparedStatement prep = null;
		ResultSet r = null;
		String sql = "select * from tbl_comentario where id_comentario = ?";
		
		try {
			prep = conexao.prepareStatement(sql);
			
			prep.setString(1, id);
			
			r = prep.executeQuery();
			
			while(r.next()) {
				String conteudo = r.getString("conteudo");
				return conteudo;
			}
			
			return null;
		} catch (Exception e) {
//			System.out.println(e.getMessage());
			return null;
		}finally {
			try {
				r.close();
				conexao.close();
				prep.close();
			} catch (Exception e2) {
//				System.out.println(e2.getMessage());
			}
		}
	}
	
	
	public String getLogin(String id ) {
		
		ConexaoDAO con = new ConexaoDAO();
		Connection conexao = con.getConexao();
		
		PreparedStatement prep = null;
		ResultSet r = null;
		String sql = "select * from tbl_comentario where id_comentario = ?";
		
		try {
			prep = conexao.prepareStatement(sql);
			
			prep.setString(1, id);
			
			r = prep.executeQuery();
			
			while(r.next()) {
				String login = r.getString("login");
				return login;
			}
			
			return null;
		} catch (Exception e) {
//			System.out.println(e.getMessage());
			return null;
		}finally {
			try {
				r.close();
				conexao.close();
				prep.close();
			} catch (Exception e2) {
//				System.out.println(e2.getMessage());
			}
		}
	}
}
