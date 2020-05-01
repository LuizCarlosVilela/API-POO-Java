package br.edu.ifal.proo;

import java.util.List;

import br.edu.ifal.proo.dao.BlogDAO;
import br.edu.ifal.proo.dao.ComentarioDAO;
import br.edu.ifal.proo.dao.DeleteDAO;
import br.edu.ifal.proo.dao.PerfilDAO;
import br.edu.ifal.proo.dao.PostDAO;
import br.edu.ifal.proo.excecoes.AtributoInvalidoException;
import br.edu.ifal.proo.excecoes.BlogNaoEncontradoException;
import br.edu.ifal.proo.excecoes.CadastroInvalidoException;
import br.edu.ifal.proo.excecoes.FalhaAutenticacaoException;
import br.edu.ifal.proo.excecoes.FalhaAutorizacaoException;
import br.edu.ifal.proo.excecoes.PerfilNaoEncontradoException;
import br.edu.ifal.proo.excecoes.PostNaoEncontradoException;
import br.edu.ifal.proo.modelo.Blog;
import br.edu.ifal.proo.modelo.Comentario;
import br.edu.ifal.proo.modelo.Perfil;
import br.edu.ifal.proo.modelo.Post;

public class Fachada {

	/**
	 * Limpa os dados existentes no banco de dados
	 */
	public void clearPersistence() {
		DeleteDAO clear = new DeleteDAO();
		clear.clearAll();
	}

	/**
	 * Cria um perfil de usuario.
	 * 
	 * @param login
	 * @param senha
	 * @param nomeExibicao
	 * @param email
	 * @param sexo
	 * @param dataNascimento
	 * @param biografia
	 * 
	 * 
	 *    Validacoes: 
	 *    		- login nao pode ser nulo ou vazio 
	 * 			- login deve ser unico 
	 * 			- a senha não pode ser nula ou vazia 
	 * 			- email não pode ser nulo ou vazio 
	 * 			- sexo deve assumir um dos seguintes valores 'Feminino', 'Masculino' ou 'Não
	 *                       informado' 
     * 			- data de nascimento deve ser um valor
	 *          válido e no momento do cadastro do usuário deve ter
	 *          mais de 16 anos
	 * 
	 * 
	 */
	public void createProfile(String login, 
			String senha, String nomeExibicao, 
			String email, String sexo,
			String dataNascimento, 
			String biografia) throws CadastroInvalidoException {
		
		Perfil perfil = new Perfil();
		PerfilDAO perfilDAO = new PerfilDAO();
		
		if(login == null || login.equals("")) {
			throw new CadastroInvalidoException("Login inválido");
		}
		else if(senha == null || senha.equals("")) {
			throw new CadastroInvalidoException("Senha inválida");
		}
		else if(email == null || email.equals("")) {
			throw new CadastroInvalidoException("Email inválido");
		}
		else if(sexo == null || sexo.equals("")) {
			throw new CadastroInvalidoException("Sexo inválido");
		}
		else if(!(sexo.equals("Masculino") || sexo.equals("Feminino") || sexo.equals("Não informado"))) {
			throw new CadastroInvalidoException("Sexo inválido");
		}
		else if(perfil.getIdade(dataNascimento) < 16) {
			throw new CadastroInvalidoException("Data de nascimento inválida"); 
		}
		else if(perfilDAO.checkLogin(login) == true) {
			throw new CadastroInvalidoException("Login existente");
		}
		else if(perfilDAO.checkEmail(email) == true) {
			throw new CadastroInvalidoException("Email existente");
		}
		else {
			
			Perfil p = new Perfil();
			p.setLogin(login);
			p.setSenha(senha);
			p.setNomeExibicao(nomeExibicao);
			p.setEmail(email);
			p.setSexo(sexo);
			p.setDataNascimento(dataNascimento);
			p.setBiografia(biografia);
			
			perfilDAO.insert(p);
		}
	}

	/**
	 * Retorna os dados pessoais do usuario dado o campo passado. Os atributos possíveis são:
	 *    - nomeExibicao
	 *    - email
	 *    - sexo
	 *    - dataNascimento
	 *    - biografia
	 * 
	 * @param login
	 * @param atributo
	 * @return
	 */
	public String getProfileInformation(String login, String atributo) throws AtributoInvalidoException, PerfilNaoEncontradoException {
		PerfilDAO perfilDAO = new PerfilDAO();
		
		if(perfilDAO.checkLogin(login) == false) {
			throw new PerfilNaoEncontradoException("Perfil não encontrado");
		}
		else if(perfilDAO.checkAtributo(atributo) == false) {
			throw new AtributoInvalidoException("Atributo inválido");
		}
		else {
			String campo = perfilDAO.getAtributo(login, atributo);
			return campo;
		}
		
	}

	/**
	 * Altera o perfil do usuário
	 * @param login do do usuário
	 * @param senha senha do usuário
	 * @param atributo a ser modificado
	 * @param valor novo valor do atributo
	 * @throws CadastroInvalidoException lançada se o novo valor for inválido
	 * @throws AtributoInvalidoException lançada se o atributo referenciado for inexistente
	 * @throws FalhaAutenticacaoException lançada se o par login/senha não conseguir autenticar o usuário
	 */
	public void changeProfileInformation(String login, String senha, String atributo, String valor) throws CadastroInvalidoException, AtributoInvalidoException, FalhaAutenticacaoException {
		PerfilDAO perfilDAO = new PerfilDAO();
		Perfil perfil = new Perfil();
		
		if(perfilDAO.fazerLogin(login, senha) == false) {
			throw new FalhaAutenticacaoException("Login/senha incorretos");
		}
		else if(perfilDAO.checkAtributo(atributo) == false) {
			throw new AtributoInvalidoException("Atributo inválido");
		}
		
		else if(atributo.equals("senha")) {
			if(valor == null || valor.equals("")) {
				throw new CadastroInvalidoException("Senha inválida");
			}
			else {
				perfilDAO.alterSenha(login, senha, valor);
			}
		}
		
		else if(atributo.equals("email")) {
			if(valor == null || valor.equals("")) {
				throw new CadastroInvalidoException("Email inválido");
			}
			else if(perfilDAO.checkEmail(valor) == true) {
				throw new CadastroInvalidoException("Email existente");
			}
			else {
				perfilDAO.alterEmail(login, senha, valor);
			}
		}
		
		else if(atributo.equals("sexo")) {
			if(valor == null || valor.equals("")) {
				throw new CadastroInvalidoException("Sexo inválido");
			}
			else if(!(valor.equals("Masculino") || valor.equals("Feminino") || valor.equals("Não informado"))) {
				throw new CadastroInvalidoException("Sexo inválido");
			}
			else {
				perfilDAO.alterSexo(login, senha, valor);
			}
		}
		
		else if(atributo.equals("login")) {
			if(valor == null || valor.equals("")) {
				throw new CadastroInvalidoException("Login inválido");
			}
			else if(perfilDAO.checkLogin(valor) == true) {
				throw new CadastroInvalidoException("Login existente");
			}
			else {
				perfilDAO.alterLogin(login, senha, valor);
			}
		}
		
		else if(atributo.equals("dataNascimento")) {
			if(valor == null || valor.equals("")) {
				throw new CadastroInvalidoException("Data de nascimento inválida");
			}
			else if(perfil.getIdade(valor) < 16) {
				throw new CadastroInvalidoException("Data de nascimento inválida");
			}
			else {
				perfilDAO.alterDataNascimento(login, senha, valor);
			}
		}
		
		else if(atributo.equals("biografia")) {
			if(valor == null || valor.equals("")) {
				throw new CadastroInvalidoException("Biografia inválida");
			}
			else {
				perfilDAO.alterBiografia(login, senha, valor);
			}
		}
		else if(atributo.equals("nomeExibicao")) {
			if(valor == null || valor.equals("")) {
				throw new CadastroInvalidoException("Nome de exibicao inválido");
			}
			else {
				perfilDAO.alterNomeExibicao(login, senha, valor);
				
			}
		}
		
		
	}

	/**
	 * Cria um novo blog
	 *  
	 * @param login do do usuário
	 * @param senha senha do usuário
	 * @param titulo do novo blog
	 * @param descricao do novo blog
	 * @return id do novo blog gerado
	 * @throws FalhaAutenticacaoException lançada se o par login/senha não conseguir autenticar o usuário
	 * @throws CadastroInvalidoException lançada se os valores de cadastro do blog estiverem inválidos
	 */
	public String createBlog(String login, 
			String senha, 
			String titulo, 
			String descricao) throws FalhaAutenticacaoException, CadastroInvalidoException {
		
		PerfilDAO perfilDAO = new PerfilDAO();
		
		if(perfilDAO.fazerLogin(login, senha) == false) {
			throw new FalhaAutenticacaoException("Login/senha incorretos");
		}
		else if(titulo == null || titulo.equals("")) {
			throw new CadastroInvalidoException("Dados do blog incorretos");
		}
		else if(descricao == null || descricao.equals("")) {
			throw new CadastroInvalidoException("Dados do blog incorretos");
		}
		else {
			Blog blog = new Blog();
			BlogDAO blogDAO = new BlogDAO();
			
			blog.setDono(login);
			blog.setTitulo(titulo);
			blog.setDescricao(descricao);
			
			int id_perfil = Integer.parseInt(perfilDAO.getId(login, senha));
			blog.setId_perfil(id_perfil);
			
			String id = blogDAO.insert(blog);
			return id;
		}	
	}

	/**
	 * Retorna os dados de um blog por atributo. Os atributos possíveis são:
	 *   - titulo
	 *   - descricao
	 *   - dono
	 *   
	 * @param idBlog id do blog a ser recuperado
	 * @param atributo atributo buscado
	 * @return propriedade buscada
	 * @throws AtributoInvalidoException lançada se o atributo referenciado for inexistente
	 */
	public String getBlogInformation(String idBlog, String atributo) throws AtributoInvalidoException {
		
		BlogDAO blogDAO = new BlogDAO();
		
		if(blogDAO.checkAtributo(atributo) == false) {
			throw new AtributoInvalidoException("Atributo inválido");
		}
		else {
			String campo = blogDAO.getCampo(atributo, idBlog);
			return campo;
		}
		
	}

	/**
	 * Modifica os dados do blog
	 * @param login do do usuário
	 * @param senha senha do usuário
	 * @param blogId id do blogo a ser modificado
	 * @param atributo atributo a ser modificado
	 * @param valor novo valor a ser modificado
	 * @throws FalhaAutenticacaoException lançada se o par login/senha não conseguir autenticar o usuário
	 * @throws AtributoInvalidoException lançada se o atributo referenciado for inexistente
	 * @throws CadastroInvalidoException lançada se os valores de cadastro do blog estiverem inválidos
	 * @throws FalhaAutorizacaoException lançada se o blog não pertencer ao usuário informado
	 */
	public void changeBlogInformation(String login, String senha, String blogId, String atributo, String valor) throws FalhaAutenticacaoException, AtributoInvalidoException, CadastroInvalidoException, FalhaAutorizacaoException {
		
		
		
		PerfilDAO perfilDAO = new PerfilDAO();
		BlogDAO blogDAO = new BlogDAO();
		
		if(perfilDAO.fazerLogin(login, senha) == false) {
			throw new FalhaAutenticacaoException("Login/senha incorretos");
		}
		else if(blogId == null || blogId.equals("")) {
			throw new FalhaAutorizacaoException("Acesso não autorizado");
		}
		else if(blogDAO.checkDono(login, blogId) == false) {
			throw new FalhaAutorizacaoException("Acesso não autorizado");
		}
		
		else if(blogDAO.checkAtributo(atributo) == false) {
			throw new AtributoInvalidoException("Atributo inválido");
		}
		
		else if(atributo.equals("titulo")) {
			if(valor == null || valor.equals("")) {
				throw new CadastroInvalidoException("Título inválido");
			}
			else {
				blogDAO.alterTitulo(login, blogId, valor);
			}
		}
		else if(atributo.equals("descricao")) {
			
			if(valor == null || valor.equals("")) {
				throw new CadastroInvalidoException("Descrição inválida");
			}
			else {
				blogDAO.alterDescricao(login, blogId, valor);
			}
		}
	}
	
	/**
	 * Realiza o cadastro de um novo post
	 * @param login do do usuário
	 * @param senha senha do usuário
	 * @param blogId id do blogo a ser modificado
	 * @param titulo título do novo post
	 * @param conteudo conteúdo do novo post
	 * @return id do post criado
	 * @throws FalhaAutenticacaoException lançada se o par login/senha não conseguir autenticar o usuário
	 * @throws CadastroInvalidoException lançada se os valores de cadastro do post estiverem inválidos
	 * @throws FalhaAutorizacaoException lançada se o blog não pertencer ao usuário informado
	 */
	public String createPost(
			String login, String senha, String blogId, 
			String titulo, String conteudo) throws FalhaAutenticacaoException, FalhaAutorizacaoException, CadastroInvalidoException {
		
		PerfilDAO perfilDAO = new PerfilDAO();
		BlogDAO blogDAO = new BlogDAO();
		
		if(perfilDAO.fazerLogin(login, senha) == false) {
			throw new FalhaAutenticacaoException("Login/senha incorretos");
		}
		else if(blogDAO.checkDono(login, blogId) == false) {
			throw new FalhaAutorizacaoException("Acesso não autorizado");
		}
		else if(titulo == null || titulo.equals("")) {
			throw new CadastroInvalidoException("Dados do post incorretos");
		}
		else if(conteudo == null || conteudo.equals("")) {
			throw new CadastroInvalidoException("Dados do post incorretos");
		}
		else {
			PostDAO postDAO = new PostDAO();
			Post post = new Post();
			
			post.setTitulo(titulo);
			post.setConteudo(conteudo);
			post.setId_blog(blogId);
			post.setDataCriacao();
			
			String id_post = postDAO.insert(post);
			return id_post;
		}
	}
	
	
	/**
	 * Retorna os atributos de um post. Os atributos possíveis são:
	 *    - titulo
	 *    - conteudo
	 *    - dataCriacao (formato => dd/MM/yyyy)
	 * 
	 * @param postId
	 * @param atributo
	 * @return
	 * @throws AtributoInvalidoException lançada se o atributo é inválido
	 * @throws FalhaAutorizacaoException lançada se o id é inválido
	 */
	public String getPostInformation(String postId, String atributo) throws AtributoInvalidoException, FalhaAutorizacaoException {
		PostDAO postDAO = new PostDAO();
		
		if(postDAO.checkAtributo(atributo) == false) {
			throw new AtributoInvalidoException("Atributo inválido");
		}
		else if(postDAO.checkId(postId) == false) {
			throw new FalhaAutorizacaoException("Id inválido");
		}
		else {
			String valor = postDAO.getCampo(postId, atributo);
			return valor;
		}
	}
	
	/**
	 * Permite a modificação dos dados de um post
	 * @param login do do usuário
	 * @param senha senha do usuário
	 * @param postId id do post a ser modificado
	 * @param atributo nome do atributo a ser modificado. Pode ser 'titulo' ou 'conteudo'
	 * @param valor novo valor do atributo
	 * @throws FalhaAutenticacaoException lançada se o par login/senha não conseguir autenticar o usuário
	 * @throws AtributoInvalidoException lançada se o atributo referenciado for inexistente
	 * @throws CadastroInvalidoException lançada se os valores de cadastro do post estiverem inválidos
	 * @throws FalhaAutorizacaoException lançada se o post não pertencer a um blog de propriedade do usuário
	 */
	public void changePostInformation(String login, String senha, String postId, String atributo, String valor) throws FalhaAutenticacaoException, FalhaAutorizacaoException, AtributoInvalidoException, CadastroInvalidoException {
		
		
		PerfilDAO perfilDAO = new PerfilDAO();
		PostDAO postDAO = new PostDAO();
		
		if(perfilDAO.fazerLogin(login, senha) == false) {
			throw new FalhaAutenticacaoException("Login/senha incorretos");
		}
		else if(postDAO.checkPostBlogPerfil(postId, login) == false) {
			throw new FalhaAutorizacaoException("Acesso não autorizado");
		}
		else if(postDAO.checkAtributo(atributo) == false) {
			throw new AtributoInvalidoException("Atributo inválido");
		}
		else if(atributo.equals("titulo")) {
			if(valor == null || valor.equals("")) {
				throw new CadastroInvalidoException("Titulo inválido");
			}
			else {
				postDAO.alterTitulo(postId, valor);
			}
		}
		else if(atributo.equals("conteudo")) {
			if(valor == null || valor.equals("")) {
				throw new CadastroInvalidoException("Conteúdo inválido");
			}
			else {
				postDAO.alterConteudo(postId, valor);
			}
		}
		
	}

	/**
	 * Retorna o número de blogs de um dado usuário
	 * @param login
	 * @return o número de blogs
	 * @throws PerfilNaoEncontradoException lançada caso não exista um usuário com 
	 * o login definido
	 */
	public int getNumberOfBlogsByLogin(String login) throws PerfilNaoEncontradoException {
		PerfilDAO perfilDAO = new PerfilDAO();
		BlogDAO blogDAO = new BlogDAO();
		
		if(perfilDAO.checkLogin(login) == false) {
			throw new PerfilNaoEncontradoException("Perfil não encontrado");
		}
		
		else {
			int quantidade = blogDAO.getBlogPerfil(login);
			return quantidade;
		}
		
	}
	
	/**
	 * Retorna o id do blog na posição 'index'. 
	 * @param login login do usuário a ser buscado
	 * @param index posição do blog
	 * @return id do blog encontrado
	 * @throws PerfilNaoEncontradoException lançada o login passado não exista 
	 * @throws AtributoInvalidoException lançada caso o índice passado não se 
	 * refira a um login válido
	 */
	public String getBlogByLogin(String login, int index) throws PerfilNaoEncontradoException, AtributoInvalidoException {
		PerfilDAO perfilDAO = new PerfilDAO();
		
		List<String> lista = perfilDAO.getIdBlogPerfilIndex(login);
		if(perfilDAO.checkLogin(login) == false) {
			throw new PerfilNaoEncontradoException("Perfil não encontrado");
		}
		else if(lista.size() < index) {
			throw new AtributoInvalidoException("Atributo inválido");
		}
		else {
			String id_blog = lista.get(index);
			return id_blog;
		}
	}
	
	/**
	 * Retorna o id do post na posição 'index'
	 * @param blogId id do blog a ser buscado
	 * @param index índice do post a ser buscado
	 * @return id do post encontrado
	 * @throws BlogNaoEncontradoException lançada caso o blog não exista
	 * @throws AtributoInvalidoException lançada caso o índice passado não se refira
	 * a um post válido
	 */
	public String getPostByBlog(String blogId, int index) throws BlogNaoEncontradoException, AtributoInvalidoException {
		BlogDAO blogDAO = new BlogDAO();
		
		List<String> lista = blogDAO.getPostBlog(blogId);
		
		if(blogDAO.checkBlog(blogId) == false) {
			throw new BlogNaoEncontradoException("Blog não existe");
		}
		else if(lista.size() < index) {
			throw new AtributoInvalidoException("Index inválido");
		}
		else {
			String id_blog = lista.get(index);
			return id_blog;
		}
	}
	
	/**
	 * Retorna o número de posts de um dado blog
	 * @param blogId id do blog buscado
	 * @return número de posts do blog
	 * @throws BlogNaoEncontradoException lançada caso o blog informado não exista
	 */
	public int getNumberOfPosts(String blogId) throws BlogNaoEncontradoException {
		BlogDAO blogDAO = new BlogDAO();
		
		if(blogDAO.checkBlog(blogId) == false) {
			throw new BlogNaoEncontradoException("Blog não existe");
		}else {
			
			int quantidade = blogDAO.getPostQuant(blogId);
			return quantidade;
		}
	}

	/**
	 * Retorna o id do comentario na posição 'index'
	 * @param postId id do post a ser buscado
	 * @param index índice do comentário a ser buscado
	 * @return id do comentário encontrado
	 * @throws PostNaoEncontradoException lançada caso o post não exista
	 * @throws AtributoInvalidoException lançada caso o índice passado não se refira
	 * a um comentário válido
	 */

	public String getCommentByPost(String postId, int index) throws PostNaoEncontradoException, AtributoInvalidoException {
		PostDAO postDAO = new PostDAO();
		List<String> lista = postDAO.getComPost(postId);
		
		if(postDAO.checkId(postId) == false) {
			throw new PostNaoEncontradoException("Post não existe");
		}
		else if(lista.size() < index) {
			throw new AtributoInvalidoException("Index inválido");
		}
		else {
			String id_comentario = lista.get(index);
			return id_comentario;
		}
	}
	
	/**
	 * Retorna o número de comentários de um post
	 * @param postId id do post
	 * @return número de comentários
	 * @throws PostNaoEncontradoException lançada caso o post não seja encontrado
	 */
	public int getNumberOfComments(String postId) throws PostNaoEncontradoException {
		PostDAO postDAO = new PostDAO();
		
		if(postDAO.checkId(postId) == false) {
			throw new PostNaoEncontradoException("Post não existe");
		}else {
			int quantidade = postDAO.getComPostQuant(postId);
			return quantidade;
		}
	}
	
	/**
	 * Adiciona comentário a um post
	 * @param login do usuário que postou o comentário
	 * @param senha do comentário que postou o comentáio
	 * @param postId id do post de origem
	 * @param conteudo a ser postado
	 * @return id do novo comentário
	 * @throws FalhaAutenticacaoException lançada caso não seja possível validar 
	 * a autenticação do usuário
	 * @throws FalhaAutorizacaoException lançada caso não seja possível encontrar o post informado
	 * @throws CadastroInvalidoException lançada caso o conteúdo do comentário seja vazio
	 */
	public String addComment(String login, String senha, String postId, String conteudo) throws FalhaAutenticacaoException, FalhaAutorizacaoException, CadastroInvalidoException {
		PerfilDAO perfilDAO = new PerfilDAO();
		PostDAO postDAO = new PostDAO();
		
		if(perfilDAO.fazerLogin(login, senha) == false) {
			throw new FalhaAutenticacaoException("Login/senha incorretos");
		}
		else if(postDAO.checkId(postId) == false) {
			throw new FalhaAutorizacaoException("Post não encontrado");
		}
		else if(conteudo == null || conteudo.equals("")) {
			throw new CadastroInvalidoException("Conteudo inválido!");
		}
		else {
			Comentario comentario = new Comentario();
			ComentarioDAO comentarioDAO = new ComentarioDAO();
			
			comentario.setConteudo(conteudo);
			comentario.setId_post(postId);
			comentario.setLogin(login);
			
			String id = comentarioDAO.insert(comentario);
			return id;	
		}
	}
	
	/**
	 * Retorna o texto de um comentário
	 * @param commentId id do comentário a ser recuperado
	 * @return conteúdo do comentário
	 * @throws FalhaAutorizacaoException lançada caso o id fornecido seja inválido.
	 */
	public String getCommentText(String commentId) throws FalhaAutorizacaoException {
		ComentarioDAO comentarioDAO = new ComentarioDAO();
		
		if(comentarioDAO.checkId(commentId) == false) {
			throw new FalhaAutorizacaoException("Acesso negado");
		}
		else {
			String conteudo = comentarioDAO.getConteudo(commentId);
			return conteudo;
		}
	}
	
	/**
	 * Retorna o login do usuário que postou o comentário
	 * @param commentId id do comentário a ser recuperado
	 * @return login do usuário que postou o comentário
	 * @throws FalhaAutorizacaoException lançada caso o id fornecido seja inválido.
	 */
	public String getCommentAuthor(String commentId) throws FalhaAutorizacaoException {
		ComentarioDAO comentarioDAO = new ComentarioDAO();
		
		if(comentarioDAO.checkId(commentId) == false) {
			throw new FalhaAutorizacaoException("Acesso negado");
		}
		else {
			String login = comentarioDAO.getLogin(commentId);
			return login;
		}
	}

	/**
	 * Deleta um post pertencendo a um blog do usuario. Deleta todos os comentarios 
	 * relacionados
	 * @param login do dono do blog
	 * @param senha do dono do blog
	 * @param postId id do post a ser deletado
	 * @throws FalhaAutenticacaoException lançada caso não seja possível autenticar o 
	 * usuário
	 * @throws FalhaAutorizacaoException lançada caso o id informado não pertença a um 
	 * blog cujo proprietário é o usuário informado
	 */
	public void deletePost(String login, String senha, String postId) throws FalhaAutenticacaoException, FalhaAutorizacaoException {
		PerfilDAO perfilDAO = new PerfilDAO();
		PostDAO postDAO = new PostDAO();
		
		if (perfilDAO.fazerLogin(login, senha) == false) {
			throw new FalhaAutenticacaoException("Login/senha incorretos");
		}
		else if(postDAO.checkId(postId) == false) {
			throw new FalhaAutorizacaoException("Acesso negado");
		}
		else if(postDAO.checkPostBlogPerfil(postId, login) == false) {
			throw new FalhaAutorizacaoException("Acesso negado");
		}
		else {
			DeleteDAO delete = new DeleteDAO();
			delete.deleteFromPostComent(postId);
		}
		
	}
	
	/**
	 * Deleta um blog pertencendo a um usuario. Deleta todos os posts e comentarios 
	 * relacionados
	 * @param login do dono do blog
	 * @param senha do dono do blog
	 * @param blogId id do post a ser deletado
	 * @throws FalhaAutenticacaoException lançada caso não seja possível autenticar o 
	 * usuário
	 * @throws FalhaAutorizacaoException lançada caso o id informado não pertença a um 
	 * blog cujo proprietário é o usuário informado
	 */
	public void deleteBlog(String login, String senha, String blogId) throws FalhaAutenticacaoException, FalhaAutorizacaoException {
		BlogDAO blogDAO = new BlogDAO();
		PerfilDAO perfilDAO = new PerfilDAO();
		
		if(perfilDAO.fazerLogin(login, senha) == false) {
			throw new FalhaAutenticacaoException("Login/senha inválidos");
		}
		else if(blogId == null || blogId.equals("")) {
			throw new FalhaAutenticacaoException("Blog inválido");
		}
		else if(blogDAO.checkDono(login, blogId) == false) {
			throw new FalhaAutenticacaoException("Acesso negado");
		}
		else {
			DeleteDAO delete = new DeleteDAO();
			delete.deleteFromBlogPostComent(blogId);
		}
	}
	
	/**
	 * Deleta o perfil de um usuário. Deve-se deletado em cascata:
	 *    - Os blogs do usuário
	 *    - Os posts do blog e comentário
	 *    - Os comentários em posts de outros usuários onde o usuário comentou 
	 * @param login
	 * @param senha
	 * @throws FalhaAutenticacaoException
	 */
	public void deleteProfile(String login, String senha) throws FalhaAutenticacaoException, PerfilNaoEncontradoException {
		
		PerfilDAO perfilDAO = new PerfilDAO();
		if(login == null || login.equals("")) {
			throw new FalhaAutenticacaoException("Login/senha incorretos");
		}
		else if(senha == null || senha.equals("")) {
			throw new FalhaAutenticacaoException("Login/senha incorretos");
		}
		else if(perfilDAO.fazerLogin(login, senha) == false) {
			throw new PerfilNaoEncontradoException("Perfil não encontrado");
		}
		else {
			DeleteDAO delete = new DeleteDAO();
			delete.deleteFromPerfil(login);
		}
	}
	
	/**
	 * Busca por perfis de usuários que casem com o perfil. 
	 * 
	 * A busca deve ser realizada sobre os nomes de exibição dos usuários
	 * (obs.: se o nome do usuário nao estiver disponível, entao usa-se o login)
	 * 
	 * Além disso, a busca deve buscar em partes do nome do usuário. Por exemplo, suponha
	 * que no banco conste os seguintes registros:
	 *    - Maria
	 *    - Maria José da silva
	 *    - Mariana rocha
	 *    - Mario antonio
	 *    - Jose Maria
	 * , e que seja realizada uma busca a parte da string 'maria'. Neste caso deveria ser
	 * retornado uma lista contendo os logins dos usuários:
	 *    - Maria
	 *    - Maria José da silva
	 *    - Mariana rocha
	 *    - José Maria
	 *    
	 * É importante destacar que a busca deve ser case-insensitive. 
	 * @param match padrao a ser buscado
	 * @return lista dos logins dos usuários que casam na busca
	 * A lista deve estar ordenada.
	 */
	public List<String> findProfileByName(String match) {
		
		PerfilDAO perfilDAO = new PerfilDAO();
		List<String> lista = perfilDAO.getByNome(match);
		return lista;
		
	}
	
	/**
	 * Busca perfis de usuário por gênero
	 * @param gender genero a ser buscado
	 * @return lista dos logins dos usuários que casam na busca
	 * A lista deve estar ordenada.
	 */
	public List<String> findProfileByGender(String gender) {
		PerfilDAO perfilDAO = new PerfilDAO();
		List<String> lista = perfilDAO.getByNomeByGenero(gender);
		
		return lista;
	}
	
	/**
	 * 	Busca por blogs que casem com o perfil. 
	 * 
	 * A busca deve ser realizada sobre o título dos blogs.
	 * 
	 * De modo semelhante à busca de perfis, caso partes do nome casem devem ser retornado
	 * A busca deve ser case-insensitive retornando uma lista ordenada com os ids dos 
	 * blogs
	 * @param match
	 * @return
	 */
	public List<String> findBlogByName(String match) {
		BlogDAO blogDAO = new BlogDAO();
		List<String> lista = blogDAO.getBlogByNome(match);
		
		return lista;
	}
}
