package br.edu.ifal.proo.modelo;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Perfil {
	private String id;
	private String login;
	private String senha;
	private String nomeExibicao;
	private String email;
	private String sexo;
	private String dataNascimento;
	private String biografia;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getNomeExibicao() {
		return nomeExibicao;
	}
	public void setNomeExibicao(String nomeExibicao) {
		this.nomeExibicao = nomeExibicao;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public String getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	
	public int getIdade(String dataNascimento) {
		try {
			//Verifica se a data � veridica
			SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
			Date d = f.parse(dataNascimento);
			
			//Transforma data de nascimento
			DateTimeFormatter f1 = DateTimeFormatter.ofPattern("dd/MM/yyyy"); 
			LocalDate d1 = LocalDate.parse(dataNascimento, f1); 
			
			SimpleDateFormat f2 = new SimpleDateFormat("yyyy");
			Date d2 = f2.parse(d1.toString());
			String data_nascimento = f2.format(d2);
			
			//Pega Data do Sistema!
			SimpleDateFormat f3 = new SimpleDateFormat("y");
			Date d3 = new Date();
			String data_atual = f3.format(d3);
			
			int data_nascimento1 = Integer.parseInt(data_nascimento);
			int data_atual1 = Integer.parseInt(data_atual);
			
			int idade = data_atual1 - data_nascimento1;
			return idade;
			
		} catch (Exception e) {
			return -1;
		}
	}
	public String getBiografia() {
		return biografia;
	}
	public void setBiografia(String biografia) {
		this.biografia = biografia;
	}
	
}
