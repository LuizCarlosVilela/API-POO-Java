package br.edu.ifal.proo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifal.proo.modelo.Post;

public class PostDAO {
	
	public String insert(Post p ) {
		
		ConexaoDAO con = new ConexaoDAO();
		Connection conexao = con.getConexao();
		
		PreparedStatement prep = null;
		ResultSet r = null;
		String sql = "insert into tbl_post (titulo, conteudo, id_blog, dataCriacao) values (?,?,?,?)";
		
		try {
		
			prep = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			prep.setString(1, p.getTitulo());
			prep.setString(2, p.getConteudo());
			prep.setString(3, p.getId_blog());
			prep.setString(4, p.getDataCriacao());
			
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
	
	public String getCampo(String postId, String campo) {
		
		ConexaoDAO con = new ConexaoDAO();
		Connection conexao = con.getConexao();
		
		PreparedStatement prep = null;
		ResultSet r = null;
		String sql = "select * from tbl_post where id_post = ?";
		
		try {
		
			prep = conexao.prepareStatement(sql);
			
			prep.setString(1, postId);
			
			r = prep.executeQuery();
			
			while(r.next()) {
				String valor = r.getString(campo);
				return valor;
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
	
	public boolean checkAtributo(String l) {
		ConexaoDAO con = new ConexaoDAO();
		Connection conexao = con.getConexao();
		
		PreparedStatement prep = null;
		ResultSet r = null;
		
		String sql = "select * from tbl_post";
				
		try {
		
			prep = conexao.prepareStatement(sql);
			
			r = prep.executeQuery();
			
			ResultSetMetaData m = r.getMetaData();
			
			for (int x=1; x <= m.getColumnCount(); x++){
				if(m.getColumnName(x).equals(l)) {
					return true;
				}
			}
			return false;
			
		} catch (Exception e) {
//			System.out.println(e.getMessage());
			return false;
		}finally {
			try {
				conexao.close();
				prep.close();
				r.close();
			} catch (Exception e2) {
//				System.out.println(e2.getMessage());
			}
		}
	}
	
	public boolean checkId(String postId) {
		ConexaoDAO con = new ConexaoDAO();
		Connection conexao = con.getConexao();
		
		PreparedStatement prep = null;
		ResultSet r = null;
		
		String sql = "select * from tbl_post where id_post = ?";
				
		try {
		
			prep = conexao.prepareStatement(sql);
			
			prep.setString(1, postId);
			
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
				conexao.close();
				prep.close();
				r.close();
			} catch (Exception e2) {
//				System.out.println(e2.getMessage());
			}
		}
	}
	
	public boolean checkPostBlogPerfil(String postId, String login) {
		ConexaoDAO con = new ConexaoDAO();
		Connection conexao = con.getConexao(); 
		
		PreparedStatement prep = null;
		ResultSet r = null;
		
		String sql = "select tbl_post.titulo from tbl_post, tbl_blog, tbl_perfil where tbl_blog.id_blog = tbl_post.id_blog and tbl_perfil.id_perfil = tbl_blog.id_perfil and tbl_perfil.login = ? and tbl_post.id_post = ?;";
				
		try {
		
			prep = conexao.prepareStatement(sql);
			
			prep.setString(1, login);
			prep.setString(2, postId);
			
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
				conexao.close();
				prep.close();
				r.close();
			} catch (Exception e2) {
//				System.out.println(e2.getMessage());
			}
		}
	}
	
	
	public void alterTitulo(String postId, String valor) {
		ConexaoDAO con = new ConexaoDAO();
		Connection conexao = con.getConexao();
		
		PreparedStatement prep = null;
		
		String sql = "update tbl_post set titulo = ? where id_post = ?";
				
		try {
		
			prep = conexao.prepareStatement(sql);
			
			prep.setString(1, valor);
			prep.setString(2, postId);
			
			prep.execute();
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			
			
		}finally {
			try {
				conexao.close();
				prep.close();
			} catch (Exception e2) {
//				System.out.println(e2.getMessage());
			}
		}
	}
	public void alterConteudo(String postId, String valor) {
		ConexaoDAO con = new ConexaoDAO();
		Connection conexao = con.getConexao();
		
		PreparedStatement prep = null;
		
		String sql = "update tbl_post set conteudo = ? where id_post = ?";
				
		try {
		
			prep = conexao.prepareStatement(sql);
			
			prep.setString(1, valor);
			prep.setString(2, postId);
			
			prep.execute();
			
		} catch (Exception e) {
//			System.out.println(e.getMessage());
			
			
		}finally {
			try {
				conexao.close();
				prep.close();
			} catch (Exception e2) {
//				System.out.println(e2.getMessage());
			}
		}
	}
	
	public List<String> getComPost(String postId) {
		
		ConexaoDAO con = new ConexaoDAO();
		Connection conexao = con.getConexao();
		
		PreparedStatement prep = null;
		ResultSet r = null;
		
		String sql = "select tbl_comentario.id_comentario from tbl_post, tbl_comentario where tbl_post.id_post = tbl_comentario.id_post and tbl_post.id_post = ?";
		
		try {
			
			prep = conexao.prepareStatement(sql);
			
			prep.setString(1, postId);

			r = prep.executeQuery();
			
			List<String> lista = new ArrayList<>();
			
			while(r.next()) {
				int id_come = r.getInt("tbl_comentario.id_comentario");
				String comen = Integer.toString(id_come);	
				lista.add(comen);
			}
			
			return lista;
			
			
		} catch (Exception e) {
//			System.out.println(e.getMessage());
			return null;
		}finally {
			try {
				conexao.close();
				prep.close();
				r.close();
			} catch (Exception e2) {
//				System.out.println(e2.getMessage());
			}
		}
	}
	
	public int getComPostQuant(String postId) {
		
		ConexaoDAO con = new ConexaoDAO();
		Connection conexao = con.getConexao();
		
		PreparedStatement prep = null;
		ResultSet r = null;
		
		String sql = "select count(tbl_comentario.id_comentario) from tbl_post, tbl_comentario where tbl_post.id_post = tbl_comentario.id_post and tbl_post.id_post = ?";
		
		try {
			
			prep = conexao.prepareStatement(sql);
			
			prep.setString(1, postId);

			r = prep.executeQuery();
			
			while(r.next()) {
				int quantidade = r.getInt("count(tbl_comentario.id_comentario)");
				return quantidade;
			}
			
			return -1;
			
			
		} catch (Exception e) {
//			System.out.println(e.getMessage());
			return -1;
		}finally {
			try {
				conexao.close();
				prep.close();
				r.close();
			} catch (Exception e2) {
//				System.out.println(e2.getMessage());
			}
		}
	}
}
