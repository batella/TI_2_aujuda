package model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Usuario {
	private String cpf;
	private String nome;
	private String sobrenome;
	private String username;
	private String telefone;
	private String cidade;
	
	public Usuario() {
		this.cpf = "";
		this.nome = "";
		this.sobrenome = "";
		this.username = "";
		this.telefone = "";
		this.cidade = "";
	}
	
	public Usuario(String cpf, String nome, String sobrenome, String username, String telefone, String cidade) {
		setCpf(cpf);
		setNome(nome);
		setSobrenome(sobrenome);
		setUsername(username);
		setTelefone(telefone);
		setCidade(cidade);
	}

	
	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}


	@Override
	public String toString() {
		return "Nome: " + nome + "   Sobrenome:" + sobrenome + "   Username.: " + username + "   Telefone: "
				+ telefone  + "   Cidade: " + cidade;
	}
	
	@Override
	public boolean equals(Object obj) {
		return (this.getID() == ((Produto) obj).getID());
	}	
}