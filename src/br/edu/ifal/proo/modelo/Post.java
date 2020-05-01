package br.edu.ifal.proo.modelo;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Post {
	private String titulo;
	private String conteudo;
	private String id_blog;
	private String dataCriacao;
	
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getConteudo() {
		return conteudo;
	}
	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}
	public String getId_blog() {
		return id_blog;
	}
	public void setId_blog(String id_blog) {
		this.id_blog = id_blog;
	}
	public String getDataCriacao() {
		return dataCriacao;
	}
	public void setDataCriacao() {
		SimpleDateFormat f3 = new SimpleDateFormat("dd/MM/yyyy");
		Date d3 = new Date();
		String data_atual = f3.format(d3);
		
		this.dataCriacao = data_atual;

	}
}
