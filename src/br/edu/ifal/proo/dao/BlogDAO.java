package br.edu.ifal.proo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifal.proo.modelo.Blog;


public class BlogDAO {
	
	public String insert(Blog b ) {
		
		ConexaoDAO con = new ConexaoDAO();
		Connection conexao = con.getConexao();
		
		PreparedStatement prep = null;
		ResultSet r = null;
		String sql = "insert into tbl_blog (titulo, descricao, id_perfil, dono) values (?,?,?,?)";
		
		try {
			
			prep = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			prep.setString(1, b.getTitulo());
			prep.setString(2, b.getDescricao());
			prep.setInt(3, b.getId_perfil());
			prep.setString(4, b.getDono());
			
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
	
	 
	public String getCampo(String campo, String idBlog ) {
		
		ConexaoDAO con = new ConexaoDAO();
		Connection conexao = con.getConexao();
		
		PreparedStatement prep = null;
		ResultSet r = null;
		String sql = "select * from tbl_blog where id_blog = ?";
		
		try {
			prep = conexao.prepareStatement(sql);
			
			prep.setString(1, idBlog);
			
			r = prep.executeQuery();
			
			while(r.next()) {
				String atributo = r.getString(campo);
				return atributo;
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
		
		String sql = "select * from tbl_blog";
				
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
	
	public boolean checkDono(String login, String blogId) {
		ConexaoDAO con = new ConexaoDAO();
		Connection conexao = con.getConexao();
		
		PreparedStatement prep = null;
		ResultSet r = null;
		
		String sql = "select * from tbl_blog where id_blog = ? and dono = ?";
				
		try {
			prep = conexao.prepareStatement(sql);
			prep.setString(1, blogId);
			prep.setString(2, login);
			
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
	public boolean checkBlog(String blogId) {
		ConexaoDAO con = new ConexaoDAO();
		Connection conexao = con.getConexao();
		
		PreparedStatement prep = null;
		ResultSet r = null;
		
		String sql = "select * from tbl_blog where id_blog = ?";
				
		try {
			prep = conexao.prepareStatement(sql);
			prep.setString(1, blogId);
			
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
	

	
	public void alterTitulo(String login, String blogId, String valor) {
		ConexaoDAO con = new ConexaoDAO();
		Connection conexao = con.getConexao();
		
		PreparedStatement prep = null;
		
		String sql = "update tbl_blog set titulo = ? WHERE id_blog = ? and dono = ?";
			
		
		try {
			prep = conexao.prepareStatement(sql);
			prep.setString(1, valor);
			prep.setInt(2, Integer.parseInt(blogId));
			prep.setString(3, login);
			
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
	
	public void alterDescricao(String login, String blogId, String valor) {
		ConexaoDAO con = new ConexaoDAO();
		Connection conexao = con.getConexao();
		
		PreparedStatement prep = null;
		
		String sql = "update tbl_blog set descricao = ? where id_blog = ? and dono = ?";
		
		try {
			prep = conexao.prepareStatement(sql);
			
			prep.setString(1, valor);
			prep.setInt(2, Integer.parseInt(blogId));
			prep.setString(3, login);
			
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
	
	public int getBlogPerfil(String login) {
		ConexaoDAO con = new ConexaoDAO();
		Connection conexao = con.getConexao();
		
		PreparedStatement prep = null;
		ResultSet r = null;
		
		String sql = "select count(tbl_blog.titulo) from tbl_blog, tbl_perfil where tbl_perfil.id_perfil = tbl_blog.id_perfil and tbl_perfil.id_perfil = ?";
		
		try {
			PerfilDAO perfilDAO = new PerfilDAO();
			int id = Integer.parseInt(perfilDAO.getId2(login));
			
			prep = conexao.prepareStatement(sql);
			
			prep.setInt(1, id);

			r = prep.executeQuery();
			
			while(r.next()) {
				int quantidade = r.getInt("count(tbl_blog.titulo)");
				
				return quantidade;
			}
			return -1;
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
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
	
	
	public List<String> getPostBlog(String blogId) {
		
		ConexaoDAO con = new ConexaoDAO();
		Connection conexao = con.getConexao();
		
		PreparedStatement prep = null;
		ResultSet r = null;
		
		String sql = "select tbl_post.id_post from tbl_blog, tbl_post where tbl_blog.id_blog = tbl_post.id_blog and tbl_blog.id_blog = ?";
		
		try {
			
			prep = conexao.prepareStatement(sql);
			
			prep.setString(1, blogId);

			r = prep.executeQuery();
			
			List<String> lista = new ArrayList<>();
			
			while(r.next()) {
				int id_post = r.getInt("tbl_post.id_post");
				String post = Integer.toString(id_post);	
				lista.add(post);
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

	
	
	public int getPostQuant(String blogId) {
		
		ConexaoDAO con = new ConexaoDAO();
		Connection conexao = con.getConexao();
		
		PreparedStatement prep = null;
		ResultSet r = null;
		
		String sql = "select count(tbl_post.id_post) from tbl_blog, tbl_post where tbl_blog.id_blog = tbl_post.id_blog and tbl_blog.id_blog = ?";
		
		try {
			
			prep = conexao.prepareStatement(sql);
			
			prep.setString(1, blogId);

			r = prep.executeQuery();
			
			
			while(r.next()) {
				int quantidade = r.getInt("count(tbl_post.id_post)");
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
	
	
	public List<String> getBlogByNome(String titulo) {

		ConexaoDAO con = new ConexaoDAO();
		Connection conexao = con.getConexao();

		PreparedStatement prep = null;
		ResultSet r = null;
		
		String sql = "select id_blog from tbl_blog where titulo like ? order by titulo asc";

		try {

			List<String> lista = new ArrayList<>();
			
			prep = conexao.prepareStatement(sql);
			prep.setString(1, "%"+titulo+"%");
			
			r = prep.executeQuery();
			
			while(r.next()) {
				String id_blog = r.getString("id_blog");
				
				lista.add(id_blog);
			}

			return lista;

		} catch (Exception e) {
//			System.out.println(e.getMessage());
			return null;
		} finally {
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
