package model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Ong {
	private String cnpj;
	private String nome
	private String endereco;
	private String fk_animal_id;
	private String telefone;
	private String descricao;
	
	public Ong() {
		this.cnpj = "";
		this.nome = "";
		this.endereco = "";
		this.fk_animal_id = "";
		this.telefone = "";
		this.descricao = "";
	}
	
	public Ong(String cnpj, String nome, String endereco, String fk_animal_id, String telefone, String descricao) {
		setCnpj(cnpj);
		setNome(nome);
		setEndereco(endereco);
		setFk_animal_id(fk_animal_id);
		setTelefone(telefone);
		setDescricao(descricao);
	}

	
	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
	public String getFk_animal_id() {
		return fk_animal_id;
	}

	public void setFk_animal_id(String fk_animal_id) {
		this.fk_animal_id = fk_animal_id;
	}
	
	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}


	@Override
	public String toString() {
		return "Nome: " + nome + "   Endereco:" + endereco + "   Fk_animal_id.: " + fk_animal_id + "   Telefone: "
				+ telefone  + "   Descricao: " + descricao;
	}
	
	@Override
	public boolean equals(Object obj) {
		return (this.getID() == ((Produto) obj).getID());
	}	
}