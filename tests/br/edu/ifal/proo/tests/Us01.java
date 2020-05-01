/**
 * 
 */
package br.edu.ifal.proo.tests;

import static org.junit.Assert.assertEquals;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import br.edu.ifal.proo.Fachada;
import br.edu.ifal.proo.excecoes.AtributoInvalidoException;
import br.edu.ifal.proo.excecoes.CadastroInvalidoException;
import br.edu.ifal.proo.excecoes.PerfilNaoEncontradoException;

/**
 * @author ivocalado
* 
*
*User Story-- Criar perfil
*
*Criar um novo perfil. No ato do cadastro, diversos dados podem ser informados 
de forma a compor o perfil de um novo usuário.

*Dados Informados:
*	- Login;
*	- Senha;
*	- Nome de exibição;
*	- Email;
*	- Sexo;
*	- Data de nascimento;
*	- Biografia;
*
*Restrições:
*    -Campos obrigatórios:
*           *login;
*           *Senha;
*           *email;
       
*    -Campo único no sistema (Não é possivel existir outro perfil com mesmo dado)
*        *login;
*        *email
*
*/
public class Us01 {

	static Fachada fachada;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUp() throws Exception {
		fachada = new Fachada();
		fachada.clearPersistence();
	}

	@AfterClass
	public static void tearDown() throws Exception {
		System.out.println("tear down...");
	}

	@Rule
	public ExpectedException exceptionRule = ExpectedException.none();

	/**
	 * Testa se é possível criar um usuário com login nulo
	 * @throws Exception
	 */
	@Test
	public void test001() throws Exception {
		exceptionRule.expect(CadastroInvalidoException.class);
		exceptionRule.expectMessage("Login inválido");
		fachada.createProfile(null, "123", "maria", "teste@teste.com", "Feminino", "01/01/2000", "biografia");

	}

	/**
	 * Testa se é possível criar um usuário com login vazio
	 * @throws Exception
	 */
	@Test
	public void test002() throws Exception {
		exceptionRule.expect(CadastroInvalidoException.class);
		exceptionRule.expectMessage("Login inválido");
		fachada.createProfile("", "123", "maria", "teste@teste.com", "Feminino", "01/01/2000", "biografia");
	}

	/**
	 * Testa se é possível criar um usuário com senha vazio
	 * @throws Exception
	 */
	@Test
	public void test003() throws Exception {
		exceptionRule.expect(CadastroInvalidoException.class);
		exceptionRule.expectMessage("Senha inválida");
		fachada.createProfile("joao", "", "maria", "teste@teste.com", "Feminino", "01/01/2000", "biografia");
	}

	/**
	 * Testa se é possível criar um usuário com senha nulo
	 * @throws Exception
	 */
	@Test
	public void test004() throws Exception {
		exceptionRule.expect(CadastroInvalidoException.class);
		exceptionRule.expectMessage("Senha inválida");
		fachada.createProfile("joao", null, "maria", "teste@teste.com", "Feminino", "01/01/2000", "biografia");
	}

	/**
	 * Testa se é possível criar um usuário com email vazio
	 * @throws Exception
	 */
	@Test
	public void test005() throws Exception {
		exceptionRule.expect(CadastroInvalidoException.class);
		exceptionRule.expectMessage("Email inválido");
		fachada.createProfile("joao", "1223", "maria", "", "Feminino", "01/01/2000", "biografia");
	}

	/**
	 * Testa se é possível criar um usuário com email nulo
	 * @throws Exception
	 */
	@Test
	public void test006() throws Exception {
		exceptionRule.expect(CadastroInvalidoException.class);
		exceptionRule.expectMessage("Email inválido");
		fachada.createProfile("joao", "1223", "maria", null, "Feminino", "01/01/2000", "biografia");
	}

	/**
	 * Testa se é possível criar um usuário com sexo vazio
	 * @throws Exception
	 */
	@Test
	public void test007() throws Exception {
		exceptionRule.expect(CadastroInvalidoException.class);
		exceptionRule.expectMessage("Sexo inválido");
		fachada.createProfile("joao", "1223", "maria", "joao@joao.com", "", "01/01/2000", "biografia");
	}

	/**
	 * Testa se é possível criar um usuário com sexo nulo
	 * @throws Exception
	 */
	@Test
	public void test008() throws Exception {
		exceptionRule.expect(CadastroInvalidoException.class);
		exceptionRule.expectMessage("Sexo inválido");
		fachada.createProfile("joao", "1223", "maria", "joao@joao.com", null, "01/01/2000", "biografia");
	}

	/**
	 * Testa se é possível criar um usuário com sexo diferente dos valores predefinidos
	 * @throws Exception
	 */
	@Test
	public void test009() throws Exception {
		exceptionRule.expect(CadastroInvalidoException.class);
		exceptionRule.expectMessage("Sexo inválido");
		fachada.createProfile("joao", "1223", "maria", "joao@joao.com", "qualquer coisa", "01/01/2000", "biografia");
	}

	/**
	 * Testa se é possível criar um usuário com login já existente
	 * @throws Exception
	 */
	@Test
	public void test010() throws Exception {
		fachada.clearPersistence();
		exceptionRule.expect(CadastroInvalidoException.class);
		exceptionRule.expectMessage("Login existente");
		
		fachada.createProfile("mariasilva", "qwe2", "Maria Silva", "maria@gmail.com", "Feminino", "01/01/1980",
				"Eu sou eu");


		fachada.createProfile("mariasilva", "qwe2", "Maria Silva", "maria2@gmail.com", "Feminino", "01/01/1980",
				"Eu sou eu");
	}

	/**
	 * Testa se é possível criar um usuário com email já existente
	 * @throws Exception
	 */
	@Test
	public void test011() throws Exception {
		fachada.clearPersistence();
		fachada.createProfile("mariasilva", "qwe2", "Maria Silva", "maria@gmail.com", "Feminino", "01/01/1980",
				"Eu sou eu");

		exceptionRule.expect(CadastroInvalidoException.class);
		exceptionRule.expectMessage("Email existente");
		fachada.createProfile("mariasilva2", "qwe2", "Maria Silva", "maria@gmail.com", "Feminino", "01/01/1980",
				"Eu sou eu");
	}
	
	/**
	 * Testa se a recuperação de dados pessoais checa login invalido
	 * @throws Exception
	 */
	@Test
	public void test012() throws Exception {
		fachada.clearPersistence();

		exceptionRule.expect(PerfilNaoEncontradoException.class);
		exceptionRule.expectMessage("Perfil não encontrado");
		fachada.getProfileInformation("mariasilva", "nomeExibicao");
	}

	/**
	 * Testa se a recuperação de dados pessoais checa login invalido
	 * @throws Exception
	 */
	@Test
	public void test013() throws Exception {
		fachada.clearPersistence();

		exceptionRule.expect(PerfilNaoEncontradoException.class);
		exceptionRule.expectMessage("Perfil não encontrado");
		fachada.getProfileInformation(null, "nomeExibicao");
	}
	
	/**
	 * Testa se a recuperação de dados pessoais checa atributo invalido
	 * @throws Exception
	 */
	@Test
	public void test014() throws Exception {
		fachada.clearPersistence();
		fachada.createProfile("mariasilva", "qwe2", "Maria Silva", "maria@gmail.com", "Feminino", "01/01/2000",
				"Eu sou eu");
		
		exceptionRule.expect(AtributoInvalidoException.class);
		exceptionRule.expectMessage("Atributo inválido");
		fachada.getProfileInformation("mariasilva", "");
	}
	
	/**
	 * Testa se a recuperação de dados pessoais checa atributo invalido
	 * @throws Exception
	 */
	@Test
	public void test015() throws Exception {
		fachada.clearPersistence();
		fachada.createProfile("mariasilva", "qwe2", "Maria Silva", "maria@gmail.com", "Feminino", "01/01/2000",
				"Eu sou eu");
		
		exceptionRule.expect(AtributoInvalidoException.class);
		exceptionRule.expectMessage("Atributo inválido");
		fachada.getProfileInformation("mariasilva", null);
	}
	
	/**
	 * Testa o processo de recuperação dos dados pessoais de um usuário
	 * @throws Exception
	 */
	@Test
	public void test016() throws Exception {
		fachada.clearPersistence();
		fachada.createProfile("mariasilva", "qwe2", "Maria Silva", "maria@gmail.com", "Feminino", "01/01/2000",
				"Eu sou eu");
		fachada.createProfile("sicrano", "1235", "Sicrano na Silva", "sicrano@gmail.com", "Masculino", "01/01/1980",
				"Eu sou outra pessoa");

		assertEquals("Maria Silva", fachada.getProfileInformation("mariasilva", "nomeExibicao"));
		assertEquals("maria@gmail.com", fachada.getProfileInformation("mariasilva", "email"));
		assertEquals("Feminino", fachada.getProfileInformation("mariasilva", "sexo"));
		assertEquals("01/01/2000", fachada.getProfileInformation("mariasilva", "dataNascimento"));
		assertEquals("Eu sou eu", fachada.getProfileInformation("mariasilva", "biografia"));

		assertEquals("Sicrano na Silva", fachada.getProfileInformation("sicrano", "nomeExibicao"));
		assertEquals("sicrano@gmail.com", fachada.getProfileInformation("sicrano", "email"));
		assertEquals("Masculino", fachada.getProfileInformation("sicrano", "sexo"));
		assertEquals("01/01/1980", fachada.getProfileInformation("sicrano", "dataNascimento"));
		assertEquals("Eu sou outra pessoa", fachada.getProfileInformation("sicrano", "biografia"));
	}

}
