package service;

import java.util.Scanner;
import java.time.LocalDate;
import java.io.File;
import java.time.LocalDateTime;
import java.util.List;
import dao.OngDAO;
import model.Ong;
import spark.Request;
import spark.Response;


public class OngService {

	private OngDAO ongDAO = new OngDAO();
	private String form;
	private final int FORM_INSERT = 1;
	private final int FORM_DETAIL = 2;
	private final int FORM_UPDATE = 3;
	private final int FORM_ORDERBY_CNPJ = 1;
	private final int FORM_ORDERBY_NOME = 2;
	private final int FORM_ORDERBY_ENDERECO = 3;
    private final int FORM_ORDERBY_DESCRICAO = 4;
    private final int FORM_ORDERBY_TELEFONE = 5;
    private final int FORM_ORDERBY_FK_ANIMAL_ID = 6;
	
	
	public OngService() {
		makeForm();
	}

	
	public void makeForm() {
		makeForm(FORM_INSERT, new Ong(), FORM_ORDERBY_NOME);
	}

	
	public void makeForm(int orderBy) {
		makeForm(FORM_INSERT, new Ong(), orderBy);
	}

	
	public void makeForm(int tipo, Ong ong, int orderBy) {
		String nomeArquivo = "form.html";
		form = "";
		try{
			Scanner entrada = new Scanner(new File(nomeArquivo));
		    while(entrada.hasNext()){
		    	form += (entrada.nextLine() + "\n");
		    }
		    entrada.close();
		}  catch (Exception e) { System.out.println(e.getMessage()); }
		
		String umOng = "";
		if(tipo != FORM_INSERT) {
			umOng += "\t<table width=\"80%\" bgcolor=\"#f3f3f3\" align=\"center\">";
			umOng += "\t\t<tr>";
			umOng += "\t\t\t<td align=\"left\"><font size=\"+2\"><b>&nbsp;&nbsp;&nbsp;<a href=\"/ong/list/1\">Novo Ong</a></b></font></td>";
			umOng += "\t\t</tr>";
			umOng += "\t</table>";
			umOng += "\t<br>";			
		}
		
		if(tipo == FORM_INSERT || tipo == FORM_UPDATE) {
			String action = "/ong/";
			String name, nome, buttonLabel;
			if (tipo == FORM_INSERT){
				action += "insert";
				name = "Inserir Ong";
				nome = "joao, maria";
				buttonLabel = "Inserir";
			} else {
				action += "update/" + ong.getCnpj();
				name = "Atualizar Ong (CNPJ " + ong.getCnpj() + ")";
				nome = ong.getNome(); //nao deveria poder atualizar todos os dados?
				buttonLabel = "Atualizar";
			}
			umOng += "\t<form class=\"form--register\" action=\"" + action + "\" method=\"post\" cnpj=\"form-add\">";
			umOng += "\t<table width=\"80%\" bgcolor=\"#f3f3f3\" align=\"center\">";
			umOng += "\t\t<tr>";
			umOng += "\t\t\t<td colspan=\"3\" align=\"left\"><font size=\"+2\"><b>&nbsp;&nbsp;&nbsp;" + name + "</b></font></td>";
			umOng += "\t\t</tr>";
			umOng += "\t\t<tr>";
			umOng += "\t\t\t<td colspan=\"3\" align=\"left\">&nbsp;</td>";
			umOng += "\t\t</tr>";
			umOng += "\t\t<tr>";
			umOng += "\t\t\t<td>&nbsp;Nome: <input class=\"input--register\" type=\"text\" name=\"nome\" value=\""+ ong.getNome() +"\"></td>";
			umOng += "\t\t\t<td>Endereco: <input class=\"input--register\" type=\"text\" name=\"endereco\" value=\""+ ong.getEndereco() +"\"></td>";
			umOng += "\t\t\t<td>Descricao: <input class=\"input--register\" type=\"text\" name=\"descricao\" value=\""+ ong.getDescricao() +"\"></td>";
			umOng += "\t\t</tr>";
			umOng += "\t\t<tr>";
            umOng += "\t\t\t<td>&nbsp;CNPJ: <input class=\"input--register\" type=\"text\" name=\"cnpj\" value=\""+ ong.getCnpj() + "\"></td>";
			umOng += "\t\t\t<td>&nbsp;Telefone: <input class=\"input--register\" type=\"text\" name=\"telefone\" value=\""+ ong.getTelefone() + "\"></td>";
			umOng += "\t\t\t<td>Fk_animal_id: <input class=\"input--register\" type=\"text\" name=\"fk_animal_id\" value=\""+ ong.getFk_animal_id() + "\"></td>";
			umOng += "\t\t\t<td align=\"center\"><input type=\"submit\" value=\""+ buttonLabel +"\" class=\"input--main__style input--button\"></td>";
			umOng += "\t\t</tr>";
			umOng += "\t</table>";
			umOng += "\t</form>";		
		} else if (tipo == FORM_DETAIL){
			umOng += "\t<table width=\"80%\" bgcolor=\"#f3f3f3\" align=\"center\">";
			umOng += "\t\t<tr>";
			umOng += "\t\t\t<td colspan=\"3\" align=\"left\"><font size=\"+2\"><b>&nbsp;&nbsp;&nbsp;Detalhar Ong (CNPJ " + ong.getCNPJ() + ")</b></font></td>";
			umOng += "\t\t</tr>";
			umOng += "\t\t<tr>";
			umOng += "\t\t\t<td colspan=\"3\" align=\"left\">&nbsp;</td>";
			umOng += "\t\t</tr>";
			umOng += "\t\t<tr>";
			umOng += "\t\t\t<td>&nbsp;Nome: "+ ong.getNome() +"</td>";
			umOng += "\t\t\t<td>Endereco: "+ ong.getEndereco() +"</td>";
			umOng += "\t\t\t<td>Descricao: "+ ong.getDescricao() +"</td>";
			umOng += "\t\t</tr>";
			umOng += "\t\t<tr>";
            umOng += "\t\t\t<td>&nbsp;CNPJ: "+ ong.getCnpj() + "</td>";
			umOng += "\t\t\t<td>&nbsp;Telefone: "+ ong.getTelefone() + "</td>";
			umOng += "\t\t\t<td>Fk_animal_id: "+ ong.getFk_animal_id() + "</td>";
			umOng += "\t\t\t<td>&nbsp;</td>";
			umOng += "\t\t</tr>";
			umOng += "\t</table>";		
		} else {
			System.out.println("ERRO! Tipo não identificado " + tipo);
		}
		form = form.replaceFirst("<UM-ONG>", umOng);
		
		String list = new String("<table width=\"80%\" align=\"center\" bgcolor=\"#f3f3f3\">");
		list += "\n<tr><td colspan=\"6\" align=\"left\"><font size=\"+2\"><b>&nbsp;&nbsp;&nbsp;Relação de Ongs</b></font></td></tr>\n" +
				"\n<tr><td colspan=\"6\">&nbsp;</td></tr>\n" +
    			"\n<tr>\n" + 
        		"\t<td><a href=\"/ong/list/" + FORM_ORDERBY_CNPJ + "\"><b>CNPJ</b></a></td>\n" +
        		"\t<td><a href=\"/ong/list/" + FORM_ORDERBY_NOME + "\"><b>Nome</b></a></td>\n" +
        		"\t<td><a href=\"/ong/list/" + FORM_ORDERBY_ENDERECO + "\"><b>Endereco</b></a></td>\n" +
                "\t<td><a href=\"/ong/list/" + FORM_ORDERBY_DESCRICAO + "\"><b>Descricao</b></a></td>\n" +
                "\t<td><a href=\"/ong/list/" + FORM_ORDERBY_TELEFONE + "\"><b>Telefone</b></a></td>\n" +
                "\t<td><a href=\"/ong/list/" + FORM_ORDERBY_FK_ANIMAL_ID + "\"><b>Fk_animal_id</b></a></td>\n" +
        		"\t<td width=\"100\" align=\"center\"><b>Detalhar</b></td>\n" +
        		"\t<td width=\"100\" align=\"center\"><b>Atualizar</b></td>\n" +
        		"\t<td width=\"100\" align=\"center\"><b>Excluir</b></td>\n" +
        		"</tr>\n";
		
		List<Ong> ongs;
		if (orderBy == FORM_ORDERBY_CNPJ) {                 	ongs = ongDAO.getOrderByCnpj();
		} else if (orderBy == FORM_ORDERBY_NOME) {		ongs = ongDAO.getOrderByNome();
		} else if (orderBy == FORM_ORDERBY_ENDERECO) {			ongs = ongDAO.getOrderByEndereco();
        } else if (orderBy == FORM_ORDERBY_DESCRICAO) {			ongs = ongDAO.getOrderByDescricao();
        } else if (orderBy == FORM_ORDERBY_TELEFONE) {			ongs = ongDAO.getOrderByTelefone();
        } else if (orderBy == FORM_ORDERBY_FK_ANIMAL_ID) {			ongs = ongDAO.getOrderByFk_animal_id();
		} else {											ongs = ongDAO.get();
		}

		int i = 0;
		String bgcolor = "";
		for (Ong p : ongs) {
			bgcolor = (i++ % 2 == 0) ? "#fff5dd" : "#dddddd";
			list += "\n<tr bgcolor=\""+ bgcolor +"\">\n" + 
            		  "\t<td>" + p.getCnpj() + "</td>\n" +
            		  "\t<td>" + p.getNome() + "</td>\n" +
            		  "\t<td>" + p.getEndereco() + "</td>\n" +
                      "\t<td>" + p.getDescricao() + "</td>\n" +
                      "\t<td>" + p.getTelefone() + "</td>\n" +
                      "\t<td>" + p.getFk_animal_id() + "</td>\n" +
            		  "\t<td align=\"center\" valign=\"middle\"><a href=\"/ong/" + p.getCnpj() + "\"><img src=\"/image/detail.png\" width=\"20\" height=\"20\"/></a></td>\n" +
            		  "\t<td align=\"center\" valign=\"middle\"><a href=\"/ong/update/" + p.getCnpj() + "\"><img src=\"/image/update.png\" width=\"20\" height=\"20\"/></a></td>\n" +
            		  "\t<td align=\"center\" valign=\"middle\"><a href=\"javascript:confirmarDeleteOng('" + p.getCnpj() + "', '" + p.getNome() + "', '" + p.getEndereco() + "', '" + p.getDescricao + "', '" + p.getTelefone + "', '" + p.getFk_animal_id "');\"><img src=\"/image/delete.png\" width=\"20\" height=\"20\"/></a></td>\n" +
            		  "</tr>\n";
		}
		list += "</table>";		
		form = form.replaceFirst("<LISTAR-ONG>", list);				
	}
	
	
	public Object insert(Request request, Response response) {
        String cnpj = request.queryParams("cnpj");
		String nome = request.queryParams("nome");
		String endereco = request.queryParams("endereco");
		String descricao = request.queryParams("descricao");
		String telefone = request.queryParams("telefone");
		String fk_animal_id = request.queryParams("fk_animal_id");
		
		String resp = "";
		
		Ong ong = new Ong(cnpj, nome, endereco, descricao, telefone, fk_animal_id);
		
		if(ongDAO.insert(ong) == true) {
            resp = "Ong (" + nome + ") inserido!";
            response.status(201); // 201 Created
		} else {
			resp = "Ong (" + nome + ") não inserido!";
			response.status(404); // 404 Not found
		}
			
		makeForm();
		return form.replaceFirst("<input type=\"hidden\" cnpj=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" cnpj=\"msg\" name=\"msg\" value=\""+ resp +"\">");
	}

	
	public Object get(Request request, Response response) {
		String cnpj = request.params(":cnpj");		
		Ong ong = (Ong) ongDAO.get(cnpj);
		
		if (ong != null) {
			response.status(200); // success
			makeForm(FORM_DETAIL, ong, FORM_ORDERBY_NOME);
        } else {
            response.status(404); // 404 Not found
            String resp = "Ong " + cnpj + " não encontrado.";
    		makeForm();
    		form.replaceFirst("<input type=\"hidden\" cnpj=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" cnpj=\"msg\" name=\"msg\" value=\""+ resp +"\">");     
        }

		return form;
	}

	
	public Object getToUpdate(Request request, Response response) {
		String cnpj = request.params(":cnpj");		
		Ong ong = (Ong) ongDAO.get(cnpj);
		
		if (ong != null) {
			response.status(200); // success
			makeForm(FORM_UPDATE, ong, FORM_ORDERBY_NOME);
        } else {
            response.status(404); // 404 Not found
            String resp = "Ong " + cnpj + " não encontrado.";
    		makeForm();
    		form.replaceFirst("<input type=\"hidden\" cnpj=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" cnpj=\"msg\" name=\"msg\" value=\""+ resp +"\">");     
        }

		return form;
	}
	
	
	public Object getAll(Request request, Response response) {
		int orderBy = Integer.parseInt(request.params(":orderby"));
		makeForm(orderBy);
	    response.header("Content-Type", "text/html");
	    response.header("Content-Encoding", "UTF-8");
		return form;
	}			
	
	public Object update(Request request, Response response) {
        String cnpj = request.params(":cnpj");
		Ong ong = ongDAO.get(cnpj);
        String resp = "";       

        if (ong != null) {
        	ong.setNome(request.queryParams("nome"));
        	ong.setEndereco(request.queryParams("endereco"));
        	ong.setDescricao(request.queryParams("descricao"));
        	ong.setTelefone(request.queryParams("telefone"));
        	ong.setFk_animal_id(request.queryParams("fk_animal_id"));
        	ongDAO.update(ong);
        	response.status(200); // success
            resp = "Ong (CNPJ " + ong.getCnpj() + ") atualizado!";
        } else {
            response.status(404); // 404 Not found
            resp = "Ong (CNPJ \" + ong.getCnpj() + \") não encontrado!";
        }
		makeForm();
		return form.replaceFirst("<input type=\"hidden\" cnpj=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" cnpj=\"msg\" name=\"msg\" value=\""+ resp +"\">");
	}

	
	public Object delete(Request request, Response response) {
        String cnpj = request.params(":cnpj");
        Ong ong = ongDAO.get(cnpj);
        String resp = "";       

        if (ong != null) {
            ongDAO.delete(cnpj);
            response.status(200); // success
            resp = "Ong (" + cnpj + ") excluído!";
        } else {
            response.status(404); // 404 Not found
            resp = "Ong (" + cnpj + ") não encontrado!";
        }
		makeForm();
		return form.replaceFirst("<input type=\"hidden\" cnpj=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" cnpj=\"msg\" name=\"msg\" value=\""+ resp +"\">");
	}
}