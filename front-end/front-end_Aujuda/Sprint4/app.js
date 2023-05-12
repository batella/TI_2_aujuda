// declara um conjunto inicial de contatos
var db_contatos_inicial = {
    "data": [
        {
            "id": 1,
            "nome": "Cindy",
            "sexo": "Fêmea",
            "categoria": "Cachorro",
            "porte": "Grande",
            "idade": "4 anos",
            "cidade": "Belo Horizonte"
        },
        {
            "id": 2,
            "nome": "Lord",
            "sexo": "Macho",
            "categoria": "Cachorro",
            "porte": "Grande",
            "idade": "11 anos",
            "cidade": "Belo Horizonte"
        },
        {
            "id": 3,
            "nome": "Snoopy",
            "sexo": "Macho",
            "categoria": "Cachorro",
            "porte": "Médio",
            "idade": "1 ano",
            "cidade": "Belo Horizonte"
        },
        {
            "id": 4,
            "nome": "Leon",
            "sexo": "Macho",
            "categoria": "Cachorro",
            "porte": "Médio",
            "idade": "1 ano",
            "cidade": "Contagem"
        },
        {
            "id": 5,
            "nome": "Dick",
            "sexo": "Macho",
            "categoria": "Cachorro",
            "porte": "Médio",
            "idade": "12 anos",
            "cidade": "Belo Horizonte"
        },
        {
            "id": 6,
            "nome": "Tigreza",
            "sexo": "Fêmea",
            "categoria": "Gato",
            "porte": "Médio",
            "idade": "10 anos",
            "cidade": "Contagem"
        },
        {
            "id": 7,
            "nome": "Isis",
            "sexo": "Fêmea",
            "categoria": "Gato",
            "porte": "Médio",
            "idade": "10 anos",
            "cidade": "Belo Horizonte"
        },
        {
            "id": 8,
            "nome": "Velma",
            "sexo": "Fêmea",
            "categoria": "Gato",
            "porte": "Médio",
            "idade": "1 ano",
            "cidade": "Belo Horizonte"
        },
        {
            "id": 9,
            "nome": "Vinicios",
            "sexo": "Macho",
            "categoria": "Gato",
            "porte": "Médio",
            "idade": "1 ano",
            "cidade": "Betim"
        },
        {
            "id": 10,
            "nome": "Vanda",
            "sexo": "Fêmea",
            "categoria": "Gato",
            "porte": "Médio",
            "idade": "8 meses",
            "cidade": "Belo Horizonte"
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
        "porte" : contato.porte,
        "idade": contato.idade,
        "sexo" : contato.sexo,
        "categoria": contato.categoria,
        "cidade": contato.cidade
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
    db.data[index].porte = contato.porte,
    db.data[index].idade = contato.idade,
    db.data[index].sexo = contato.sexo,
    db.data[index].categoria = contato.categoria,
    db.data[index].cidade = contato.cidade

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