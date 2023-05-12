package model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Conversa {
	private int id_conversa;
	private String fk_ong_cnpj;
	private String fk_usuario_cpf;
	private String conteudo;
	private Date dataconversa;
	
	public Conversa() {
		id_conversa = "";
		fk_ong_cnpj = "";
		fk_usuario_cpf = "";
		conteudo = "";
		dataconversa = "";
	}

	public Conversa (int id_conversa, String fk_ong_cnpj, String fk_usuario_cpf, String conteudo, Date dataconversa){
		setId_conversa(id);
		setFk_ong_cnpj(fk_ong_cnpj);
		setFk_usuario_cpf(fk_usuario_cpf);
		setConteudo(conteudo);
		setDataconversa(dataconversa);
	}		
	
	public int getId_conversa() {
		return id_conversa;
	}

	public void setId_conversa(int id_conversa) {
		this.id_conversa = id_conversa;
	}

	public String getFk_ong_cnpj() {
		return fk_ong_cnpj;
	}
	
	public void setFk_ong_cnpj(String fk_ong_cnpj) {
		this.fk_ong_cnpj = fk_ong_cnpj;
	}

	public String getFk_usuario_cpf() {
		return fk_usuario_cpf;
	}
	
	public void setFk_usuario_cpf(String fk_usuario_cpf) {
		this.fk_usuario_cpf = fk_usuario_cpf;
	}


	public String getConteudo() {
		return conteudo;
	}
	
	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}

	public Date getDataconversa() {
		return dataconversa;
	}
	
	public void setDataconversa(Date dataconversa) {
		this.dataconversa = dataconversa;
	}


	@Override
	public String toString() {
		return "ID da conversa: "  + id_conversa + "   CNPJ da ONG: " + fk_ong_cnpj + "   CPF do usuário " + fk_usuario_cpf + "   Conteudo: "
		+ conteudo + "  Data da conversa:" + dataconversa ;
	}
	
	@Override
	public boolean equals(Object obj) {
		return (this.getID() == ((conversa) obj).getID());
	}	
}