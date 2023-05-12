package model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Animal {
	private int id_animal;
	private String nome;
	private String raca;
	private String porte;
	private char sexo;
	private int idade;
	private String categoria;
	private String fk_ong_cnpj;
	
	public Animal() {
		id_animal = "";
		nome = "";
		raca = "";
		porte = "";
		sexo = "";
		fk_ong_cnpj = "";
		idade = "";
		categoria = "";
	}

	public Animal (int id_animal, String nome, String raca, String porte, char sexo, String fk_ong_cnpj, int idade, String categoria){
		setId_animal(id_animal);
		setNome(nome);
		setRaca(raca);
		setPorte(porte);
		setSexo(sexo);
		setFk_ong_cnpj(fk_ong_cnpj);
		setIdade(idade);
		setCategoria(categoria)
	}		
	
	public int getId_animal() {
		return id_animal;
	}

	public void setId_animal(int id_animal) {
		this.id_animal = id_animal;
	}

	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getRaca() {
		return raca;
	}

	public void setRaca(String raca) {
		this.raca = raca;
	}

	public String getPorte() {
		return porte;
	}
	
	public void setPorte(String porte) {
		this.porte = porte;
	}

	public char getSexo() {
		return sexo;
	}
	
	public void setSexo(char sexo) {
		this.sexo = sexo;
	}

	public String getFk_ong_cnpj() {
		return fk_ong_cnpj;
	}
	
	public void setFk_ong_cnpj(String fk_ong_cnpj) {
		this.fk_ong_cnpj = fk_ong_cnpj;
	}

	public int getIdade() {
		return idade;
	}
	
	public void setIdade(int idade) {
		this.idade = idade;
	}
	
	public String getCategoria() {
		return categoria;
	}
	
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	@Override
	public String toString() {
		return "Nome: "  + nome + "   Raça:" + raca + "   Porte:" + porte + "   Sexo: "
				+ sexo  + "   CNPJ da ONG " + fk_ong_cnpj + "   Idade " + idade + "Categoria: " 
				+ categoria +"ID do animal " + id_animal;
	}
	
	@Override
	public boolean equals(Object obj) {
		return (this.getID() == ((animal) obj).getID());
	}	
}
