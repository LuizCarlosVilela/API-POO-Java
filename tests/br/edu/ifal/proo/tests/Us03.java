/**
 * 
 */
package br.edu.ifal.proo.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import br.edu.ifal.proo.Fachada;
import br.edu.ifal.proo.excecoes.AtributoInvalidoException;
import br.edu.ifal.proo.excecoes.CadastroInvalidoException;
import br.edu.ifal.proo.excecoes.FalhaAutenticacaoException;

/**
 * User Story-- Criar Blogs Cadastra um Blog, associando-o a um usuário. No ato
 * da criação deve-se fornecer as seguintes informações:
 * 
 * * Titulo do blog
 * * Descrição do Bloco
 * 
 * Restrições:
 *    - Deve ser fornecido as credenciais corretas do usuário
 *    O blog, diferentemente de um usuário, terá o conceito de id's.
 *    Isto é necessário porque em um blog não existe nenhuma informação única (Como o login em usuários).
 *    Podem existir blogs com mesmo nome e descrição
 * @author ivocalado
 *
 */
public class Us03 {
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
	 * Testa autenticacao do usuário
	 * @throws Exception
	 */
	@Test
	public void test001() throws Exception {
		exceptionRule.expect(FalhaAutenticacaoException.class);
		exceptionRule.expectMessage("Login/senha incorretos");
		fachada.createBlog("joaosilva", "123", "Meu blog", "Descrição");
	}

	/**
	 * Testa autenticacao do usuário
	 * @throws Exception
	 */
	@Test
	public void test002() throws Exception {
		exceptionRule.expect(FalhaAutenticacaoException.class);
		exceptionRule.expectMessage("Login/senha incorretos");
		fachada.createBlog("", "123", "Meu blog", "Descrição");
		
	}

	/**
	 * Testa autenticacao do usuário
	 * @throws Exception
	 */
	@Test
	public void test003() throws Exception {
		exceptionRule.expect(FalhaAutenticacaoException.class);
		exceptionRule.expectMessage("Login/senha incorretos");
		fachada.createBlog(null, "123", "Meu blog", "Descrição");
		
	}

	/**
	 * Testa autenticacao do usuário
	 * @throws Exception
	 */
	@Test
	public void test004() throws Exception {
		exceptionRule.expect(FalhaAutenticacaoException.class);
		exceptionRule.expectMessage("Login/senha incorretos");
		fachada.createBlog("joaosilva", "", "Meu blog", "Descrição");
		
	}

	/**
	 * Testa autenticacao do usuário
	 * @throws Exception
	 */
	@Test
	public void test005() throws Exception {
		exceptionRule.expect(FalhaAutenticacaoException.class);
		exceptionRule.expectMessage("Login/senha incorretos");
		fachada.createBlog("joaosilva", null, "Meu blog", "Descrição");
		
	}
	
	/**
	 * Testa autenticacao do usuário
	 * @throws Exception
	 */
	@Test
	public void test006() throws Exception {
		exceptionRule.expect(FalhaAutenticacaoException.class);
		exceptionRule.expectMessage("Login/senha incorretos");
		fachada.createBlog("mariasilva", "gdfsgsdf", "Meu blog", "Descrição");
	}
	
	/**
	 * Testa se os campos nome do blog e descricao foram fornecidos corretamente
	 * @throws Exception
	 */
	@Test
	public void test007() throws Exception {
		fachada.clearPersistence();

		fachada.createProfile("mariasilva", "123", "Maria Silva", "maria@gmail.com", "Feminino", "01/01/2000",
				"Eu sou eu");
		fachada.createProfile("sicrano", "1235", "Sicrano na Silva", "sicrano@gmail.com", "Masculino", "01/01/1980",
				"Eu sou outra pessoa");

		
		exceptionRule.expect(CadastroInvalidoException.class);
		exceptionRule.expectMessage("Dados do blog incorretos");
		fachada.createBlog("mariasilva", "123", "", "Descrição");
	}

	/**
	 * Testa se os campos nome do blog e descricao foram fornecidos corretamente
	 * @throws Exception
	 */
	@Test
	public void test008() throws Exception {
		fachada.clearPersistence();

		fachada.createProfile("mariasilva", "123", "Maria Silva", "maria@gmail.com", "Feminino", "01/01/2000",
				"Eu sou eu");
		fachada.createProfile("sicrano", "1235", "Sicrano na Silva", "sicrano@gmail.com", "Masculino", "01/01/1980",
				"Eu sou outra pessoa");

		exceptionRule.expect(CadastroInvalidoException.class);
		exceptionRule.expectMessage("Dados do blog incorretos");
		fachada.createBlog("mariasilva", "123", null, "Descrição");
	}

	/**
	 * Testa se os campos nome do blog e descricao foram fornecidos corretamente
	 * @throws Exception
	 */
	@Test
	public void test009() throws Exception {
		fachada.clearPersistence();

		fachada.createProfile("mariasilva", "123", "Maria Silva", "maria@gmail.com", "Feminino", "01/01/2000",
				"Eu sou eu");
		fachada.createProfile("sicrano", "1235", "Sicrano na Silva", "sicrano@gmail.com", "Masculino", "01/01/1980",
				"Eu sou outra pessoa");

		exceptionRule.expect(CadastroInvalidoException.class);
		exceptionRule.expectMessage("Dados do blog incorretos");
		fachada.createBlog("mariasilva", "123", "meu blog", "");
	}
	
	/**
	 * Testa se os campos nome do blog e descricao foram fornecidos corretamente
	 * @throws Exception
	 */
	@Test
	public void test010() throws Exception {
		fachada.clearPersistence();

		fachada.createProfile("mariasilva", "123", "Maria Silva", "maria@gmail.com", "Feminino", "01/01/2000",
				"Eu sou eu");
		fachada.createProfile("sicrano", "1235", "Sicrano na Silva", "sicrano@gmail.com", "Masculino", "01/01/1980",
				"Eu sou outra pessoa");

		exceptionRule.expect(CadastroInvalidoException.class);
		exceptionRule.expectMessage("Dados do blog incorretos");
		fachada.createBlog("mariasilva", "123", "meu blog", null);
	}
	
	/**
	 * Testa se dois blogs criados pelo mesmo usuario recebem ids diferentes
	 * @throws Exception
	 */
	@Test
	public void test011() throws Exception {
		fachada.clearPersistence();

		fachada.createProfile("mariasilva", "123", "Maria Silva", "maria@gmail.com", "Feminino", "01/01/2000",
				"Eu sou eu");
		fachada.createProfile("sicrano", "1235", "Sicrano na Silva", "sicrano@gmail.com", "Masculino", "01/01/1980",
				"Eu sou outra pessoa");

		String id1 = fachada.createBlog("mariasilva", "123", "Meu primeiro blog", "Quidquid latine dictum sit, altum viditur");
		String id2 = fachada.createBlog("mariasilva", "123", "Meu primeiro blog", "Whatever is said in Latin sounds profound");
		assertNotEquals(id1, id2);
	}

	/**
	 * Testa a recuperacao dos dados do blog
	 * @throws Exception
	 */
	@Test
	public void test012() throws Exception {
		fachada.clearPersistence();

		fachada.createProfile("mariasilva", "123", "Maria Silva", "maria@gmail.com", "Feminino", "01/01/2000",
				"Eu sou eu");

		String id1 = fachada.createBlog("mariasilva", "123", "Meu primeiro blog", "Quidquid latine dictum sit, altum viditur");
		String id2 = fachada.createBlog("mariasilva", "123", "Meu primeiro blog", "Whatever is said in Latin sounds profound");

		assertEquals("Meu primeiro blog", fachada.getBlogInformation(id1, "titulo"));
		assertEquals("Quidquid latine dictum sit, altum viditur", fachada.getBlogInformation(id1, "descricao"));
		assertEquals("Whatever is said in Latin sounds profound", fachada.getBlogInformation(id2, "descricao"));
	}
	
	/**
	 * Testa a passagem de um atributo invalido durante a recuperacao
	 * @throws Exception
	 */
	@Test
	public void test013() throws Exception {
		fachada.clearPersistence();

		fachada.createProfile("mariasilva", "123", "Maria Silva", "maria@gmail.com", "Feminino", "01/01/2000",
				"Eu sou eu");

		String id1 = fachada.createBlog("mariasilva", "123", "Meu primeiro blog", "Quidquid latine dictum sit, altum viditur");
		
		exceptionRule.expect(AtributoInvalidoException.class);
		exceptionRule.expectMessage("Atributo inválido");

		fachada.getBlogInformation(id1, "");
	}
	
	/**
	 * Testa a passagem de um atributo invalido durante a recuperacao
	 * @throws Exception
	 */
	@Test
	public void test014() throws Exception {
		fachada.clearPersistence();

		fachada.createProfile("mariasilva", "123", "Maria Silva", "maria@gmail.com", "Feminino", "01/01/2000",
				"Eu sou eu");

		String id1 = fachada.createBlog("mariasilva", "123", "Meu primeiro blog", "Quidquid latine dictum sit, altum viditur");
		
		exceptionRule.expect(AtributoInvalidoException.class);
		exceptionRule.expectMessage("Atributo inválido");

		fachada.getBlogInformation(id1, null);
	}
	
	/**
	 * Testa a passagem de um atributo invalido durante a recuperacao
	 * @throws Exception
	 */
	@Test
	public void test015() throws Exception {
		fachada.clearPersistence();

		fachada.createProfile("mariasilva", "123", "Maria Silva", "maria@gmail.com", "Feminino", "01/01/2000",
				"Eu sou eu");

		String id1 = fachada.createBlog("mariasilva", "123", "Meu primeiro blog", "Quidquid latine dictum sit, altum viditur");
		
		exceptionRule.expect(AtributoInvalidoException.class);
		exceptionRule.expectMessage("Atributo inválido");

		fachada.getBlogInformation(id1, "safsad");
	}

	/**
	 * Testa o retorno do dono de um blog a partir do atributo dono
	 * @throws Exception
	 */
	@Test
	public void test016() throws Exception {
		fachada.clearPersistence();

		fachada.createProfile("mariasilva", "123", "Maria Silva", "maria@gmail.com", "Feminino", "01/01/2000",
				"Eu sou eu");
		fachada.createProfile("sicrano", "1235", "Sicrano na Silva", "sicrano@gmail.com", "Masculino", "01/01/1980",
				"Eu sou outra pessoa");

		String id1 = fachada.createBlog("mariasilva", "123", "Meu primeiro blog", "Quidquid latine dictum sit, altum viditur");
		String id2 = fachada.createBlog("sicrano", "1235", "Meu primeiro blog", "Whatever is said in Latin sounds profound");
		
		assertEquals("mariasilva", fachada.getBlogInformation(id1, "dono"));
		assertEquals("sicrano", fachada.getBlogInformation(id2, "dono"));
	}
}
