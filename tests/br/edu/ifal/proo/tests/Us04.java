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
import br.edu.ifal.proo.excecoes.AtributoInvalidoException;
import br.edu.ifal.proo.excecoes.CadastroInvalidoException;
import br.edu.ifal.proo.excecoes.FalhaAutenticacaoException;
import br.edu.ifal.proo.excecoes.FalhaAutorizacaoException;

/**
 * User Story-- Editar informações do blog Permite a edição de informações do
 * Blog: - titulo - descricao
 * 
 * Restrições: - devem ser fornecidas as credencias de acesso corretas
 * 
 * @author ivocalado
 *
 */
public class Us04 {

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
	 * Testa o mecanismo de autenticacao para a chamada changeBlogInformation
	 * 
	 * @throws Exception
	 */
	@Test
	public void test001() throws Exception {
		exceptionRule.expect(FalhaAutenticacaoException.class);
		exceptionRule.expectMessage("Login/senha incorretos");
		fachada.changeBlogInformation("joaosilva", "123", "1", "Meu blog", "Descrição");
	}

	/**
	 * Testa o mecanismo de autenticacao para a chamada changeBlogInformation
	 * 
	 * @throws Exception
	 */
	@Test
	public void test002() throws Exception {
		exceptionRule.expect(FalhaAutenticacaoException.class);
		exceptionRule.expectMessage("Login/senha incorretos");
		fachada.changeBlogInformation("", "123", "1", "Meu blog", "Descrição");
	}

	/**
	 * Testa o mecanismo de autenticacao para a chamada changeBlogInformation
	 * 
	 * @throws Exception
	 */
	@Test
	public void test003() throws Exception {
		exceptionRule.expect(FalhaAutenticacaoException.class);
		exceptionRule.expectMessage("Login/senha incorretos");
		fachada.changeBlogInformation(null, "123", "1", "Meu blog", "Descrição");
	}

	/**
	 * Testa o mecanismo de autenticacao para a chamada changeBlogInformation
	 * 
	 * @throws Exception
	 */
	@Test
	public void test004() throws Exception {
		exceptionRule.expect(FalhaAutenticacaoException.class);
		exceptionRule.expectMessage("Login/senha incorretos");
		fachada.changeBlogInformation("joaosilva", "", "1", "Meu blog", "Descrição");
	}

	/**
	 * Testa o mecanismo de autenticacao para a chamada changeBlogInformation
	 * 
	 * @throws Exception
	 */
	@Test
	public void test005() throws Exception {
		exceptionRule.expect(FalhaAutenticacaoException.class);
		exceptionRule.expectMessage("Login/senha incorretos");
		fachada.changeBlogInformation("joaosilva", "123", "1", "Meu blog", "Descrição");
	}

	/**
	 * Testa o mecanismo de autorização para a chamada changeBlogInformation
	 * 
	 * @throws Exception
	 */
	@Test
	public void test006() throws Exception {
		fachada.clearPersistence();

		fachada.createProfile("mariasilva", "123", "Maria Silva", "maria@gmail.com", "Feminino", "01/01/2000",
				"Eu sou eu");
		fachada.createProfile("sicrano", "1235", "Sicrano na Silva", "sicrano@gmail.com", "Masculino", "01/01/1980",
				"Eu sou outra pessoa");

		exceptionRule.expect(FalhaAutorizacaoException.class);
		exceptionRule.expectMessage("Acesso não autorizado");
		
		fachada.changeBlogInformation("mariasilva", "123", "", "Meu blog", "Descrição");
	}

	/**
	 * Testa o mecanismo de autorização para a chamada changeBlogInformation
	 * 
	 * @throws Exception
	 */
	@Test
	public void test007() throws Exception {
		fachada.clearPersistence();

		fachada.createProfile("mariasilva", "123", "Maria Silva", "maria@gmail.com", "Feminino", "01/01/2000",
				"Eu sou eu");
		fachada.createProfile("sicrano", "1235", "Sicrano na Silva", "sicrano@gmail.com", "Masculino", "01/01/1980",
				"Eu sou outra pessoa");

		exceptionRule.expect(FalhaAutorizacaoException.class);
		exceptionRule.expectMessage("Acesso não autorizado");
		fachada.changeBlogInformation("mariasilva", "123", null, "Meu blog", "Descrição");
	}

	/**
	 * Testa o mecanismo de autorização para a chamada changeBlogInformation quando
	 * um usuário tenta modificar o blog de outro usuário
	 * 
	 * @throws Exception
	 */
	@Test
	public void test008() throws Exception {
		fachada.clearPersistence();

		fachada.createProfile("mariasilva", "123", "Maria Silva", "maria@gmail.com", "Feminino", "01/01/2000",
				"Eu sou eu");
		fachada.createProfile("sicrano", "1235", "Sicrano na Silva", "sicrano@gmail.com", "Masculino", "01/01/1980",
				"Eu sou outra pessoa");

		String id2 = fachada.createBlog("sicrano", "1235", "Meu primeiro blog",
				"Whatever is said in Latin sounds profound");

		exceptionRule.expect(FalhaAutorizacaoException.class);
		exceptionRule.expectMessage("Acesso não autorizado");
		fachada.changeBlogInformation("mariasilva", "123", id2, "Meu blog", "Descrição");
	}
	
	/**
	 * Testa a atribuição do campo titulo vazio
	 * 
	 * @throws Exception
	 */
	@Test
	public void test009() throws Exception {
		fachada.clearPersistence();

		fachada.createProfile("sicrano", "1235", "Sicrano na Silva", "sicrano@gmail.com", "Masculino", "01/01/1980",
				"Eu sou outra pessoa");

		String id2 = fachada.createBlog("sicrano", "1235", "Meu primeiro blog",
				"Whatever is said in Latin sounds profound");

		exceptionRule.expect(CadastroInvalidoException.class);
		exceptionRule.expectMessage("Título inválido");
		fachada.changeBlogInformation("sicrano", "1235", id2, "titulo", "");
	}
	
	/**
	 * Testa a atribuição do campo titulo vazio
	 * 
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
		exceptionRule.expectMessage("Título inválido");
		fachada.changeBlogInformation("sicrano", "1235", id2, "titulo", null);
	}
	
	/**
	 * Testa a modificacao e posterior recuperação dos dados
	 * 
	 * @throws Exception
	 */
	@Test
	public void test011() throws Exception {
		fachada.clearPersistence();

		fachada.createProfile("sicrano", "1235", "Sicrano na Silva", "sicrano@gmail.com", "Masculino", "01/01/1980",
				"Eu sou outra pessoa");

		String id2 = fachada.createBlog("sicrano", "1235", "Meu primeiro blog",
				"Whatever is said in Latin sounds profound");

		fachada.changeBlogInformation("sicrano", "1235", id2, "descricao", "nova descricao");
		fachada.changeBlogInformation("sicrano", "1235", id2, "titulo", "novo titulo");
		
		assertEquals("novo titulo", fachada.getBlogInformation(id2, "titulo"));
		assertEquals("nova descricao", fachada.getBlogInformation(id2, "descricao"));
	}
}
