// declara um conjunto inicial de contatos
var db_contatos_inicial = {
    "data": [
        {
            "id": 1,
            "nome": "Cão Viver",
            "cidade": "Belo Horizonte",
            "categoria": "ONG",
            "email": "caoviver@contato.br",
            "telefone": "31 90000-0000",
            "website": "caoviver.com"
        },
        {
            "id": 2,
            "nome": "Veterinária Ministério Arca de Noé",
            "cidade": "Belo Horizonte",
            "categoria": "Veterinário",
            "email": "arcadenoevet@gmail.com",
            "telefone": "31 91111-1111",
            "website": "arcadenoevet.com.br"
        },
        {
            "id": 3,
            "nome": "Abrigo Balaio de Gato",
            "cidade": "Belo Horizonte",
            "categoria": "ONG",
            "email": "balaiodegato@outlook.com",
            "telefone": "31 92222-2222",
            "website": "balaiodegato.com.br"
        },
        {
            "id": 4,
            "nome": "Projeto Cãomer",
            "cidade": "Betim",
            "categoria": "ONG",
            "email": "caomer@contato.org",
            "telefone": "31 93333-3333",
            "website": "caomer.org"
        },
        {
            "id": 5,
            "nome": "Asas e Amigos",
            "cidade": "Contagem",
            "categoria": "ONG",
            "email": "asaseamigos@gmail.com",
            "telefone": "31 94444-4444",
            "website": "asaseamigos.net"
        },
        {
            "id": 6,
            "nome": "Casa Mia",
            "cidade": "Belo Horizonte",
            "categoria": "ONG",
            "email": "casamia@contato.org",
            "telefone": "31 95555-5555",
            "website": "casamia.org"
        },
        {
            "id": 7,
            "nome": "Cadastre sua ONG!",
            "cidade": "Belo Horizonte",
            "categoria": "ONG",
            "email": "-",
            "telefone": "-",
            "website": "-"
        },
        {
            "id": 8,
            "nome": "Cadastre sua ONG!",
            "cidade": "Belo Horizonte",
            "categoria": "ONG",
            "email": "-",
            "telefone": "-",
            "website": "-"
        },
        {
            "id": 9,
            "nome": "Cadastre sua ONG!",
            "cidade": "Belo Horizonte",
            "categoria": "ONG",
            "email": "-",
            "telefone": "-",
            "website": "-"
        },
        {
            "id": 10,
            "nome": "Cadastre sua ONG!",
            "cidade": "Belo Horizonte",
            "categoria": "ONG",
            "email": "-",
            "telefone": "-",
            "website": "-"
        }
    ]
}

// Caso os dados já estejam no Local Storage, caso contrário, carrega os dados iniciais
var db = JSON.parse(localStorage.getItem('db_contato'));
if (!db) {
    db = db_contatos_inicial
};

// Exibe mensagem em um elemento de ID msg
function displayMessage(msg) {
    $('#msg').html('<div class="alert alert-warning">' + msg + '</div>');
}

function insertContato(contato) {
    // Calcula novo Id a partir do último código existente no array (PODE GERAR ERRO SE A BASE ESTIVER VAZIA)
    let novoId = 1;
    if (db.data.length != 0) 
      novoId = db.data[db.data.length - 1].id + 1;
    let novoContato = {
        "id": novoId,
        "nome": contato.nome,
        "email" : contato.email,
        "telefone": contato.telefone,
        "cidade" : contato.cidade,
        "categoria": contato.categoria,
        "website": contato.website
    };

    // Insere o novo objeto no array
    db.data.push(novoContato);
    displayMessage("Contato inserido com sucesso");

    // Atualiza os dados no Local Storage
    localStorage.setItem('db_contato', JSON.stringify(db));
}

function updateContato(id, contato) {
    // Localiza o indice do objeto a ser alterado no array a partir do seu ID
    let index = db.data.map(obj => obj.id).indexOf(id);

    // Altera os dados do objeto no array
    db.data[index].nome = contato.nome,
    db.data[index].email = contato.email,
    db.data[index].telefone = contato.telefone,
    db.data[index].cidade = contato.cidade,
    db.data[index].categoria = contato.categoria,
    db.data[index].website = contato.website

    displayMessage("Contato alterado com sucesso");

    // Atualiza os dados no Local Storage
    localStorage.setItem('db_contato', JSON.stringify(db));
}

function deleteContato(id) {    
    // Filtra o array removendo o elemento com o id passado
    db.data = db.data.filter(function (element) { return element.id != id });

    displayMessage("Contato removido com sucesso");

    // Atualiza os dados no Local Storage
    localStorage.setItem('db_contato', JSON.stringify(db));
}