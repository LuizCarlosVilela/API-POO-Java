package br.edu.ifal.proo.modelo;

public class Comentario {
	private String id_comentario;
	private String conteudo;
	private String id_post;
	private String login;
	public String getId_comentario() {
		return id_comentario;
	}
	public void setId_comentario(String id_comentario) {
		this.id_comentario = id_comentario;
	}
	public String getConteudo() {
		return conteudo;
	}
	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}
	public String getId_post() {
		return id_post;
	}
	public void setId_post(String id_post) {
		this.id_post = id_post;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	
	

}
