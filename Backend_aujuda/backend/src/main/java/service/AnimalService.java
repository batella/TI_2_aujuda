package service;

import java.util.Scanner;
import java.time.LocalDate;
import java.io.File;
import java.time.LocalDateTime;
import java.util.List;
import dao.AnimalDAO;
import model.Animal;
import spark.Request;
import spark.Response;


public class AnimalService {

	private AnimalDAO animalDAO = new AnimalDAO();
	private String form;
	private final int FORM_INSERT = 1;
	private final int FORM_DETAIL = 2;
	private final int FORM_UPDATE = 3;
	private final int FORM_ORDERBY_ID_ANIMAL = 1;
	private final int FORM_ORDERBY_NOME = 2;
	private final int FORM_ORDERBY_RACA = 3;
    private final int FORM_ORDERBY_PORTE = 4;
    private final int FORM_ORDERBY_CATEGORIA = 5;
    private final int FORM_ORDERBY_FK_ONG_CNPJ = 6;
    private final int FORM_ORDERBY_SEXO = 7;
    private final int FORM_ORDERBY_IDADE = 8;
	
	
	public AnimalService() {
		makeForm();
	}

	
	public void makeForm() {
		makeForm(FORM_INSERT, new Animal(), FORM_ORDERBY_NOME);
	}

	
	public void makeForm(int orderBy) {
		makeForm(FORM_INSERT, new Animal(), orderBy);
	}

	
	public void makeForm(int tipo, Animal animal, int orderBy) {
		String nomeArquivo = "form.html";
		form = "";
		try{
			Scanner entrada = new Scanner(new File(nomeArquivo));
		    while(entrada.hasNext()){
		    	form += (entrada.nextLine() + "\n");
		    }
		    entrada.close();
		}  catch (Exception e) { System.out.println(e.getMessage()); }
		
		String umAnimal = "";
		if(tipo != FORM_INSERT) {
			umAnimal += "\t<table width=\"80%\" bgcolor=\"#f3f3f3\" align=\"center\">";
			umAnimal += "\t\t<tr>";
			umAnimal += "\t\t\t<td align=\"left\"><font size=\"+2\"><b>&nbsp;&nbsp;&nbsp;<a href=\"/animal/list/1\">Novo Animal</a></b></font></td>";
			umAnimal += "\t\t</tr>";
			umAnimal += "\t</table>";
			umAnimal += "\t<br>";			
		}
		
		if(tipo == FORM_INSERT || tipo == FORM_UPDATE) {
			String action = "/animal/";
			String name, nome, buttonLabel;
			if (tipo == FORM_INSERT){
				action += "insert";
				name = "Inserir Animal";
				nome = "joao, maria";
				buttonLabel = "Inserir";
			} else {
				action += "update/" + animal.getId_animal();
				name = "Atualizar Animal (ID_ANIMAL " + animal.getId_animal() + ")";
				nome = animal.getNome(); //nao deveria poder atualizar todos os dados?
				buttonLabel = "Atualizar";
			}
			umAnimal += "\t<form class=\"form--register\" action=\"" + action + "\" method=\"post\" id_animal=\"form-add\">";
			umAnimal += "\t<table width=\"80%\" bgcolor=\"#f3f3f3\" align=\"center\">";
			umAnimal += "\t\t<tr>";
			umAnimal += "\t\t\t<td colspan=\"3\" align=\"left\"><font size=\"+2\"><b>&nbsp;&nbsp;&nbsp;" + name + "</b></font></td>";
			umAnimal += "\t\t</tr>";
			umAnimal += "\t\t<tr>";
			umAnimal += "\t\t\t<td colspan=\"3\" align=\"left\">&nbsp;</td>";
			umAnimal += "\t\t</tr>";
			umAnimal += "\t\t<tr>";
			umAnimal += "\t\t\t<td>&nbsp;Nome: <input class=\"input--register\" type=\"text\" name=\"nome\" value=\""+ animal.getNome() +"\"></td>";
			umAnimal += "\t\t\t<td>Raca: <input class=\"input--register\" type=\"text\" name=\"raca\" value=\""+ animal.getRaca() +"\"></td>";
			umAnimal += "\t\t\t<td>Porte: <input class=\"input--register\" type=\"text\" name=\"porte\" value=\""+ animal.getPorte() +"\"></td>";
			umAnimal += "\t\t</tr>";
			umAnimal += "\t\t<tr>";
            umAnimal += "\t\t\t<td>&nbsp;ID_ANIMAL: <input class=\"input--register\" type=\"text\" name=\"id_animal\" value=\""+ animal.getId_animal().toString() + "\"></td>";
			umAnimal += "\t\t\t<td>&nbsp;Categoria: <input class=\"input--register\" type=\"text\" name=\"categoria\" value=\""+ animal.getCategoria() + "\"></td>";
			umAnimal += "\t\t\t<td>Fk_ong_cnpj: <input class=\"input--register\" type=\"text\" name=\"fk_ong_cnpj\" value=\""+ animal.getFk_ong_cnpj() + "\"></td>";
			umAnimal += "\t\t\t<td>Sexo: <input class=\"input--register\" type=\"text\" name=\"sexo\" value=\""+ animal.getSexo().toString() + "\"></td>";
			umAnimal += "\t\t\t<td>Idade: <input class=\"input--register\" type=\"text\" name=\"idade\" value=\""+ animal.getIdade().toString() + "\"></td>";
			umAnimal += "\t\t\t<td align=\"center\"><input type=\"submit\" value=\""+ buttonLabel +"\" class=\"input--main__style input--button\"></td>";
			umAnimal += "\t\t</tr>";
			umAnimal += "\t</table>";
			umAnimal += "\t</form>";		
		} else if (tipo == FORM_DETAIL){
			umAnimal += "\t<table width=\"80%\" bgcolor=\"#f3f3f3\" align=\"center\">";
			umAnimal += "\t\t<tr>";
			umAnimal += "\t\t\t<td colspan=\"3\" align=\"left\"><font size=\"+2\"><b>&nbsp;&nbsp;&nbsp;Detalhar Animal (ID_ANIMAL " + animal.getID_ANIMAL() + ")</b></font></td>";
			umAnimal += "\t\t</tr>";
			umAnimal += "\t\t<tr>";
			umAnimal += "\t\t\t<td colspan=\"3\" align=\"left\">&nbsp;</td>";
			umAnimal += "\t\t</tr>";
			umAnimal += "\t\t<tr>";
			umAnimal += "\t\t\t<td>&nbsp;Nome: "+ animal.getNome() +"</td>";
			umAnimal += "\t\t\t<td>Raca: "+ animal.getRaca() +"</td>";
			umAnimal += "\t\t\t<td>Porte: "+ animal.getPorte() +"</td>";
			umAnimal += "\t\t</tr>";
			umAnimal += "\t\t<tr>";
            umAnimal += "\t\t\t<td>&nbsp;ID_ANIMAL: "+ animal.getId_animal().toString() + "</td>";
			umAnimal += "\t\t\t<td>&nbsp;Categoria: "+ animal.getCategoria() + "</td>";
			umAnimal += "\t\t\t<td>Fk_ong_cnpj: "+ animal.getFk_ong_cnpj() + "</td>";
			umAnimal += "\t\t\t<td>&nbsp;SEXO: "+ animal.getSexo().toString() + "</td>";
			umAnimal += "\t\t\t<td>&nbsp;IDADE: "+ animal.getIdade().toString() + "</td>";
			umAnimal += "\t\t\t<td>&nbsp;</td>";
			umAnimal += "\t\t</tr>";
			umAnimal += "\t</table>";		
		} else {
			System.out.println("ERRO! Tipo não identificado " + tipo);
		}
		form = form.replaceFirst("<UM-ANIMAL>", umAnimal);
		
		String list = new String("<table width=\"80%\" align=\"center\" bgcolor=\"#f3f3f3\">");
		list += "\n<tr><td colspan=\"6\" align=\"left\"><font size=\"+2\"><b>&nbsp;&nbsp;&nbsp;Relação de Animals</b></font></td></tr>\n" +
				"\n<tr><td colspan=\"6\">&nbsp;</td></tr>\n" +
    			"\n<tr>\n" + 
        		"\t<td><a href=\"/animal/list/" + FORM_ORDERBY_ID_ANIMAL + "\"><b>ID_ANIMAL<ID_ANIMAL/a></td>\n" +
        		"\t<td><a href=\"/animal/list/" + FORM_ORDERBY_NOME + "\"><b>Nome</b></a></td>\n" +
        		"\t<td><a href=\"/animal/list/" + FORM_ORDERBY_RACA + "\"><b>Raca</b></a></td>\n" +
                "\t<td><a href=\"/animal/list/" + FORM_ORDERBY_PORTE + "\"><b>Porte</b></a></td>\n" +
                "\t<td><a href=\"/animal/list/" + FORM_ORDERBY_CATEGORIA + "\"><b>Categoria</b></a></td>\n" +
                "\t<td><a href=\"/animal/list/" + FORM_ORDERBY_FK_ONG_CNPJ + "\"><b>Fk_ong_cnpj</b></a></td>\n" +
                "\t<td><a href=\"/animal/list/" + FORM_ORDERBY_SEXO + "\"><b>Fk_ong_cnpj</b></a></td>\n" +
                "\t<td><a href=\"/animal/list/" + FORM_ORDERBY_IDADE + "\"><b>Fk_ong_cnpj</b></a></td>\n" +
        		"\t<td width=\"100\" align=\"center\"><b>Detalhar</b></td>\n" +
        		"\t<td width=\"100\" align=\"center\"><b>Atualizar</b></td>\n" +
        		"\t<td width=\"100\" align=\"center\"><b>Excluir</b></td>\n" +
        		"</tr>\n";
		
		List<Animal> animals;
		if (orderBy == FORM_ORDERBY_ID_ANIMAL) {                 	animals = animalDAO.getOrderById_animal();
		} else if (orderBy == FORM_ORDERBY_NOME) {		animals = animalDAO.getOrderByNome();
		} else if (orderBy == FORM_ORDERBY_RACA) {			animals = animalDAO.getOrderByRaca();
        } else if (orderBy == FORM_ORDERBY_PORTE) {			animals = animalDAO.getOrderByPorte();
        } else if (orderBy == FORM_ORDERBY_CATEGORIA) {			animals = animalDAO.getOrderByCategoria();
        } else if (orderBy == FORM_ORDERBY_FK_ONG_CNPJ) {			animals = animalDAO.getOrderByFk_ong_cnpj();
        } else if (orderBy == FORM_ORDERBY_SEXO) {			animals = animalDAO.getOrderBySexo();
        } else if (orderBy == FORM_ORDERBY_IDADE) {			animals = animalDAO.getOrderByIdade();
		} else {											animals = animalDAO.get();
		}

		int i = 0;
		String bgcolor = "";
		for (Animal p : animals) {
			bgcolor = (i++ % 2 == 0) ? "#fff5dd" : "#dddddd";
			list += "\n<tr bgcolor=\""+ bgcolor +"\">\n" + 
            		  "\t<td>" + p.getId_animal() + "</td>\n" +
            		  "\t<td>" + p.getNome() + "</td>\n" +
            		  "\t<td>" + p.getRaca() + "</td>\n" +
                      "\t<td>" + p.getPorte() + "</td>\n" +
                      "\t<td>" + p.getCategoria() + "</td>\n" +
                      "\t<td>" + p.getFk_ong_cnpj() + "</td>\n" +
                      "\t<td>" + p.getSexo() + "</td>\n" +
                      "\t<td>" + p.getIdade() + "</td>\n" +
            		  "\t<td align=\"center\" valign=\"middle\"><a href=\"/animal/" + p.getId_animal() + "\"><img src=\"/image/detail.png\" width=\"20\" height=\"20\"/></a></td>\n" +
            		  "\t<td align=\"center\" valign=\"middle\"><a href=\"/animal/update/" + p.getId_animal() + "\"><img src=\"/image/update.png\" width=\"20\" height=\"20\"/></a></td>\n" +
            		  "\t<td align=\"center\" valign=\"middle\"><a href=\"javascript:confirmarDeleteAnimal('" + p.getId_animal() + "', '" + p.getNome() + "', '" + p.getRaca() + "', '" + p.getPorte + "', '" + p.getCategoria + "', '" + p.getFk_ong_cnpj + "', '" + p.getSexo + "', '" + p.getIdade "');\"><img src=\"/image/delete.png\" width=\"20\" height=\"20\"/></a></td>\n" +
            		  "</tr>\n";
		}
		list += "</table>";		
		form = form.replaceFirst("<LISTAR-ANIMAL>", list);				
	}
	
	
	public Object insert(Request request, Response response) {
        int id_animal = Integer.parseInt(request.queryParams("id_animal"));
		String nome = request.queryParams("nome");
		String raca = request.queryParams("raca");
		String porte = request.queryParams("porte");
		String categoria = request.queryParams("categoria");
		String fk_ong_cnpj = request.queryParams("fk_ong_cnpj");
		char sexo = Character.parseChar(request.queryParams("sexo"));
		int idade = Integer.parseInt(request.queryParams("idade"));
		
		
		String resp = "";
		
		Animal animal = new Animal(id_animal, nome, raca, porte, categoria, fk_ong_cnpj, sexo, idade);
		
		if(animalDAO.insert(animal) == true) {
            resp = "Animal (" + nome + ") inserido!";
            response.status(201); // 201 Created
		} else {
			resp = "Animal (" + nome + ") não inserido!";
			response.status(404); // 404 Not found
		}
			
		makeForm();
		return form.replaceFirst("<input type=\"hidden\" id_animal=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id_animal=\"msg\" name=\"msg\" value=\""+ resp +"\">");
	}

	
	public Object get(Request request, Response response) {
		int id_animal = Integer.parseInt(request.queryParams("id_animal"));		
		Animal animal = (Animal) animalDAO.get(id_animal);
		
		if (animal != null) {
			response.status(200); // success
			makeForm(FORM_DETAIL, animal, FORM_ORDERBY_NOME);
        } else {
            response.status(404); // 404 Not found
            String resp = "Animal " + id_animal + " não encontrado.";
    		makeForm();
    		form.replaceFirst("<input type=\"hidden\" id_animal=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id_animal=\"msg\" name=\"msg\" value=\""+ resp +"\">");     
        }

		return form;
	}

	
	public Object getToUpdate(Request request, Response response) {
		int id_animal = Integer.parseInt(request.queryParams("id_animal"));		
		Animal animal = (Animal) animalDAO.get(id_animal);
		
		if (animal != null) {
			response.status(200); // success
			makeForm(FORM_UPDATE, animal, FORM_ORDERBY_NOME);
        } else {
            response.status(404); // 404 Not found
            String resp = "Animal " + id_animal + " não encontrado.";
    		makeForm();
    		form.replaceFirst("<input type=\"hidden\" id_animal=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id_animal=\"msg\" name=\"msg\" value=\""+ resp +"\">");     
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
		int id_animal = Integer.parseInt(request.queryParams("id_animal"));
		Animal animal = animalDAO.get(id_animal);
        String resp = "";       

        if (animal != null) {
        	int id_animal = Integer.parseInt(request.queryParams("id_animal"));
    		String nome = request.queryParams("nome");
    		String raca = request.queryParams("raca");
    		String porte = request.queryParams("porte");
    		String categoria = request.queryParams("categoria");
    		String fk_ong_cnpj = request.queryParams("fk_ong_cnpj");
    		char sexo = Character.parseChar(request.queryParams("sexo"));
    		int idade = Integer.parseInt(request.queryParams("idade"));
        	animalDAO.update(animal);
        	response.status(200); // success
            resp = "Animal (ID_ANIMAL " + animal.getId_animal() + ") atualizado!";
        } else {
            response.status(404); // 404 Not found
            resp = "Animal (ID_ANIMAL \" + animal.getId_animal() + \") não encontrado!";
        }
		makeForm();
		return form.replaceFirst("<input type=\"hidden\" id_animal=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id_animal=\"msg\" name=\"msg\" value=\""+ resp +"\">");
	}

	
	public Object delete(Request request, Response response) {
		int id_animal = Integer.parseInt(request.queryParams("id_animal"));
        Animal animal = animalDAO.get(id_animal);
        String resp = "";       

        if (animal != null) {
            animalDAO.delete(id_animal);
            response.status(200); // success
            resp = "Animal (" + id_animal + ") excluído!";
        } else {
            response.status(404); // 404 Not found
            resp = "Animal (" + id_animal + ") não encontrado!";
        }
		makeForm();
		return form.replaceFirst("<input type=\"hidden\" id_animal=\"msg\" name=\"msg\" value=\"\">", "<input type=\"hidden\" id_animal=\"msg\" name=\"msg\" value=\""+ resp +"\">");
	}
}