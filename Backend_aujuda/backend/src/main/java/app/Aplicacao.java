package app;

import static spark.Spark.*;
import service.UsuarioService;
import service.AnimalService


public class Aplicacao {
	
	private static UsuarioService usuarioService = new UsuarioService();
	
    public static void main(String[] args) {
        port(6789);
        
        staticFiles.location("/public");
        
        post("/usuario/insert", (request, response) -> usuarioService.insert(request, response));

        get("/usuario/:cpf", (request, response) -> usuarioService.get(request, response));
        
        get("/usuario/list/:orderby", (request, response) -> usuarioService.getAll(request, response));

        get("/usuario/update/:cpf", (request, response) -> usuarioService.getToUpdate(request, response));
        
        post("/usuario/update/:cpf", (request, response) -> usuarioService.update(request, response));
           
        get("/usuario/delete/:cpf", (request, response) -> usuarioService.delete(request, response));

             
    }

    private static AnimalService animalService = new AnimalService();
	
    public static void main(String[] args) {
        port(6789);
        
        staticFiles.location("/public");
        
        post("/animal/insert", (request, response) -> animalService.insert(request, response));

        get("/animal/:id_animal", (request, response) -> animalService.get(request, response));
        
        get("/animal/list/:orderby", (request, response) -> animalService.getAll(request, response));

        get("/animal/update/:id_animal", (request, response) -> animalService.getToUpdate(request, response));
        
        post("/animal/update/:id_animal", (request, response) -> animalService.update(request, response));
           
        get("/animal/delete/:id_animal", (request, response) -> animalService.delete(request, response));

             
    }

    private static ConversaService conversaService = new ConversaService();
	
    public static void main(String[] args) {
        port(6789);
        
        staticFiles.location("/public");
        
        post("/conversa/insert", (request, response) -> conversaService.insert(request, response));

        get("/conversa/:id_conversa", (request, response) -> conversaService.get(request, response));
        
        get("/conversa/list/:orderby", (request, response) -> conversaService.getAll(request, response));

        get("/conversa/update/:id_conversa", (request, response) -> conversaService.getToUpdate(request, response));
        
        post("/conversa/update/:id_conversa", (request, response) -> conversaService.update(request, response));
           
        get("/conversa/delete/:id_conversa", (request, response) -> conversaService.delete(request, response));

             
    }

    private static OngService ongService = new OngService();
	
    public static void main(String[] args) {
        port(6789);
        
        staticFiles.location("/public");
        
        post("/ong/insert", (request, response) -> ongService.insert(request, response));

        get("/ong/:cnpj", (request, response) -> ongService.get(request, response));
        
        get("/ong/list/:orderby", (request, response) -> ongService.getAll(request, response));

        get("/ong/update/:cnpj", (request, response) -> ongService.getToUpdate(request, response));
        
        post("/ong/update/:cnpj", (request, response) -> ongService.update(request, response));
           
        get("/ong/delete/:cnpj", (request, response) -> ongService.delete(request, response));

             
    }
}