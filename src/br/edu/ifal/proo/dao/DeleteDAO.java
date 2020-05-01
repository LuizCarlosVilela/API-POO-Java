package br.edu.ifal.proo.dao;
import java.sql.*;

public class DeleteDAO {
	public void clearAll() {
		String sql1 = "delete from tbl_perfil";
		String sql2 = "delete from tbl_blog";
		String sql3 = "delete from tbl_post";
		String sql4 = "delete from tbl_comentario";
		
		//Reinicia o auto_increment
		String sql5 = "alter table tbl_blog auto_increment = 1";
		
		//Reinicia o auto_autoIncrement
		String sql6 = "alter table tbl_post auto_increment = 1";
		
		ConexaoDAO con = new ConexaoDAO();
		Connection conexao = con.getConexao();
		
		PreparedStatement prep1 = null;
		PreparedStatement prep2 = null;
		PreparedStatement prep3 = null;
		PreparedStatement prep4 = null;
		PreparedStatement prep5 = null;
		PreparedStatement prep6 = null;
		
		
		try {
			
			prep4 = conexao.prepareStatement(sql4);
			prep4.execute();
			
			prep3 = conexao.prepareStatement(sql3);
			prep3.execute();
			
			//Reinicia o auto_increment da tbl_post
			prep6 = conexao.prepareStatement(sql6);
			prep6.execute();
			
			prep2 = conexao.prepareStatement(sql2);
			prep2.execute();
			
			//Reinicia o auto_increment da tbl_blog
			prep5 = conexao.prepareStatement(sql5);
			prep5.execute();
			
			prep1 = conexao.prepareStatement(sql1);
			prep1.execute();
			
			
		} catch (Exception e) {
			//System.out.println(e.getMessage());
		}finally {
			try {
				conexao.close();
				prep1.close();
				prep2.close();
				prep3.close();
				prep4.close();
				prep5.close();
			} catch (Exception e2) {
				//System.out.println(e2.getMessage());
			}
		}
	}
	
	
	public void deleteFromPostComent(String postId) {
		
		ConexaoDAO con = new ConexaoDAO();
		Connection conexao = con.getConexao();
		
		PreparedStatement prep1 = null;
		PreparedStatement prep2 = null;
		
		String sql1 = "delete from tbl_comentario where id_post = ?";
		String sql2= "delete from tbl_post where id_post = ?";
		
		try {
			
			prep1 = conexao.prepareStatement(sql1);
			prep1.setString(1, postId);
			prep1.execute();
			
			prep2 = conexao.prepareStatement(sql2);
			prep2.setString(1, postId);
			prep2.execute();
			
		
		} catch (Exception e) {
//			System.out.println(e.getMessage());
		}finally {
			try {
				conexao.close();
				prep1.close();
				prep2.close();
			} catch (Exception e2) {
//				System.out.println(e2.getMessage());
			}
		}
	}
	
	public void deleteFromBlogPostComent(String blogId) {
		String sql1 = "select tbl_post.id_post from tbl_post, tbl_blog where tbl_blog.id_blog = tbl_post.id_blog and tbl_blog.id_blog = ?";
		String sql2 = "delete from tbl_comentario where id_post = ?";
		String sql3 = "delete from tbl_post where id_blog = ?";
		String sql4 = "delete from tbl_blog where id_blog = ?";
		
		ConexaoDAO con = new ConexaoDAO();
		Connection conexao = con.getConexao();
		
		PreparedStatement prep1 = null;
		PreparedStatement prep2 = null;
		PreparedStatement prep3 = null;
		PreparedStatement prep4 = null;

		ResultSet r = null;
		
		try {
			
			prep1 = conexao.prepareStatement(sql1);
			prep1.setString(1, blogId);
			r = prep1.executeQuery();
			
			r.next();
			String id_post = r.getString("tbl_post.id_post");
			
			prep2 = conexao.prepareStatement(sql2);
			prep2.setString(1, id_post);
			prep2.execute();
			
			prep3 = conexao.prepareStatement(sql3);
			prep3.setString(1, blogId);
			prep3.execute();
			
			prep4 = conexao.prepareStatement(sql4);
			prep4.setString(1, blogId);
			prep4.execute();
			
		} catch (Exception e) {
			//System.out.println(e.getMessage());
		}finally {
			try {
				conexao.close();
				r.close();
				prep1.close();
				prep2.close();
				prep3.close();
				prep4.close();
			} catch (Exception e2) {
				//System.out.println(e2.getMessage());
			}
		}
	}
	
	public void deleteFromPerfil(String login) {
		String sql0 = "select tbl_blog.id_blog from tbl_perfil, tbl_blog where tbl_perfil.id_perfil = tbl_blog.id_perfil and tbl_blog.dono = ?";
		String sql1 = "select tbl_post.id_post from tbl_post, tbl_blog where tbl_blog.id_blog = tbl_post.id_blog and tbl_blog.id_blog = ?";
		String sql2 = "delete from tbl_comentario where id_post = ? or login = ?";
		String sql3 = "delete from tbl_post where id_blog = ?";
		String sql4 = "delete from tbl_blog where id_blog = ?";
		String sql5 = "delete from tbl_perfil where login = ?";
		
		ConexaoDAO con = new ConexaoDAO();
		Connection conexao = con.getConexao();
		
		PreparedStatement prep0 = null;
		PreparedStatement prep1 = null;
		PreparedStatement prep2 = null;
		PreparedStatement prep3 = null;
		PreparedStatement prep4 = null;
		PreparedStatement prep5 = null;

		ResultSet r = null;
		ResultSet r2 = null;
		
		try {
			
			prep0 = conexao.prepareStatement(sql0);
			prep0.setString(1, login);
			
			r2 = prep0.executeQuery();
			
			String blogId = null;
			
			if(r2.next()) {
				blogId = r2.getString("tbl_blog.id_blog");
			}
			
			
			prep1 = conexao.prepareStatement(sql1);
			prep1.setString(1, blogId);
			r = prep1.executeQuery();
			String id_post = null;
			if(r.next()) {
				id_post = r.getString("tbl_post.id_post");
				
			}
			
			prep2 = conexao.prepareStatement(sql2);
			prep2.setString(1, id_post);
			prep2.setString(2, login);
			prep2.execute();
			
			prep3 = conexao.prepareStatement(sql3);
			prep3.setString(1, blogId);
			prep3.execute();
			
			prep4 = conexao.prepareStatement(sql4);
			prep4.setString(1, blogId);
			prep4.execute();
			
			prep5 = conexao.prepareStatement(sql5);
			prep5.setString(1, login);
			prep5.execute();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				conexao.close();
				r.close();
				r2.close();
				prep0.close();
				prep1.close();
				prep2.close();
				prep3.close();
				prep4.close();
				prep5.close();
			} catch (Exception e2) {
				//System.out.println(e2.getMessage());
			}
		}
	}
	
}
