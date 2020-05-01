/**
 * 
 */
package br.edu.ifal.proo.tests;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import br.edu.ifal.proo.Fachada;
import br.edu.ifal.proo.excecoes.FalhaAutenticacaoException;
import br.edu.ifal.proo.excecoes.PerfilNaoEncontradoException;

/**
 * User Story-- Deletar conta de usuário
 * 
 * Remove a conta do usuário do sistema.
 * 
 * Restrições:
 *    - Devem ser fornecidas credencias de acesso válidas
 * @author ivocalado
 *
 */
public class Us10 {

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
	 * Testa a autenticação do usuário durante a deleção de um profile
	 * @throws Exception
	 */
	@Test
	public void test001() throws Exception {
		fachada.clearPersistence();
		exceptionRule.expect(FalhaAutenticacaoException.class);
		exceptionRule.expectMessage("Login/senha incorretos");
		fachada.deleteProfile("", "");
	}
	
	/**
	 * Testa a autenticação do usuário durante a deleção de um profile
	 * @throws Exception
	 */
	@Test
	public void test002() throws Exception {
		fachada.clearPersistence();
		exceptionRule.expect(FalhaAutenticacaoException.class);
		exceptionRule.expectMessage("Login/senha incorretos");
		fachada.deleteProfile(null, "");
	}
	
	/**
	 * Testa a autenticação do usuário durante a deleção de um profile
	 * @throws Exception
	 */
	@Test
	public void test003() throws Exception {
		fachada.clearPersistence();
		exceptionRule.expect(FalhaAutenticacaoException.class);
		exceptionRule.expectMessage("Login/senha incorretos");
		fachada.deleteProfile("", null);
	}

	/**
	 * Testa a autenticação do usuário durante a deleção de um profile
	 * @throws Exception
	 */
	@Test
	public void test004() throws Exception {
		fachada.clearPersistence();
		exceptionRule.expect(FalhaAutenticacaoException.class);
		exceptionRule.expectMessage("Login/senha incorretos");
		fachada.deleteProfile(null, null);
	}
	
	/**
	 * Os comentários do usuário devem ser deletados mesmo em blogs de outros usuários
	 * @throws Exception
	 */
	@Test
	public void test005() throws Exception {
		exceptionRule.expect(PerfilNaoEncontradoException.class);
		exceptionRule.expectMessage("Perfil não encontrado");
		
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
		
		fachada.deleteProfile("sicrano", "1235");
		fachada.getNumberOfBlogsByLogin("sicrano");
	}	

	
	/**
	 * Os comentários do usuário devem ser deletados mesmo em blogs de outros usuários
	 * @throws Exception
	 */
	@Test
	public void test006() throws Exception {
		
		
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

		fachada.deleteProfile("mariasilva", "123");
		
		assertEquals(1, fachada.getNumberOfComments(postId));
	}	
	
	

}
