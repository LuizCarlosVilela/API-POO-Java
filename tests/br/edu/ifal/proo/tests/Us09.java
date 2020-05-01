/**
 * 
 */
package br.edu.ifal.proo.tests;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import br.edu.ifal.proo.Fachada;
import br.edu.ifal.proo.excecoes.FalhaAutenticacaoException;

/**
 * User Story-- Deletar blog
 * Permite a deleção de de um blog. Todos os posts e 
 *    comentários pertencentes aquele blog serão deletados
 * Restrição:
 *    - apenas o dono do blog deve ter permissão de deletar o blog
 * @author ivocalado
 *
 */
public class Us09 {
	static Fachada fachada;

	@Rule
	public ExpectedException exceptionRule = ExpectedException.none();

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		fachada = new Fachada();
	}

	/**
	 * Testa a autenticação do usuário durante a deleção de um blog
	 * @throws Exception
	 */
	@Test
	public void test001() throws Exception {
		fachada.clearPersistence();
		exceptionRule.expect(FalhaAutenticacaoException.class);
		exceptionRule.expectMessage("Login/senha incorretos");
		fachada.deletePost("", "", "");
	}
	
	/**
	 * Testa a autenticação do usuário durante a deleção de um blog
	 * @throws Exception
	 */
	@Test
	public void test002() throws Exception {
		fachada.clearPersistence();
		exceptionRule.expect(FalhaAutenticacaoException.class);
		exceptionRule.expectMessage("Login/senha incorretos");
		fachada.deletePost("dsfd", "", "");
	}
	
	/**
	 * Testa a autenticação do usuário durante a deleção de um blog
	 * @throws Exception
	 */
	@Test
	public void test003() throws Exception {
		fachada.clearPersistence();
		exceptionRule.expect(FalhaAutenticacaoException.class);
		exceptionRule.expectMessage("Login/senha incorretos");
		fachada.deletePost(null, "", "");
	}

	/**
	 * Testa a autenticação do usuário durante a deleção de um blog
	 * @throws Exception
	 */
	@Test
	public void test004() throws Exception {
		fachada.clearPersistence();
		exceptionRule.expect(FalhaAutenticacaoException.class);
		exceptionRule.expectMessage("Login/senha incorretos");
		fachada.deletePost("sdfsd", null, "");
	}
	
	/**
	 * Testa a autenticação do usuário durante a deleção de um blog
	 * @throws Exception
	 */
	@Test
	public void test005() throws Exception {
		fachada.clearPersistence();
		exceptionRule.expect(FalhaAutenticacaoException.class);
		exceptionRule.expectMessage("Login/senha incorretos");
		fachada.deletePost("sfsda", "sadfsadf", "");
	}
	
	/**
	 * Testa a deleção do usuário num blog inválido
	 * @throws Exception
	 */
	@Test
	public void test006() throws Exception {
		exceptionRule.expect(FalhaAutenticacaoException.class);
		exceptionRule.expectMessage("Blog inválido");
		
		fachada.clearPersistence();
		
		fachada.createProfile("sicrano", "1235", "Sicrano na Silva", "sicrano@gmail.com", "Masculino", "01/01/1980",
				"Eu sou outra pessoa");
		
		fachada.createProfile("mariasilva", "123", "Maria Silva", "maria@gmail.com", "Feminino", "01/01/2000",
				"Eu sou eu");

		String id2 = fachada.createBlog("sicrano", "1235", "Meu primeiro blog",
				"Whatever is said in Latin sounds profound");
		
		String postId = fachada.createPost("sicrano", "1235",  id2,  "Titulo do post",  "Conteúdo do post");
		fachada.addComment("sicrano", "1235", postId, "conteudo 1");
		fachada.addComment("mariasilva", "123", postId, "conteudo 2");

		fachada.deleteBlog("sicrano", "1235", "");
	}

	/**
	 * Testa a deleção do usuário num blog inválido
	 * @throws Exception
	 */
	@Test
	public void test007() throws Exception {
		exceptionRule.expect(FalhaAutenticacaoException.class);
		exceptionRule.expectMessage("Blog inválido");
		
		fachada.clearPersistence();
		
		fachada.createProfile("sicrano", "1235", "Sicrano na Silva", "sicrano@gmail.com", "Masculino", "01/01/1980",
				"Eu sou outra pessoa");
		
		fachada.createProfile("mariasilva", "123", "Maria Silva", "maria@gmail.com", "Feminino", "01/01/2000",
				"Eu sou eu");

		String id2 = fachada.createBlog("sicrano", "1235", "Meu primeiro blog",
				"Whatever is said in Latin sounds profound");
		
		String postId = fachada.createPost("sicrano", "1235",  id2,  "Titulo do post",  "Conteúdo do post");
		fachada.addComment("sicrano", "1235", postId, "conteudo 1");
		fachada.addComment("mariasilva", "123", postId, "conteudo 2");

		fachada.deleteBlog("sicrano", "1235", null);
	}
	
	/**
	 * Testa a deleção do usuário num blog de outro usuário
	 * @throws Exception
	 */
	@Test
	public void test008() throws Exception {
		exceptionRule.expect(FalhaAutenticacaoException.class);
		exceptionRule.expectMessage("Acesso negado");
		
		fachada.clearPersistence();
		
		fachada.createProfile("sicrano", "1235", "Sicrano na Silva", "sicrano@gmail.com", "Masculino", "01/01/1980",
				"Eu sou outra pessoa");
		
		fachada.createProfile("mariasilva", "123", "Maria Silva", "maria@gmail.com", "Feminino", "01/01/2000",
				"Eu sou eu");

		String blogId = fachada.createBlog("sicrano", "1235", "Meu primeiro blog",
				"Whatever is said in Latin sounds profound");
		
		String postId = fachada.createPost("sicrano", "1235",  blogId,  "Titulo do post",  "Conteúdo do post");
		fachada.addComment("sicrano", "1235", postId, "conteudo 1");
		fachada.addComment("mariasilva", "123", postId, "conteudo 2");

		fachada.deleteBlog("mariasilva", "123", blogId);
	}
	
	/**
	 * Testa a deleção do blog
	 * @throws Exception
	 */
	@Test
	public void test009() throws Exception {
		
		fachada.clearPersistence();
		
		fachada.createProfile("sicrano", "1235", "Sicrano na Silva", "sicrano@gmail.com", "Masculino", "01/01/1980",
				"Eu sou outra pessoa");
		
		fachada.createProfile("mariasilva", "123", "Maria Silva", "maria@gmail.com", "Feminino", "01/01/2000",
				"Eu sou eu");

		String blogId = fachada.createBlog("sicrano", "1235", "Meu primeiro blog",
				"Whatever is said in Latin sounds profound");

		assertEquals(1, fachada.getNumberOfBlogsByLogin("sicrano"));
		String postId = fachada.createPost("sicrano", "1235",  blogId,  "Titulo do post",  "Conteúdo do post");
		fachada.addComment("sicrano", "1235", postId, "conteudo 1");
		fachada.addComment("mariasilva", "123", postId, "conteudo 2");

		
		fachada.deleteBlog("sicrano", "1235", blogId);
		assertEquals(0, fachada.getNumberOfBlogsByLogin("sicrano"));
	}

}
