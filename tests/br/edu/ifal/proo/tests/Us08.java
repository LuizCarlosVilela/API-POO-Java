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
import br.edu.ifal.proo.excecoes.FalhaAutorizacaoException;

/**
 * User Story-- Deletar post
 * Permite a deleção de um determinado post de um blog. 
 * Todos os comentários associados ao post devem ser igualmente deletados
 * 
 *  * Restrições:
 *   - o usuário que for postar deve fornecer credenciais válidas, 
 *   podendo apenas o dono do blog deletar o post
 * @author ivocalado
 *
 */
public class Us08 {

	static Fachada fachada;
	
	@Rule
	public ExpectedException exceptionRule = ExpectedException.none();

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		fachada = new Fachada();
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
		

	}
	
	/**
	 * Testa a autenticação durante a delecao do post
	 * @throws Exception
	 */
	@Test
	public void test001() throws Exception {
		exceptionRule.expect(FalhaAutenticacaoException.class);
		exceptionRule.expectMessage("Login/senha incorretos");
		fachada.deletePost("", "", "");		
	}

	/**
	 * Testa a autenticação durante a delecao do post
	 * @throws Exception
	 */
	@Test
	public void test002() throws Exception {
		exceptionRule.expect(FalhaAutenticacaoException.class);
		exceptionRule.expectMessage("Login/senha incorretos");
		fachada.deletePost(null, "", "");		
	}
	
	/**
	 * Testa a autenticação durante a delecao do post
	 * @throws Exception
	 */
	@Test
	public void test003() throws Exception {
		exceptionRule.expect(FalhaAutenticacaoException.class);
		exceptionRule.expectMessage("Login/senha incorretos");
		fachada.deletePost(null, null, "");		
	}
	
	/**
	 * Testa o acesso ao post durante a delecao do post
	 * @throws Exception
	 */
	@Test
	public void test004() throws Exception {
		exceptionRule.expect(FalhaAutorizacaoException.class);
		exceptionRule.expectMessage("Acesso negado");
		fachada.deletePost("sicrano", "1235", "");		
	}

	/**
	 * Testa o acesso ao post durante a delecao do post
	 * @throws Exception
	 */
	@Test
	public void test005() throws Exception {
		exceptionRule.expect(FalhaAutorizacaoException.class);
		exceptionRule.expectMessage("Acesso negado");
		fachada.deletePost("sicrano", "1235", null);		
	}

	/**
	 * Testa o acesso ao post durante a delecao do post
	 * @throws Exception
	 */
	@Test
	public void test006() throws Exception {
		exceptionRule.expect(FalhaAutorizacaoException.class);
		exceptionRule.expectMessage("Acesso negado");
		fachada.deletePost("mariasilva", "123", "");		
	}
	
	/**
	 * Testa a deleção de um post e seus comentários
	 * @throws Exception
	 */
	@Test
	public void test007() throws Exception {
		String blogId = fachada.getBlogByLogin("sicrano", 0);
		String postId = fachada.getPostByBlog(blogId, 0);
		
		fachada.deletePost("sicrano", "1235", postId);
		
		assertEquals(0, fachada.getNumberOfPosts(blogId));
	}
	
	/**
	 * Testa se um post deletado continua disponível
	 * @throws Exception
	 */
	@Test
	public void test008() throws Exception {
		exceptionRule.expect(FalhaAutorizacaoException.class);
		exceptionRule.expectMessage("Acesso negado");

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
		
		String blogId = fachada.getBlogByLogin("sicrano", 0);
		postId = fachada.getPostByBlog(blogId, 0);
		fachada.deletePost("sicrano", "1235", postId);
		fachada.deletePost("sicrano", "1235", postId);
	}
	
	/**
	 * Testa se um comentário de um post deletado continua disponível
	 * @throws Exception
	 */
	@Test
	public void test009() throws Exception {
		exceptionRule.expect(FalhaAutorizacaoException.class);
		
		exceptionRule.expectMessage("Acesso negado");

		fachada.clearPersistence();
		
		fachada.createProfile("sicrano", "1235", "Sicrano na Silva", "sicrano@gmail.com", "Masculino", "01/01/1980",
				"Eu sou outra pessoa");
		
		fachada.createProfile("mariasilva", "123", "Maria Silva", "maria@gmail.com", "Feminino", "01/01/2000",
				"Eu sou eu");

		String id2 = fachada.createBlog("sicrano", "1235", "Meu primeiro blog",
				"Whatever is said in Latin sounds profound");
		
		String postId = fachada.createPost("sicrano", "1235",  id2,  "Titulo do post",  "Conteúdo do post");
		String com1 = fachada.addComment("sicrano", "1235", postId, "conteudo 1");
		String com2 = fachada.addComment("mariasilva", "123", postId, "conteudo 2");
		
		String blogId = fachada.getBlogByLogin("sicrano", 0);
		postId = fachada.getPostByBlog(blogId, 0);
		
		fachada.deletePost("sicrano", "1235", postId);
		fachada.getCommentAuthor(com1);
	}

	/**
	 * Testa se um comentário de um post deletado continua disponível
	 * @throws Exception
	 */
	@Test
	public void test010() throws Exception {
		exceptionRule.expect(FalhaAutorizacaoException.class);
		exceptionRule.expectMessage("Acesso negado");

		fachada.clearPersistence();
		
		fachada.createProfile("sicrano", "1235", "Sicrano na Silva", "sicrano@gmail.com", "Masculino", "01/01/1980",
				"Eu sou outra pessoa");
		
		fachada.createProfile("mariasilva", "123", "Maria Silva", "maria@gmail.com", "Feminino", "01/01/2000",
				"Eu sou eu");

		String id2 = fachada.createBlog("sicrano", "1235", "Meu primeiro blog",
				"Whatever is said in Latin sounds profound");
		
		String postId = fachada.createPost("sicrano", "1235",  id2,  "Titulo do post",  "Conteúdo do post");
		String com1 = fachada.addComment("sicrano", "1235", postId, "conteudo 1");
		
		String blogId = fachada.getBlogByLogin("sicrano", 0);
		postId = fachada.getPostByBlog(blogId, 0);
		
		fachada.deletePost("sicrano", "1235", postId);
		fachada.getCommentText(com1);
	}
	
	/**
	 * Testa se o número de posts em um blog após deleção
	 * @throws Exception
	 */
	@Test
	public void test011() throws Exception {

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
		
		String blogId = fachada.getBlogByLogin("sicrano", 0);
		assertEquals(1, fachada.getNumberOfPosts(blogId));
		
		postId = fachada.getPostByBlog(blogId, 0);
		
		fachada.deletePost("sicrano", "1235", postId);
		assertEquals(0, fachada.getNumberOfPosts(blogId));
	}
}
