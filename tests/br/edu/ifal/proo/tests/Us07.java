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
import br.edu.ifal.proo.excecoes.FalhaAutorizacaoException;

/**
 * User Story-- Adicionar comentários
 * Permite a adição de comentários sobre postagens. 
 * (Ex.: Usuário A emite comentário sobre um post do usuário B)
 * 
 * Restrições:
 *   - o usuário que for postar deve fornecer credenciais válidas
 * 
 * @author ivocalado
 *
 */
public class Us07 {

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
		
		fachada.createPost("sicrano", "1235",  id2,  "Titulo do post",  "Conteúdo do post");
	}

	/**
	 * Testa a autenticação durante a chamada a addComment
	 * @throws Exception
	 */
	@Test
	public void test001() throws Exception {
		exceptionRule.expect(FalhaAutenticacaoException.class);
		exceptionRule.expectMessage("Login/senha incorretos");
		fachada.addComment("", "", "", "");		
	}

	/**
	 * Testa a autenticação durante a chamada a addComment
	 * @throws Exception
	 */
	@Test
	public void test002() throws Exception {
		exceptionRule.expect(FalhaAutenticacaoException.class);
		exceptionRule.expectMessage("Login/senha incorretos");
		fachada.addComment(null, "", "", "");		
	}
	
	/**
	 * Testa a autenticação durante a chamada a addComment
	 * @throws Exception
	 */
	@Test
	public void test003() throws Exception {
		exceptionRule.expect(FalhaAutenticacaoException.class);
		exceptionRule.expectMessage("Login/senha incorretos");
		fachada.addComment(null, null, "", "");		
	}
	
	/**
	 * Testa a checagem de existência do post durante a chamada a addComment
	 * @throws Exception
	 */
	@Test
	public void test004() throws Exception {
		exceptionRule.expect(FalhaAutorizacaoException.class);
		exceptionRule.expectMessage("Post não encontrado");
		fachada.addComment("sicrano", "1235", "", "");		
	}

	/**
	 * Testa a checagem de existência do post durante a chamada a addComment
	 * @throws Exception
	 */
	@Test
	public void test005() throws Exception {
		exceptionRule.expect(FalhaAutorizacaoException.class);
		exceptionRule.expectMessage("Post não encontrado");
		fachada.addComment("sicrano", "1235", null, "");		
	}
	
	/**
	 * Testa a criação dos comentários e a unicidade dos ids dos comentários
	 * @throws Exception
	 */
	@Test
	public void test006() throws Exception {
		int numBlogs = fachada.getNumberOfBlogsByLogin("sicrano");
		assertEquals(1, numBlogs);
		String blogId = fachada.getBlogByLogin("sicrano", 0);
		int numPosts = fachada.getNumberOfPosts(blogId);
		assertEquals(1, numPosts);
		String postId = fachada.getPostByBlog(blogId, 0);
		
		String comentarioId1 = fachada.addComment("sicrano", "1235", postId, "conteudo 1");
		String comentarioId2 = fachada.addComment("mariasilva", "123", postId, "conteudo 2");
		
		assertNotEquals(comentarioId1, comentarioId2);
		
		assertEquals("sicrano", fachada.getCommentAuthor(comentarioId1));
		assertEquals("mariasilva", fachada.getCommentAuthor(comentarioId2));
		
		assertEquals("conteudo 1", fachada.getCommentText(comentarioId1));
		assertEquals("conteudo 2", fachada.getCommentText(comentarioId2));
	}



}
