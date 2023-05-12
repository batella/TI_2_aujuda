package service;

import java.util.Scanner;
import java.time.LocalDate;
import java.io.File;
import java.time.LocalDateTime;
import java.util.List;
import dao.ConversaDAO;
import model.Conversa;
import spark.Request;
import spark.Response;


public class ConversaService {

	private ConversaDAO conversaDAO = new ConversaDAO();
	private String form;
	private final int FORM_INSERT = 1;
	private final int FORM_DETAIL = 2;
	private final int FORM_UPDATE = 3;
	private final int FORM_ORDERBY_ID_CONVERSA = 1;
	private final int FORM_ORDERBY_FK_USUARIO_CPF = 2;
	private final int FORM_ORDERBY_CONTEUDO = 3;
    private final int FORM_ORDERBY_FK_ONG_CNPJ = 4;
    private final int FORM_ORDERBY_DATACONVERSA = 5;
	
	
	public ConversaService() {
		makeForm();
	}

	
	public void makeForm() {
		makeForm(FORM_INSERT, new Conversa(), FORM_ORDERBY_FK_USUARIO_CPF);
	}

	
	public void makeForm(int orderBy) {
		makeForm(FORM_INSERT, new Conversa(), orderBy);
	}

	
	public void makeForm(int tipo, Conversa conversa, int orderBy) {
		String fk_usuario_cpfArquivo = "form.html";
		form = "";
		try{
			Scanner entrada = new Scanner(new File(fk_usuario_cpfArquivo));
		    while(entrada.hasNext()){
		    	form += (entrada.nextLine() + "\n");
		    }
		    entrada.close();
		}  catch (Exception e) { System.out.println(e.getMessage()); }
		
		String umConversa = "";
		if(tipo != FORM_INSERT) {
			umConversa += "\t<table width=\"80%\" bgcolor=\"#f3f3f3\" align=\"center\">";
			umConversa += "\t\t<tr>";
			umConversa += "\t\t\t<td align=\"left\"><font size=\"+2\"><b>&nbsp;&nbsp;&nbsp;<a href=\"/conversa/list/1\">Novo Conversa</a></b></font></td>";
			umConversa += "\t\t</tr>";
			umConversa += "\t</table>";
			umConversa += "\t<br>";			
		}
		
		if(tipo == FORM_INSERT || tipo == FORM_UPDATE) {
			String action = "/conversa/";
			String name, fk_usuario_cpf, buttonLabel;
			if (tipo == FORM_INSERT){
				action += "insert";
				name = "Inserir Conversa";
				fk_usuario_cpf = "joao, maria";
				buttonLabel = "Inserir";
			} else {
				action += "update/" + conversa.getId_conversa();
				name = "Atualizar Conversa (ID_CONVERSA " + conversa.getId_conversa() + ")";
				fk_usuario_cpf = conversa.getFk_usuario_cpf(); //nao deveria poder atualizar todos os dados?
				buttonLabel = "Atualizar";
			}
			umConversa += "\t<form class=\"form--register\" action=\"" + action + "\" method=\"post\" id_conversa=\"form-add\">";
			umConversa += "\t<table width=\"80%\" bgcolor=\"#f3f3f3\" align=\"center\">";
			umConversa += "\t\t<tr>";
			umConversa += "\t\t\t<td colspan=\"3\" align=\"left\"><font size=\"+2\"><b>&nbsp;&nbsp;&nbsp;" + name + "</b></font></td>";
			umConversa += "\t\t</tr>";
			umConversa += "\t\t<tr>";
			umConversa += "\t\t\t<td colspan=\"3\" align=\"left\">&nbsp;</td>";
			umConversa += "\t\t</tr>";
			umConversa += "\t\t<tr>";
			umConversa += "\t\t\t<td>&nbsp;Fk_usuario_cpf: <input class=\"input--register\" type=\"text\" name=\"fk_usuario_cpf\" value=\""+ conversa.getFk_usuario_cpf() +"\"></td>";
			umConversa += "\t\t\t<td>Conteudo: <input class=\"input--register\" type=\"text\" name=\"conteudo\" value=\""+ conversa.getConteudo() +"\"></td>";
			umConversa += "\t\t</tr>";
			umConversa += "\t\t<tr>";
            umConversa += "\t\t\t<td>&nbsp;ID_CONVERSA: <input class=\"input--register\" type=\"text\" name=\"id_conversa\" value=\""+ conversa.getId_conversa().toString() + "\"></td>";
			umConversa += "\t\t\t<td>Fk_ong_cnpj: <input class=\"input--register\" type=\"text\" name=\"fk_ong_cnpj\" value=\""+ conversa.getFk_ong_cnpj() + "\"></td>";
			umConversa += "\t\t\t<td>Dataconversa: <input class=\"input--register\" type=\"text\" name=\"dataconversa\" value=\""+ conversa.getDataconversa().toString() + "\"></td>";
			umConversa += "\t\t\t<td align=\"center\"><input type=\"submit\" value=\""+ buttonLabel +"\" class=\"input--main__style input--button\"></td>";
			umConversa += "\t\t</tr>";
			umConversa += "\t</table>";
			umConversa += "\t</form>";		
		} else if (tipo == FORM_DETAIL){
			umConversa += "\t<table width=\"80%\" bgcolor=\"#f3f3f3\" align=\"center\">";
			umConversa += "\t\t<tr>";
			umConversa += "\t\t\t<td colspan=\"3\" align=\"left\"><font size=\"+2\"><b>&nbsp;&nbsp;&nbsp;Detalhar Conversa (ID_CONVERSA " + conversa.getID_CONVERSA() + ")</b></font></td>";
			umConversa += "\t\t</tr>";
			umConversa += "\t\t<tr>";
			umConversa += "\t\t\t<td colspan=\"3\" align=\"left\">&nbsp;</td>";
			umConversa += "\t\t</tr>";
			umConversa += "\t\t<tr>";
			umConversa += "\t\t\t<td>&nbsp;Fk_usuario_cpf: "+ conversa.getFk_usuario_cpf() +"</td>";
			umConversa += "\t\t\t<td>Conteudo: "+ conversa.getConteudo() +"</td>";
			
			umConversa += "\t\t</tr>";
			umConversa += "\t\t<tr>";
            umConversa += "\t\t\t<td>&nbsp;ID_CONVERSA: "+ conversa.getId_conversa().toString() + "</td>";
			
			umConversa += "\t\t\t<td>Fk_ong_cnpj: "+ conversa.getFk_ong_cnpj() + "</td>";
			
			umConversa += "\t\t\t<td>&nbsp;DATACONVERSA: "+ conversa.getDataconversa().toString() + "</td>";
			umConversa += "\t\t\t<td>&nbsp;</td>";
			umConversa += "\t\t</tr>";
			umConversa += "\t</table>";		
		} else {
			System.out.println("ERRO! Tipo não identificado " + tipo);
		}
		form = form.replaceFirst("<UM-CONVERSA>", umConversa);
		
		String list = new String("<table width=\"80%\" align=\"center\" bgcolor=\"#f3f3f3\">");
		list += "\n<tr><td colspan=\"6\" align=\"left\"><font size=\"+2\"><b>&nbsp;&nbsp;&nbsp;Relação de Conversas</b></font></td></tr>\n" +
				"\n<tr><td colspan=\"6\">&nbsp;</td></tr>\n" +
    			"\n<tr>\n" + 
        		"\t<td><a href=\"/conversa/list/" + FORM_ORDERBY_ID_CONVERSA + "\"><b>ID_CONVERSA<ID_CONVERSA/a></td>\n" +
        		"\t<td><a href=\"/conversa/list/" + FORM_ORDERBY_FK_USUARIO_CPF + "\"><b>Fk_usuario_cpf</b></a></td>\n" +
        		"\t<td><a href=\"/conversa/list/" + FORM_ORDERBY_CONTEUDO + "\"><b>Conteudo</b></a></td>\n" +
                
                
                "\t<td><a href=\"/conversa/list/" + FORM_ORDERBY_FK_ONG_CNPJ + "\"><b>Fk_ong_cnpj</b></a></td>\n" +
                
                "\t<td><a href=\"/conversa/list/" + FORM_ORDERBY_DATACONVERSA + "\"><b>Fk_ong_cnpj</b></a></td>\n" +
        		"\t<td width=\"100\" align=\"center\"><b>Detalhar</b></td>\n" +
        		"\t<td width=\"100\" align=\"center\"><b>Atualizar</b></td>\n" +
        		"\t<td width=\"100\" align=\"center\"><b>Excluir</b></td>\n" +
        		"</tr>\n";
		
		List<Conversa> conversas;
		if (orderBy == FORM_ORDERBY_ID_CONVERSA) {                 	conversas = conversaDAO.getOrderById_conversa();
		} else if (orderBy == FORM_ORDERBY_FK_USUARIO_CPF) {		conversas = conversaDAO.getOrderByFk_usuario_cpf();
		} else if (orderBy == FORM_ORDERBY_CONTEUDO) {			conversas = conversaDAO.getOrderByConteudo();
        } else if (orderBy == FORM_ORDERBY_FK_ONG_CNPJ) {			conversas = conversaDAO.getOrderByFk_ong_cnpj();
        } else if (orderBy == FORM_ORDERBY_DATACONVERSA) {			conversas = conversaDAO.getOrderByDataconversa();
		} else {											conversas = conversaDAO.get();
		}

		int i = 0;
		String bgcolor = "";
		for (Conversa p : conversas) {
			bgcolor = (i++ % 2 == 0) ? "#fff5dd" : "#dddddd";
			list += "\n<tr bgcolor=\""+ bgcolor +"\">\n" + 
            		  "\t<td>" + p.getId_conversa() + "</td>\n" +
            		  "\t<td>" + p.getFk_usuario_cpf() + "</td>\n" +
            		  "\t<td>" + p.getConteudo() + "</td>\n" +
                      "\t<td>" + p.getFk_ong_cnpj() + "</td>\n" +
                      "\t<td>" + p.getDataconversa() + "</td>\n" +
            		  "\t<td align=\"center\" valign=\"middle\"><a href=\"/conversa/" + p.getId_conversa() + "\"><img src=\"/image/detail.png\" width=\"20\" height=\"20\"/></a></td>\n" +
            		  "\t<td align=\"center\" valign=\"middle\"><a href=\"/conversa/update/" + p.getId_conversa() + "\"><img src=\"/image/update.png\" width=\"20\" height=\"20\"/></a></td>\n" +
            		  "\t<td align=\"center\" valign=\"middle\"><a href=\"javascript:confirmarDeleteConversa('" + p.getId_conversa() + "', '" + p.getFk_usuario_cpf() + "', '" + p.getConteudo() + "', '" + p.getFk_ong_cnpj + "', '"  + p.getDataconversa "');\"><img src=\"/image/delete.png\" width=\"20\" height=\"20\"/></a></td>\n" +
            		  "</tr>\n";
		}
		list += "</table>";		
		form = form.replaceFirst("<LISTAR-CONVERSA>", list);				
	}
	
	
	public Object insert(Request request, Response response) {
        int id_conversa = Integer.parseInt(request.queryParams("id_conversa"));
		String fk_usuario_cpf = request.queryParams("fk_usuario_cpf");
		String conteudo = request.queryParams("conteudo");
		String fk_ong_cnpj = request.queryParams("fk_ong_cnpj");
		Date dataconversa = Date.parseDate(request.queryParams("dataconversa"));
		
		
		String resp = "";
		
		Conversa conversa = new Conversa(id_conversa, fk_usuario_cpf, conteudo, fk_ong_cnpj, dataconversa);
		
		if(conversaDAO.insert(conversa) == true) {
            resp = "Conversa (" + fk_usuario_cpf + ") inserido!";
            response.status(201); // 201 Created
		} else {
			resp = "Conversa (" + fk_usuario_cpf + ") não inserido!";
			response.status(404); // 404 Not found
		}
			
		makeForm();
		return form.replaceFirst("<input type=\"hidden\" id_conversa=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id_conversa=\"msg\" name=\"msg\" value=\""+ resp +"\">");
	}

	
	public Object get(Request request, Response response) {
		int id_conversa = Integer.parseInt(request.queryParams("id_conversa"));		
		Conversa conversa = (Conversa) conversaDAO.get(id_conversa);
		
		if (conversa != null) {
			response.status(200); // success
			makeForm(FORM_DETAIL, conversa, FORM_ORDERBY_FK_USUARIO_CPF);
        } else {
            response.status(404); // 404 Not found
            String resp = "Conversa " + id_conversa + " não encontrado.";
    		makeForm();
    		form.replaceFirst("<input type=\"hidden\" id_conversa=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id_conversa=\"msg\" name=\"msg\" value=\""+ resp +"\">");     
        }

		return form;
	}

	
	public Object getToUpdate(Request request, Response response) {
		int id_conversa = Integer.parseInt(request.queryParams("id_conversa"));		
		Conversa conversa = (Conversa) conversaDAO.get(id_conversa);
		
		if (conversa != null) {
			response.status(200); // success
			makeForm(FORM_UPDATE, conversa, FORM_ORDERBY_FK_USUARIO_CPF);
        } else {
            response.status(404); // 404 Not found
            String resp = "Conversa " + id_conversa + " não encontrado.";
    		makeForm();
    		form.replaceFirst("<input type=\"hidden\" id_conversa=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id_conversa=\"msg\" name=\"msg\" value=\""+ resp +"\">");     
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
		int id_conversa = Integer.parseInt(request.queryParams("id_conversa"));
		Conversa conversa = conversaDAO.get(id_conversa);
        String resp = "";       

        if (conversa != null) {
        	int id_conversa = Integer.parseInt(request.queryParams("id_conversa"));
    		String fk_usuario_cpf = request.queryParams("fk_usuario_cpf");
    		String conteudo = request.queryParams("conteudo");
    		String fk_ong_cnpj = request.queryParams("fk_ong_cnpj");
    		Date dataconversa = Date.parseDate(request.queryParams("dataconversa"));
        	conversaDAO.update(conversa);
        	response.status(200); // success
            resp = "Conversa (ID_CONVERSA " + conversa.getId_conversa() + ") atualizado!";
        } else {
            response.status(404); // 404 Not found
            resp = "Conversa (ID_CONVERSA \" + conversa.getId_conversa() + \") não encontrado!";
        }
		makeForm();
		return form.replaceFirst("<input type=\"hidden\" id_conversa=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id_conversa=\"msg\" name=\"msg\" value=\""+ resp +"\">");
	}

	
	public Object delete(Request request, Response response) {
		int id_conversa = Integer.parseInt(request.queryParams("id_conversa"));
        Conversa conversa = conversaDAO.get(id_conversa);
        String resp = "";       

        if (conversa != null) {
            conversaDAO.delete(id_conversa);
            response.status(200); // success
            resp = "Conversa (" + id_conversa + ") excluído!";
        } else {
            response.status(404); // 404 Not found
            resp = "Conversa (" + id_conversa + ") não encontrado!";
        }
		makeForm();
		return form.replaceFirst("<input type=\"hidden\" id_conversa=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id_conversa=\"msg\" name=\"msg\" value=\""+ resp +"\">");
	}
}