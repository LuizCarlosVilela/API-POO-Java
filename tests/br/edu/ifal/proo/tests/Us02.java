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

/**
 * User Story-- Alterar Perfil
 * 
 * Altera os dados do perfil de um usuário. Após cadastrado, um usuário pode
 * alterar seus dados cadastrais. (ex.: alterar senha). Para alteração, o login
 * e senha do usuário deve ser informado.
 */
public class Us02 {

	static Fachada fachada;

	@Rule
	public ExpectedException exceptionRule = ExpectedException.none();

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		fachada = new Fachada();
		fachada.clearPersistence();

		fachada.createProfile("mariasilva", "123", "Maria Silva", "maria@gmail.com", "Feminino", "01/01/2000",
				"Eu sou eu");
		fachada.createProfile("sicrano", "1235", "Sicrano na Silva", "sicrano@gmail.com", "Masculino", "01/01/1980",
				"Eu sou outra pessoa");

	}

	/**
	 * Testa se a modificacao de dados pessoais checa login invalido
	 * 
	 * @throws Exception
	 */
	@Test
	public void test001() throws Exception {
		exceptionRule.expect(FalhaAutenticacaoException.class);
		exceptionRule.expectMessage("Login/senha incorretos");

		fachada.changeProfileInformation("joao", "123", "email", "123");
	}

	/**
	 * Testa se a modificacao de dados pessoais checa login invalido
	 * 
	 * @throws Exception
	 */
	@Test
	public void test002() throws Exception {
		exceptionRule.expect(FalhaAutenticacaoException.class);
		exceptionRule.expectMessage("Login/senha incorretos");
		fachada.changeProfileInformation(null, "123", "email", "123");
	}

	/**
	 * Testa se a modificacao de dados pessoais checa se login e senha estão
	 * corretos
	 * 
	 * @throws Exception
	 */
	@Test
	public void test003() throws Exception {
		exceptionRule.expect(FalhaAutenticacaoException.class);
		exceptionRule.expectMessage("Login/senha incorretos");
		fachada.changeProfileInformation("mariasilva", "12334", "nomeExibicao", "fsdfs");
	}

	/**
	 * Testa se a modificacao de dados pessoais checa se login e senha estão
	 * corretos
	 * 
	 * @throws Exception
	 */
	@Test
	public void test004() throws Exception {
		exceptionRule.expect(FalhaAutenticacaoException.class);
		exceptionRule.expectMessage("Login/senha incorretos");
		fachada.changeProfileInformation("mariasilva", null, "nomeExibicao", "fsdfs");
	}

	
	/**
	 * Testa se a modificacao de dados pessoais checa atributo inválido
	 * 
	 * @throws Exception
	 */
	@Test
	public void test005() throws Exception {
		exceptionRule.expect(AtributoInvalidoException.class);
		exceptionRule.expectMessage("Atributo inválido");
		fachada.changeProfileInformation("mariasilva", "123", "fsdfds", "fsdfs");
	}

	/**
	 * Testa se a modificacao de dados pessoais checa atributo inválido
	 * 
	 * @throws Exception
	 */
	@Test
	public void test006() throws Exception {
		exceptionRule.expect(AtributoInvalidoException.class);
		exceptionRule.expectMessage("Atributo inválido");
		fachada.changeProfileInformation("mariasilva", "123", "", "fsdfs");
	}

	/**
	 * Testa se a modificacao de dados pessoais checa atributo inválido
	 * 
	 * @throws Exception
	 */
	@Test
	public void test007() throws Exception {
		exceptionRule.expect(AtributoInvalidoException.class);
		exceptionRule.expectMessage("Atributo inválido");
		fachada.changeProfileInformation("mariasilva", "123", null, "fsdfs");
	}

	/**
	 * Testa se a nova senha é inválida
	 * @throws Exception
	 */
	@Test
	public void test008() throws Exception {
		exceptionRule.expect(CadastroInvalidoException.class);
		exceptionRule.expectMessage("Senha inválida");
		fachada.changeProfileInformation("mariasilva", "123", "senha", "");
	}
	
	/**
	 * Testa se a nova senha é inválida
	 * @throws Exception
	 */
	@Test
	public void test009() throws Exception {
		exceptionRule.expect(CadastroInvalidoException.class);
		exceptionRule.expectMessage("Senha inválida");
		fachada.changeProfileInformation("mariasilva", "123", "senha", null);
	}
	
	/**
	 * Testa se o novo email é inválido
	 * @throws Exception
	 */
	@Test
	public void test010() throws Exception {
		exceptionRule.expect(CadastroInvalidoException.class);
		exceptionRule.expectMessage("Email inválido");
		fachada.changeProfileInformation("mariasilva", "123", "email", "");
	}
	
	/**
	 * Testa se o novo email é inválido
	 * @throws Exception
	 */
	@Test
	public void test011() throws Exception {
		exceptionRule.expect(CadastroInvalidoException.class);
		exceptionRule.expectMessage("Email inválido");
		fachada.changeProfileInformation("mariasilva", "123", "email", null);
	}
	
	/**
	 * Testa se o novo sexo é inválido
	 * @throws Exception
	 */
	@Test
	public void test012() throws Exception {
		exceptionRule.expect(CadastroInvalidoException.class);
		exceptionRule.expectMessage("Sexo inválido");
		fachada.changeProfileInformation("mariasilva", "123", "sexo", null);
	}

	/**
	 * Testa se o novo sexo é inválido
	 * @throws Exception
	 */
	@Test
	public void test013() throws Exception {
		exceptionRule.expect(CadastroInvalidoException.class);
		exceptionRule.expectMessage("Sexo inválido");
		fachada.changeProfileInformation("mariasilva", "123", "sexo", "");
	}
	
	/**
	 * Testa se o novo sexo é inválido
	 * @throws Exception
	 */
	@Test
	public void test014() throws Exception {
		exceptionRule.expect(CadastroInvalidoException.class);
		exceptionRule.expectMessage("Sexo inválido");
		fachada.changeProfileInformation("mariasilva", "123", "sexo", "qualquer coisa");
	}
	
	/**
	 * Testa se o novo login é inválido
	 * @throws Exception
	 */
	@Test
	public void test015() throws Exception {
		exceptionRule.expect(CadastroInvalidoException.class);
		exceptionRule.expectMessage("Login inválido");
		fachada.changeProfileInformation("mariasilva", "123", "login", "");
	}

	/**
	 * Testa se o novo login é inválido
	 * @throws Exception
	 */
	@Test
	public void test016() throws Exception {
		exceptionRule.expect(CadastroInvalidoException.class);
		exceptionRule.expectMessage("Login inválido");
		fachada.changeProfileInformation("mariasilva", "123", "login", null);
	}
	
	/**
	 * Testa se o novo login é existente
	 * @throws Exception
	 */
	@Test
	public void test017() throws Exception {
		exceptionRule.expect(CadastroInvalidoException.class);
		exceptionRule.expectMessage("Login existente");
		fachada.changeProfileInformation("mariasilva", "123", "login", "sicrano");
	}
	
	/**
	 * Testa se o novo email é existente
	 * @throws Exception
	 */
	@Test
	public void test018() throws Exception {
		exceptionRule.expect(CadastroInvalidoException.class);
		exceptionRule.expectMessage("Email existente");
		fachada.changeProfileInformation("mariasilva", "123", "email", "sicrano@gmail.com");
	}
	
	/**
	 * Testa a mudança e recuperação dos dados
	 * @throws Exception
	 */
	@Test
	public void test019() throws Exception {
		fachada.clearPersistence();
		fachada.createProfile("mariasilva", "qwe2", "Maria Silva", "maria@gmail.com", "Feminino", "01/01/2000",
				"Eu sou eu");

		assertEquals("Maria Silva", fachada.getProfileInformation("mariasilva", "nomeExibicao"));
		assertEquals("maria@gmail.com", fachada.getProfileInformation("mariasilva", "email"));
		assertEquals("Feminino", fachada.getProfileInformation("mariasilva", "sexo"));
		assertEquals("01/01/2000", fachada.getProfileInformation("mariasilva", "dataNascimento"));
		assertEquals("Eu sou eu", fachada.getProfileInformation("mariasilva", "biografia"));

		fachada.changeProfileInformation("mariasilva", "qwe2", "nomeExibicao", "Maria Antonia");
		fachada.changeProfileInformation("mariasilva", "qwe2", "sexo", "Masculino");
		fachada.changeProfileInformation("mariasilva", "qwe2", "email", "maria@outlook.com");
		fachada.changeProfileInformation("mariasilva", "qwe2", "dataNascimento", "01/01/1999");
		fachada.changeProfileInformation("mariasilva", "qwe2", "biografia", "nova biografia");

		assertEquals("Maria Antonia", fachada.getProfileInformation("mariasilva", "nomeExibicao"));
		assertEquals("maria@outlook.com", fachada.getProfileInformation("mariasilva", "email"));
		assertEquals("Masculino", fachada.getProfileInformation("mariasilva", "sexo"));
		assertEquals("01/01/1999", fachada.getProfileInformation("mariasilva", "dataNascimento"));
		assertEquals("nova biografia", fachada.getProfileInformation("mariasilva", "biografia"));

	}

}
