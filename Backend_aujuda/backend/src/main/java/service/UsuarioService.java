package service;

import java.util.Scanner;
import java.time.LocalDate;
import java.io.File;
import java.time.LocalDateTime;
import java.util.List;
import dao.UsuarioDAO;
import model.Usuario;
import spark.Request;
import spark.Response;


public class UsuarioService {

	private UsuarioDAO usuarioDAO = new UsuarioDAO();
	private String form;
	private final int FORM_INSERT = 1;
	private final int FORM_DETAIL = 2;
	private final int FORM_UPDATE = 3;
	private final int FORM_ORDERBY_CPF = 1;
	private final int FORM_ORDERBY_NOME = 2;
	private final int FORM_ORDERBY_SOBRENOME = 3;
    private final int FORM_ORDERBY_USERNAME = 4;
    private final int FORM_ORDERBY_TELEFONE = 5;
    private final int FORM_ORDERBY_CIDADE = 6;
	
	
	public UsuarioService() {
		makeForm();
	}

	
	public void makeForm() {
		makeForm(FORM_INSERT, new Usuario(), FORM_ORDERBY_NOME);
	}

	
	public void makeForm(int orderBy) {
		makeForm(FORM_INSERT, new Usuario(), orderBy);
	}

	
	public void makeForm(int tipo, Usuario usuario, int orderBy) {
		String nomeArquivo = "form.html";
		form = "";
		try{
			Scanner entrada = new Scanner(new File(nomeArquivo));
		    while(entrada.hasNext()){
		    	form += (entrada.nextLine() + "\n");
		    }
		    entrada.close();
		}  catch (Exception e) { System.out.println(e.getMessage()); }
		
		String umUsuario = "";
		if(tipo != FORM_INSERT) {
			umUsuario += "\t<table width=\"80%\" bgcolor=\"#f3f3f3\" align=\"center\">";
			umUsuario += "\t\t<tr>";
			umUsuario += "\t\t\t<td align=\"left\"><font size=\"+2\"><b>&nbsp;&nbsp;&nbsp;<a href=\"/usuario/list/1\">Novo Usuario</a></b></font></td>";
			umUsuario += "\t\t</tr>";
			umUsuario += "\t</table>";
			umUsuario += "\t<br>";			
		}
		
		if(tipo == FORM_INSERT || tipo == FORM_UPDATE) {
			String action = "/usuario/";
			String name, nome, buttonLabel;
			if (tipo == FORM_INSERT){
				action += "insert";
				name = "Inserir Usuario";
				nome = "joao, maria";
				buttonLabel = "Inserir";
			} else {
				action += "update/" + usuario.getCpf();
				name = "Atualizar Usuario (CPF " + usuario.getCpf() + ")";
				nome = usuario.getNome(); //nao deveria poder atualizar todos os dados?
				buttonLabel = "Atualizar";
			}
			umUsuario += "\t<form class=\"form--register\" action=\"" + action + "\" method=\"post\" cpf=\"form-add\">";
			umUsuario += "\t<table width=\"80%\" bgcolor=\"#f3f3f3\" align=\"center\">";
			umUsuario += "\t\t<tr>";
			umUsuario += "\t\t\t<td colspan=\"3\" align=\"left\"><font size=\"+2\"><b>&nbsp;&nbsp;&nbsp;" + name + "</b></font></td>";
			umUsuario += "\t\t</tr>";
			umUsuario += "\t\t<tr>";
			umUsuario += "\t\t\t<td colspan=\"3\" align=\"left\">&nbsp;</td>";
			umUsuario += "\t\t</tr>";
			umUsuario += "\t\t<tr>";
			umUsuario += "\t\t\t<td>&nbsp;Nome: <input class=\"input--register\" type=\"text\" name=\"nome\" value=\""+ usuario.getNome() +"\"></td>";
			umUsuario += "\t\t\t<td>Sobrenome: <input class=\"input--register\" type=\"text\" name=\"sobrenome\" value=\""+ usuario.getSobrenome() +"\"></td>";
			umUsuario += "\t\t\t<td>Username: <input class=\"input--register\" type=\"text\" name=\"username\" value=\""+ usuario.getUsername() +"\"></td>";
			umUsuario += "\t\t</tr>";
			umUsuario += "\t\t<tr>";
            umUsuario += "\t\t\t<td>&nbsp;CPF: <input class=\"input--register\" type=\"text\" name=\"cpf\" value=\""+ usuario.getCpf() + "\"></td>";
			umUsuario += "\t\t\t<td>&nbsp;Telefone: <input class=\"input--register\" type=\"text\" name=\"telefone\" value=\""+ usuario.getTelefone() + "\"></td>";
			umUsuario += "\t\t\t<td>Cidade: <input class=\"input--register\" type=\"text\" name=\"cidade\" value=\""+ usuario.getCidade() + "\"></td>";
			umUsuario += "\t\t\t<td align=\"center\"><input type=\"submit\" value=\""+ buttonLabel +"\" class=\"input--main__style input--button\"></td>";
			umUsuario += "\t\t</tr>";
			umUsuario += "\t</table>";
			umUsuario += "\t</form>";		
		} else if (tipo == FORM_DETAIL){
			umUsuario += "\t<table width=\"80%\" bgcolor=\"#f3f3f3\" align=\"center\">";
			umUsuario += "\t\t<tr>";
			umUsuario += "\t\t\t<td colspan=\"3\" align=\"left\"><font size=\"+2\"><b>&nbsp;&nbsp;&nbsp;Detalhar Usuario (CPF " + usuario.getCPF() + ")</b></font></td>";
			umUsuario += "\t\t</tr>";
			umUsuario += "\t\t<tr>";
			umUsuario += "\t\t\t<td colspan=\"3\" align=\"left\">&nbsp;</td>";
			umUsuario += "\t\t</tr>";
			umUsuario += "\t\t<tr>";
			umUsuario += "\t\t\t<td>&nbsp;Nome: "+ usuario.getNome() +"</td>";
			umUsuario += "\t\t\t<td>Sobrenome: "+ usuario.getSobrenome() +"</td>";
			umUsuario += "\t\t\t<td>Username: "+ usuario.getUsername() +"</td>";
			umUsuario += "\t\t</tr>";
			umUsuario += "\t\t<tr>";
            umUsuario += "\t\t\t<td>&nbsp;CPF: "+ usuario.getCpf() + "</td>";
			umUsuario += "\t\t\t<td>&nbsp;Telefone: "+ usuario.getTelefone() + "</td>";
			umUsuario += "\t\t\t<td>Cidade: "+ usuario.getCidade() + "</td>";
			umUsuario += "\t\t\t<td>&nbsp;</td>";
			umUsuario += "\t\t</tr>";
			umUsuario += "\t</table>";		
		} else {
			System.out.println("ERRO! Tipo não identificado " + tipo);
		}
		form = form.replaceFirst("<UM-USUARIO>", umUsuario);
		
		String list = new String("<table width=\"80%\" align=\"center\" bgcolor=\"#f3f3f3\">");
		list += "\n<tr><td colspan=\"6\" align=\"left\"><font size=\"+2\"><b>&nbsp;&nbsp;&nbsp;Relação de Usuarios</b></font></td></tr>\n" +
				"\n<tr><td colspan=\"6\">&nbsp;</td></tr>\n" +
    			"\n<tr>\n" + 
        		"\t<td><a href=\"/usuario/list/" + FORM_ORDERBY_CPF + "\"><b>CPF</b></a></td>\n" +
        		"\t<td><a href=\"/usuario/list/" + FORM_ORDERBY_NOME + "\"><b>Nome</b></a></td>\n" +
        		"\t<td><a href=\"/usuario/list/" + FORM_ORDERBY_SOBRENOME + "\"><b>Sobrenome</b></a></td>\n" +
                "\t<td><a href=\"/usuario/list/" + FORM_ORDERBY_USERNAME + "\"><b>Username</b></a></td>\n" +
                "\t<td><a href=\"/usuario/list/" + FORM_ORDERBY_TELEFONE + "\"><b>Telefone</b></a></td>\n" +
                "\t<td><a href=\"/usuario/list/" + FORM_ORDERBY_CIDADE + "\"><b>Cidade</b></a></td>\n" +
        		"\t<td width=\"100\" align=\"center\"><b>Detalhar</b></td>\n" +
        		"\t<td width=\"100\" align=\"center\"><b>Atualizar</b></td>\n" +
        		"\t<td width=\"100\" align=\"center\"><b>Excluir</b></td>\n" +
        		"</tr>\n";
		
		List<Usuario> usuarios;
		if (orderBy == FORM_ORDERBY_CPF) {                 	usuarios = usuarioDAO.getOrderByCpf();
		} else if (orderBy == FORM_ORDERBY_NOME) {		usuarios = usuarioDAO.getOrderByNome();
		} else if (orderBy == FORM_ORDERBY_SOBRENOME) {			usuarios = usuarioDAO.getOrderBySobrenome();
        } else if (orderBy == FORM_ORDERBY_USERNAME) {			usuarios = usuarioDAO.getOrderByUsername();
        } else if (orderBy == FORM_ORDERBY_TELEFONE) {			usuarios = usuarioDAO.getOrderByTelefone();
        } else if (orderBy == FORM_ORDERBY_CIDADE) {			usuarios = usuarioDAO.getOrderByCidade();
		} else {											usuarios = usuarioDAO.get();
		}

		int i = 0;
		String bgcolor = "";
		for (Usuario p : usuarios) {
			bgcolor = (i++ % 2 == 0) ? "#fff5dd" : "#dddddd";
			list += "\n<tr bgcolor=\""+ bgcolor +"\">\n" + 
            		  "\t<td>" + p.getCpf() + "</td>\n" +
            		  "\t<td>" + p.getNome() + "</td>\n" +
            		  "\t<td>" + p.getSobrenome() + "</td>\n" +
                      "\t<td>" + p.getUsername() + "</td>\n" +
                      "\t<td>" + p.getTelefone() + "</td>\n" +
                      "\t<td>" + p.getCidade() + "</td>\n" +
            		  "\t<td align=\"center\" valign=\"middle\"><a href=\"/usuario/" + p.getCpf() + "\"><img src=\"/image/detail.png\" width=\"20\" height=\"20\"/></a></td>\n" +
            		  "\t<td align=\"center\" valign=\"middle\"><a href=\"/usuario/update/" + p.getCpf() + "\"><img src=\"/image/update.png\" width=\"20\" height=\"20\"/></a></td>\n" +
            		  "\t<td align=\"center\" valign=\"middle\"><a href=\"javascript:confirmarDeleteUsuario('" + p.getCpf() + "', '" + p.getNome() + "', '" + p.getSobrenome() + "', '" + p.getUsername + "', '" + p.getTelefone + "', '" + p.getCidade "');\"><img src=\"/image/delete.png\" width=\"20\" height=\"20\"/></a></td>\n" +
            		  "</tr>\n";
		}
		list += "</table>";		
		form = form.replaceFirst("<LISTAR-USUARIO>", list);				
	}
	
	
	public Object insert(Request request, Response response) {
        String cpf = request.queryParams("cpf");
		String nome = request.queryParams("nome");
		String sobrenome = request.queryParams("sobrenome");
		String username = request.queryParams("username");
		String telefone = request.queryParams("telefone");
		String cidade = request.queryParams("cidade");
		
		String resp = "";
		
		Usuario usuario = new Usuario(cpf, nome, sobrenome, username, telefone, cidade);
		
		if(usuarioDAO.insert(usuario) == true) {
            resp = "Usuario (" + nome + ") inserido!";
            response.status(201); // 201 Created
		} else {
			resp = "Usuario (" + nome + ") não inserido!";
			response.status(404); // 404 Not found
		}
			
		makeForm();
		return form.replaceFirst("<input type=\"hidden\" cpf=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" cpf=\"msg\" name=\"msg\" value=\""+ resp +"\">");
	}

	
	public Object get(Request request, Response response) {
		String cpf = request.params(":cpf");		
		Usuario usuario = (Usuario) usuarioDAO.get(cpf);
		
		if (usuario != null) {
			response.status(200); // success
			makeForm(FORM_DETAIL, usuario, FORM_ORDERBY_NOME);
        } else {
            response.status(404); // 404 Not found
            String resp = "Usuario " + cpf + " não encontrado.";
    		makeForm();
    		form.replaceFirst("<input type=\"hidden\" cpf=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" cpf=\"msg\" name=\"msg\" value=\""+ resp +"\">");     
        }

		return form;
	}

	
	public Object getToUpdate(Request request, Response response) {
		String cpf = request.params(":cpf");		
		Usuario usuario = (Usuario) usuarioDAO.get(cpf);
		
		if (usuario != null) {
			response.status(200); // success
			makeForm(FORM_UPDATE, usuario, FORM_ORDERBY_NOME);
        } else {
            response.status(404); // 404 Not found
            String resp = "Usuario " + cpf + " não encontrado.";
    		makeForm();
    		form.replaceFirst("<input type=\"hidden\" cpf=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" cpf=\"msg\" name=\"msg\" value=\""+ resp +"\">");     
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
        String cpf = request.params(":cpf");
		Usuario usuario = usuarioDAO.get(cpf);
        String resp = "";       

        if (usuario != null) {
        	usuario.setNome(request.queryParams("nome"));
        	usuario.setSobrenome(request.queryParams("sobrenome"));
        	usuario.setUsername(request.queryParams("username"));
        	usuario.setTelefone(request.queryParams("telefone"));
        	usuario.setCidade(request.queryParams("cidade"));
        	usuarioDAO.update(usuario);
        	response.status(200); // success
            resp = "Usuario (CPF " + usuario.getCpf() + ") atualizado!";
        } else {
            response.status(404); // 404 Not found
            resp = "Usuario (CPF \" + usuario.getCpf() + \") não encontrado!";
        }
		makeForm();
		return form.replaceFirst("<input type=\"hidden\" cpf=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" cpf=\"msg\" name=\"msg\" value=\""+ resp +"\">");
	}

	
	public Object delete(Request request, Response response) {
        String cpf = request.params(":cpf");
        Usuario usuario = usuarioDAO.get(cpf);
        String resp = "";       

        if (usuario != null) {
            usuarioDAO.delete(cpf);
            response.status(200); // success
            resp = "Usuario (" + cpf + ") excluído!";
        } else {
            response.status(404); // 404 Not found
            resp = "Usuario (" + cpf + ") não encontrado!";
        }
		makeForm();
		return form.replaceFirst("<input type=\"hidden\" cpf=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" cpf=\"msg\" name=\"msg\" value=\""+ resp +"\">");
	}
}