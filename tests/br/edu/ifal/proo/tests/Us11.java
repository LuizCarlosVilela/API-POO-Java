/**
 * 
 */
package br.edu.ifal.proo.tests;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import br.edu.ifal.proo.Fachada;

/**
 * User Story-- Pesquisas no blog
 * Este User story tem como objetivo permitir a buscas em geral 
 * no blog. Alguns exemplos de buscas são:
 * 
 *    - Buscar por todos os blogs de um usuários;
 *    - Buscar por todos post onde um dado usuário postou;
 *    - Buscar todos os posts que contiver um texto especificado.
 * @author ivocalado
 *
 */
public class Us11 {

	static Fachada fachada;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		fachada = new Fachada();
		fachada.clearPersistence();

		
		fachada.createProfile("sicrano", "123", "Sicrano na Silva", "sicrano@gmail.com", "Masculino", "01/01/1980",
				"Eu sou outra pessoa");
		
		fachada.createProfile("mariasilva", "123", "Maria Silva", "maria@gmail.com", "Feminino", "01/01/2000",
				"Eu sou eu");

		fachada.createProfile("josesilva", "123", "Jose maria", "jose@gmail.com", "Masculino", "01/01/1980",
				"Eu sou outra pessoa");
		
		
		//O Email estava igual ao outro, por isso não estava inserindo por conta de emails iguais
		
//		fachada.createProfile("jose", "123", null, "jose@gmail.com", "Masculino", "01/01/1980",
//				"Eu sou outra pessoa");	
		
		fachada.createProfile("jose", "123", null, "jose1@gmail.com", "Masculino", "01/01/1980",
				"Eu sou outra pessoa");
		
		
		String blogId1 = fachada.createBlog("sicrano", "123", "Meu primeiro blog",
				"Whatever is said in Latin sounds profound");
		String blogId2 = fachada.createBlog("sicrano", "123", "Meu segundo blog",
				"Whatever is said in Latin sounds profound");
		
		String postId1 = fachada.createPost("sicrano", "123",  blogId1,  "Titulo do post",  "Conteúdo do post");
		String postId2 = fachada.createPost("sicrano", "123",  blogId2,  "Titulo do post",  "Conteúdo do post");
		
		fachada.addComment("sicrano", "123", postId1, "conteudo 1");
		fachada.addComment("mariasilva", "123", postId2, "conteudo 2");
	}

	/**
	 * Testa a busca por perfis de usuários
	 */
	@Test
	public void test001() {
		List<String> lista = fachada.findProfileByName("maria");
		assertEquals("[josesilva, mariasilva]", lista.toString());
		
		lista = fachada.findProfileByName("jose");
		assertEquals("[jose, josesilva]", lista.toString());
		
		lista = fachada.findProfileByName("silva");
		assertEquals("[mariasilva, sicrano]", lista.toString());
	}
	
	/**
	 * Testa a busca por genero
	 */
	@Test
	public void test002() {
		List<String> lista = fachada.findProfileByGender("Masculino");
		assertEquals("[jose, josesilva, sicrano]", lista.toString());
		
		lista = fachada.findProfileByGender("Feminino");
		assertEquals("[mariasilva]", lista.toString());
	}
	
	/**
	 * Testa a busca por genero
	 */
	@Test
	public void test003() {
		
		List<String> lista = fachada.findBlogByName("primeiro");
		assertEquals(1, lista.size());

		lista = fachada.findBlogByName("meu");
		assertEquals(2, lista.size());

	}

}
