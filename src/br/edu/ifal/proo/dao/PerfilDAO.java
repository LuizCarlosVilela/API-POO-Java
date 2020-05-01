package br.edu.ifal.proo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifal.proo.modelo.Perfil;

public class PerfilDAO {

	public void insert(Perfil p) {
		ConexaoDAO con = new ConexaoDAO();
		Connection conexao = con.getConexao();

		PreparedStatement prep = null;

		String sql = "insert into tbl_perfil (login, senha, email, sexo, biografia, nomeExibicao, dataNascimento) values (?,?,?,?,?,?,?)";

		try {
			prep = conexao.prepareStatement(sql);

			prep.setString(1, p.getLogin());
			prep.setString(2, p.getSenha());
			prep.setString(3, p.getEmail());
			prep.setString(4, p.getSexo());
			prep.setString(5, p.getBiografia());
			prep.setString(6, p.getNomeExibicao());
			prep.setString(7, p.getDataNascimento());

			prep.execute();

		} catch (Exception e) {
//			System.out.println(e.getMessage());
		} finally {
			try {
				conexao.close();
				prep.close();
			} catch (Exception e2) {
//				System.out.println(e2.getMessage());
			}
		}
	}

	public boolean checkLogin(String l) {
		ConexaoDAO con = new ConexaoDAO();
		Connection conexao = con.getConexao();

		PreparedStatement prep = null;
		ResultSet r = null;

		String sql = "select login from tbl_perfil";

		try {

			prep = conexao.prepareStatement(sql);

			r = prep.executeQuery();

			while (r.next()) {
				String login = r.getString("login");
				if (login.equals(l)) {
					return true;
				}
			}

			return false;

		} catch (Exception e) {
//			System.out.println(e.getMessage());
			return false;
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

	public boolean checkEmail(String l) {
		ConexaoDAO con = new ConexaoDAO();
		Connection conexao = con.getConexao();

		PreparedStatement prep = null;
		ResultSet r = null;

		String sql = "select email from tbl_perfil";

		try {

			prep = conexao.prepareStatement(sql);

			r = prep.executeQuery();

			while (r.next()) {
				String email = r.getString("email");
				if (email.equals(l)) {
					return true;
				}
			}

			return false;

		} catch (Exception e) {
//			System.out.println(e.getMessage());
			return false;
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

	public boolean checkAtributo(String l) {
		ConexaoDAO con = new ConexaoDAO();
		Connection conexao = con.getConexao();

		PreparedStatement prep = null;
		ResultSet r = null;

		String sql = "select * from tbl_perfil";

		try {

			prep = conexao.prepareStatement(sql);

			r = prep.executeQuery();

			ResultSetMetaData m = r.getMetaData();

			for (int x = 1; x <= m.getColumnCount(); x++) {
				if (m.getColumnName(x).equals(l)) {
					return true;
				}
			}
			return false;

		} catch (Exception e) {
//			System.out.println(e.getMessage());
			return false;
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

	public String getAtributo(String login, String atributo) {
		ConexaoDAO con = new ConexaoDAO();
		Connection conexao = con.getConexao();

		PreparedStatement prep = null;
		ResultSet r = null;

		String sql = "select * from tbl_perfil where login = ?";

		try {

			prep = conexao.prepareStatement(sql);

			prep.setString(1, login);
			r = prep.executeQuery();

			while (r.next()) {
				String campo = r.getString(atributo);
				return campo;
			}
			return null;

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

	public boolean fazerLogin(String login, String senha) {
		ConexaoDAO con = new ConexaoDAO();
		Connection conexao = con.getConexao();

		PreparedStatement prep = null;
		ResultSet r = null;

		String sql = "select * from tbl_perfil where login = ? and senha = ?";

		try {

			prep = conexao.prepareStatement(sql);

			prep.setString(1, login);
			prep.setString(2, senha);
			r = prep.executeQuery();

			while (r.next()) {
				return true;
			}
			return false;

		} catch (Exception e) {
//			System.out.println(e.getMessage());
			return false;
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

	public void alterSenha(String login, String senha, String valor) {
		ConexaoDAO con = new ConexaoDAO();
		Connection conexao = con.getConexao();

		PreparedStatement prep = null;

		String sql = "update tbl_perfil set senha = ? where login = ? and senha = ?";

		try {

			prep = conexao.prepareStatement(sql);

			prep.setString(1, valor);
			prep.setString(2, login);
			prep.setString(3, senha);

			prep.execute();

		} catch (Exception e) {
//			System.out.println(e.getMessage());
		} finally {
			try {
				conexao.close();
				prep.close();
			} catch (Exception e2) {
//				System.out.println(e2.getMessage());
			}
		}
	}

	public void alterLogin(String login, String senha, String valor) {
		ConexaoDAO con = new ConexaoDAO();
		Connection conexao = con.getConexao();

		PreparedStatement prep = null;

		String sql = "update tbl_perfil set login = ? where login = ? and senha = ?";

		try {

			prep = conexao.prepareStatement(sql);

			prep.setString(1, valor);
			prep.setString(2, login);
			prep.setString(3, senha);

			prep.execute();

		} catch (Exception e) {
//			System.out.println(e.getMessage());
		} finally {
			try {
				conexao.close();
				prep.close();
			} catch (Exception e2) {
//				System.out.println(e2.getMessage());
			}
		}
	}

	public void alterEmail(String login, String senha, String valor) {

		ConexaoDAO con = new ConexaoDAO();
		Connection conexao = con.getConexao();

		PreparedStatement prep = null;

		String sql = "update tbl_perfil set email = ? where login = ? and senha = ?";

		try {

			prep = conexao.prepareStatement(sql);

			prep.setString(1, valor);
			prep.setString(2, login);
			prep.setString(3, senha);

			prep.execute();

		} catch (Exception e) {
//			System.out.println(e.getMessage());
		} finally {
			try {
				conexao.close();
				prep.close();
			} catch (Exception e2) {
//				System.out.println(e2.getMessage());
			}
		}
	}

	public void alterSexo(String login, String senha, String valor) {
		ConexaoDAO con = new ConexaoDAO();
		Connection conexao = con.getConexao();

		PreparedStatement prep = null;

		String sql = "update tbl_perfil set sexo = ? where login = ? and senha = ?";

		try {

			prep = conexao.prepareStatement(sql);

			prep.setString(1, valor);
			prep.setString(2, login);
			prep.setString(3, senha);

			prep.execute();

		} catch (Exception e) {
//			System.out.println(e.getMessage());
		} finally {
			try {
				conexao.close();
				prep.close();
			} catch (Exception e2) {
//				System.out.println(e2.getMessage());
			}
		}
	}

	public void alterDataNascimento(String login, String senha, String valor) {
		ConexaoDAO con = new ConexaoDAO();
		Connection conexao = con.getConexao();

		PreparedStatement prep = null;

		String sql = "update tbl_perfil set dataNascimento = ? where login = ? and senha = ?";

		try {

			prep = conexao.prepareStatement(sql);

			prep.setString(1, valor);
			prep.setString(2, login);
			prep.setString(3, senha);

			prep.execute();

		} catch (Exception e) {
//			System.out.println(e.getMessage());
		} finally {
			try {
				conexao.close();
				prep.close();
			} catch (Exception e2) {
//				System.out.println(e2.getMessage());
			}
		}
	}

	public void alterBiografia(String login, String senha, String valor) {
		ConexaoDAO con = new ConexaoDAO();
		Connection conexao = con.getConexao();

		PreparedStatement prep = null;

		String sql = "update tbl_perfil set biografia = ? where login = ? and senha = ?";

		try {

			prep = conexao.prepareStatement(sql);

			prep.setString(1, valor);
			prep.setString(2, login);
			prep.setString(3, senha);

			prep.execute();

		} catch (Exception e) {
//			System.out.println(e.getMessage());
		} finally {
			try {
				conexao.close();
				prep.close();
			} catch (Exception e2) {
//				System.out.println(e2.getMessage());
			}
		}
	}

	public void alterNomeExibicao(String login, String senha, String valor) {
		ConexaoDAO con = new ConexaoDAO();
		Connection conexao = con.getConexao();

		PreparedStatement prep = null;

		String sql = "update tbl_perfil set nomeExibicao = ? where login = ? and senha = ?";

		try {

			prep = conexao.prepareStatement(sql);

			prep.setString(1, valor);
			prep.setString(2, login);
			prep.setString(3, senha);

			prep.execute();

		} catch (Exception e) {
//			System.out.println(e.getMessage());
		} finally {
			try {
				conexao.close();
				prep.close();
			} catch (Exception e2) {
//				System.out.println(e2.getMessage());
			}
		}
	}

	public String getId(String login, String senha) {
		ConexaoDAO con = new ConexaoDAO();
		Connection conexao = con.getConexao();

		PreparedStatement prep = null;
		ResultSet r = null;

		String sql = "select * from tbl_perfil where login = ? and senha = ?";

		try {

			prep = conexao.prepareStatement(sql);

			prep.setString(1, login);
			prep.setString(2, senha);
			r = prep.executeQuery();

			while (r.next()) {
				int id = r.getInt("id_perfil");
				String id_perfil = Integer.toString(id);

				return id_perfil;
			}
			return null;

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

	public String getId2(String login) {
		ConexaoDAO con = new ConexaoDAO();
		Connection conexao = con.getConexao();

		PreparedStatement prep = null;
		ResultSet r = null;

		String sql = "select * from tbl_perfil where login = ?";

		try {

			prep = conexao.prepareStatement(sql);

			prep.setString(1, login);
			r = prep.executeQuery();

			while (r.next()) {
				int id = r.getInt("id_perfil");
				String id_perfil = Integer.toString(id);

				return id_perfil;
			}
			return null;

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

	public List<String> getIdBlogPerfilIndex(String login) {

		ConexaoDAO con = new ConexaoDAO();
		Connection conexao = con.getConexao();

		PreparedStatement prep = null;
		ResultSet r = null;

		String sql = "select tbl_blog.id_blog from tbl_blog, tbl_perfil where tbl_perfil.id_perfil = tbl_blog.id_perfil and tbl_perfil.id_perfil = ?";

		try {
			PerfilDAO perfilDAO = new PerfilDAO();
			int id = Integer.parseInt(perfilDAO.getId2(login));

			prep = conexao.prepareStatement(sql);

			prep.setInt(1, id);

			r = prep.executeQuery();

			List<String> lista = new ArrayList<>();

			while (r.next()) {
				int id_blog = r.getInt("tbl_blog.id_blog");
				String blog = Integer.toString(id_blog);
				lista.add(blog);
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

	public List<String> getByNome(String match) {

		ConexaoDAO con = new ConexaoDAO();
		Connection conexao = con.getConexao();

		PreparedStatement prep2 = null;
		ResultSet r2 = null;

		PreparedStatement prep3 = null;
		ResultSet r3 = null;


		String sql2 = "select login from tbl_perfil where nomeExibicao like ?  order by login asc";

		String sql3 = "select login from tbl_perfil where nomeExibicao is null and login like ? order by login asc";

		try {

			List<String> lista = new ArrayList<>();
			
			prep3 = conexao.prepareStatement(sql3);
			prep3.setString(1, "%" + match + "%");
			r3 = prep3.executeQuery();
			
			while (r3.next()) {
				String login = r3.getString("login");
				lista.add(login);
			}
			
			
			prep2 = conexao.prepareStatement(sql2);
			prep2.setString(1, "%" + match + "%");
			r2 = prep2.executeQuery();

			while (r2.next()) {
				String login = r2.getString("login");
				lista.add(login);
			}

			return lista;

		} catch (Exception e) {
//			System.out.println(e.getMessage());
			return null;
		} finally {
			try {
				conexao.close();

				prep2.close();
				r2.close();

				prep3.close();
				r3.close();
			} catch (Exception e2) {
//				System.out.println(e2.getMessage());
			}
		}
	}
	
	public List<String> getByNomeByGenero(String sexo) {

		ConexaoDAO con = new ConexaoDAO();
		Connection conexao = con.getConexao();

		PreparedStatement prep = null;
		ResultSet r = null;
		
		String sql = "select login from tbl_perfil where sexo like ? order by login asc";

		try {

			List<String> lista = new ArrayList<>();
			
			prep = conexao.prepareStatement(sql);
			prep.setString(1, "%"+sexo+"%");
			
			r = prep.executeQuery();
			
			while(r.next()) {
				String login = r.getString("login");
				
				lista.add(login);
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
