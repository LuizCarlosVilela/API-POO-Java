/**
 * 
 */
package br.edu.ifal.proo.tests;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import br.edu.ifal.proo.Fachada;
import br.edu.ifal.proo.excecoes.CadastroInvalidoException;
import br.edu.ifal.proo.excecoes.FalhaAutenticacaoException;
import br.edu.ifal.proo.excecoes.FalhaAutorizacaoException;

/**
 * User Story-- Criar postagem
 * 
 * Cadastra uma nova postagem. Cada nova postagem deve possuir uma data de
 * criação gerada pelo servidor. Ao inserir uma nova entrada, deve-se fornecer
 * as seguintes informações:
 * 
 * -Campos obrigatórios: 
 *    * Título do post
 *    * Conteúdo
 * 
 * @author ivocalado
 *
 */
public class Us05 {

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
	}

	/**
	 * Testa o login invalido do usuário
	 * @throws Exception
	 */
	@Test
	public void test001() throws Exception {
		exceptionRule.expect(FalhaAutenticacaoException.class);
		exceptionRule.expectMessage("Login/senha incorretos");
		fachada.createPost("", "",  "",  "",  "");
	}

	/**
	 * Testa o login invalido do usuário
	 * @throws Exception
	 */
	@Test
	public void test002() throws Exception {
		exceptionRule.expect(FalhaAutenticacaoException.class);
		exceptionRule.expectMessage("Login/senha incorretos");
		fachada.createPost("sdfsd", "",  "",  "",  "");
	}

	/**
	 * Testa o login invalido do usuário
	 * @throws Exception
	 */
	@Test
	public void test003() throws Exception {
		exceptionRule.expect(FalhaAutenticacaoException.class);
		exceptionRule.expectMessage("Login/senha incorretos");
		fachada.createPost(null, "",  "",  "",  "");
	}
	
	/**
	 * Testa o login invalido do usuário
	 * @throws Exception
	 */
	@Test
	public void test004() throws Exception {
		exceptionRule.expect(FalhaAutenticacaoException.class);
		exceptionRule.expectMessage("Login/senha incorretos");
		fachada.createPost("joaosilva", "",  "",  "",  "");
	}
	
	/**
	 * Testa o login invalido do usuário
	 * @throws Exception
	 */
	@Test
	public void test005() throws Exception {
		exceptionRule.expect(FalhaAutenticacaoException.class);
		exceptionRule.expectMessage("Login/senha incorretos");
		fachada.createPost("joaosilva", null,  "",  "",  "");
	}
	
	/**
	 * Testa o login invalido do usuário
	 * @throws Exception
	 */
	@Test
	public void test006() throws Exception {
		fachada.clearPersistence();
		fachada.createProfile("mariasilva", "123", "Maria Silva", "maria@gmail.com", "Feminino", "01/01/2000",
				"Eu sou eu");
		
		exceptionRule.expect(FalhaAutenticacaoException.class);
		exceptionRule.expectMessage("Login/senha incorretos");
		fachada.createPost("mariasilva", "",  "",  "",  "");
	}
	
	/**
	 * Testa o acesso a um blog invalido
	 * @throws Exception
	 */
	@Test
	public void test007() throws Exception {
		fachada.clearPersistence();
		fachada.createProfile("mariasilva", "123", "Maria Silva", "maria@gmail.com", "Feminino", "01/01/2000",
				"Eu sou eu");
		
		exceptionRule.expect(FalhaAutorizacaoException.class);
		exceptionRule.expectMessage("Acesso não autorizado");
		fachada.createPost("mariasilva", "123",  "",  "",  "");
	}
	
	/**
	 * Testa o acesso a um blog invalido
	 * @throws Exception
	 */
	@Test
	public void test008() throws Exception {
		fachada.clearPersistence();
		fachada.createProfile("mariasilva", "123", "Maria Silva", "maria@gmail.com", "Feminino", "01/01/2000",
				"Eu sou eu");
		
		exceptionRule.expect(FalhaAutorizacaoException.class);
		exceptionRule.expectMessage("Acesso não autorizado");
		fachada.createPost("mariasilva", "123",  null,  "",  "");
	}
	
	/**
	 * Testa o acesso a um blog invalido
	 * @throws Exception
	 */
	@Test
	public void test009() throws Exception {
		fachada.clearPersistence();
		fachada.createProfile("mariasilva", "123", "Maria Silva", "maria@gmail.com", "Feminino", "01/01/2000",
				"Eu sou eu");
		
		exceptionRule.expect(FalhaAutorizacaoException.class);
		exceptionRule.expectMessage("Acesso não autorizado");
		fachada.createPost("mariasilva", "123",  "1",  "",  "");
	}
	
	/**
	 * Testa a criação de um post inválido
	 * @throws Exception
	 */
	@Test
	public void test010() throws Exception {
		fachada.clearPersistence();

		fachada.createProfile("sicrano", "1235", "Sicrano na Silva", "sicrano@gmail.com", "Masculino", "01/01/1980",
				"Eu sou outra pessoa");

		String id2 = fachada.createBlog("sicrano", "1235", "Meu primeiro blog",
				"Whatever is said in Latin sounds profound");
		
		exceptionRule.expect(CadastroInvalidoException.class);
		
		exceptionRule.expectMessage("Dados do post incorretos");

		fachada.createPost("sicrano", "1235",  id2,  "",  "");
	}
	
	/**
	 * Testa a criação de um post inválido
	 * @throws Exception
	 */
	@Test
	public void test011() throws Exception {
		fachada.clearPersistence();

		fachada.createProfile("sicrano", "1235", "Sicrano na Silva", "sicrano@gmail.com", "Masculino", "01/01/1980",
				"Eu sou outra pessoa");

		String id2 = fachada.createBlog("sicrano", "1235", "Meu primeiro blog",
				"Whatever is said in Latin sounds profound");
		
		exceptionRule.expect(CadastroInvalidoException.class);
		
		exceptionRule.expectMessage("Dados do post incorretos");

		fachada.createPost("sicrano", "1235",  id2,  null,  "");
	}
	
	/**
	 * Testa a criação de um post inválido
	 * @throws Exception
	 */
	@Test
	public void test012() throws Exception {
		fachada.clearPersistence();

		fachada.createProfile("sicrano", "1235", "Sicrano na Silva", "sicrano@gmail.com", "Masculino", "01/01/1980",
				"Eu sou outra pessoa");

		String id2 = fachada.createBlog("sicrano", "1235", "Meu primeiro blog",
				"Whatever is said in Latin sounds profound");
		
		exceptionRule.expect(CadastroInvalidoException.class);
		
		exceptionRule.expectMessage("Dados do post incorretos");

		
		fachada.createPost("sicrano", "1235",  id2,  "sdffgg",  "");
	}
	
	/**
	 * Testa a criação de um post inválido
	 * @throws Exception
	 */
	@Test
	public void test013() throws Exception {
		fachada.clearPersistence();

		fachada.createProfile("sicrano", "1235", "Sicrano na Silva", "sicrano@gmail.com", "Masculino", "01/01/1980",
				"Eu sou outra pessoa");

		String id2 = fachada.createBlog("sicrano", "1235", "Meu primeiro blog",
				"Whatever is said in Latin sounds profound");
		
		exceptionRule.expect(CadastroInvalidoException.class);
		
		exceptionRule.expectMessage("Dados do post incorretos");

		fachada.createPost("sicrano", "1235",  id2,  "sdfsdf",  null);
	}
	
	/**
	 * Testa a recuperação dos atributos de um post
	 * @throws Exception
	 */
	@Test
	public void test014() throws Exception {
		fachada.clearPersistence();

		fachada.createProfile("sicrano", "1235", "Sicrano na Silva", "sicrano@gmail.com", "Masculino", "01/01/1980",
				"Eu sou outra pessoa");

		String id2 = fachada.createBlog("sicrano", "1235", "Meu primeiro blog",
				"Whatever is said in Latin sounds profound");
		
		String postId = fachada.createPost("sicrano", "1235",  id2,  "Titulo do post",  "Conteúdo do post");
		assertEquals("Titulo do post", fachada.getPostInformation(postId, "titulo"));
		assertEquals("Conteúdo do post", fachada.getPostInformation(postId, "conteudo"));
		
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		assertEquals(format.format(new Date()), fachada.getPostInformation(postId, "dataCriacao"));
	}

}
