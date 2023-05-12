# Informações do Projeto
`TÍTULO DO PROJETO`  

## Maus-tratos contra os animais.

`CURSO` 

## Ciência da Computação

## Participantes

Caio Batella, Caroline Freitas Alvernaz, Danillo Soares Ferreira, Giulia Mendes Reggiani e Pedro Marcelo Ciríaco Moura

# Estrutura do Documento

- [Informações do Projeto](#informações-do-projeto)
  - [Participantes](#participantes)
- [Estrutura do Documento](#estrutura-do-documento)
- [Introdução](#introdução)
  - [Problema](#problema)
  - [Objetivos](#objetivos)
  - [Justificativa](#justificativa)
  - [Público-Alvo](#público-alvo)
- [Especificações do Projeto](#especificações-do-projeto)
  - [Personas e Mapas de Empatia](#personas-e-mapas-de-empatia)
  - [Histórias de Usuários](#histórias-de-usuários)
  - [Requisitos](#requisitos)
    - [Requisitos Funcionais](#requisitos-funcionais)
    - [Requisitos não Funcionais](#requisitos-não-funcionais)
  - [Restrições](#restrições)
- [Projeto de Interface](#projeto-de-interface)
  - [User Flow](#user-flow)
  - [Wireframes](#wireframes)
- [Metodologia](#metodologia)
  - [Divisão de Papéis](#divisão-de-papéis)
  - [Ferramentas](#ferramentas)
  - [Controle de Versão](#controle-de-versão)
- [**############## SPRINT 1 ACABA AQUI #############**](#-sprint-1-acaba-aqui-)
- [Projeto da Solução](#projeto-da-solução)
  - [Tecnologias Utilizadas](#tecnologias-utilizadas)
  - [Arquitetura da solução](#arquitetura-da-solução)
- [Avaliação da Aplicação](#avaliação-da-aplicação)
  - [Plano de Testes](#plano-de-testes)
  - [Ferramentas de Testes (Opcional)](#ferramentas-de-testes-opcional)
  - [Registros de Testes](#registros-de-testes)
- [Referências](#referências)


# Introdução

## Problema

Atualmente, muitas Organizações Não Governamentais (ONGs) de ajuda aos animais relatam ter dificuldades em encontrar contribuintes e pessoas interessadas em adotar os animais que foram maltratados e deixados na ONG para tratamento, ao mesmo tempo em que ainda mais animais maltratados são deixados e, muitas vezes, passam o resto de suas vidas lá. Com isso, notou-se a necessidade de ajudar as ONGs tanto em uma melhor divulgação de suas atividades, quanto na divulgação de seus pets que estão aptos à adoção. Além disso, viu-se a carência de mostrar, de simular e apresentar ao futuro dono de pets os gastos e cuidados que o animal pode ter.



## Objetivos

O projeto possui como objetivo geral fornecer ajuda às ONGs de animais que, principalmente após a vigência da pandemia do Covid-19, estão perdendo seus patrocinadores.
Como objetivos específicos tem-se: apresentar as necessidades básicas que um animal pode ter; divulgar as ONGs e quais são suas necessidades atuais; construir perfis básicos para apresentar os animais ao interessados em adoção; Mostrar a maneira correta de realizar as denúncias contra maus-tratos.


## Justificativa

Ao ver a quantidade de crescente de animais abandonados, foi criado um interesse em entrar em contato com as pessoas competentes, como veterinários e ONGs de ajuda aos animais, a fim de coletar maiores informações sobre o problema e buscar saber de que forma podemos contribuir. Ao contatar essas pessoas, foi exposta a necessidade real de divulgar melhor os pets que estão prontos para a adoção, uma vez que as ONGs, majoritariamente, não suportam a quantidade de animais que possuem, nem podem receber ainda mais animais. Além disso, pelo fato de muitas pessoas devolverem os pets com a justificativa de que não têm condições financeiras de manter o animal, deseja-se construir uma simulação de custos básicos que precisarão ser arcados no caso de a pessoa realmente querer seguir com a adoção. Ainda, muitas pessoas possuem dúvidas em relação à forma correta de denunciar maus-tratos, portanto viu-se a oportunidade e a necessidade de explicar a forma correta de o fazer.


## Público-Alvo

Ainda que todas as pessoas possam usufruir do site, o enfoque está no público que tem interesse em ajudar os animais que sofrem com maus tratos. Assim, foi estabelecido como público-alvo homens e mulheres de 20 a 70 anos que tem como objetivo adotar animais abandonados e/ou que sofreram maus-tratos, e ajudar ONGs com doações e ações voluntárias.

 
# Especificações do Projeto

Deseja-se por meio deste projeto criar aba de ajuda com denúncias contra maus-tratos, facilitar o processo de adoção, fornecer as instruções adequadas àqueles que tem ou que pretendem ter um pet, facilitar o processo de doação para ONGs e sua divulgação, além de mostrar os animais que estão aptos a encontrar um lar definitivo. Deseja-se incluir todas essas informações em uma única ferramenta que seja acessível e simples.

## Personas e Mapas de Empatia

Dentre as personas estudadas, percebeu-se pontos em comum que são a vontade de ajudar mais os animais, um prévio envolvimento com ONGs ou clínicas que realizam alguns procedimentos gratuitos em animais, notou-se uma iniciativa de envolver seus próprios amigos no processo de ajuda aos animais, além de terem uma visão de quais instituições mais precisam de ajuda. Em sua maioria, são mulheres com idade entre 25 e 50 anos que possuem conexão com a natureza.

> 
> 
## Persona
> ![Persona](https://user-images.githubusercontent.com/103450981/163734523-531527af-efd8-483f-bbd5-50b12e81f388.jpeg)

## Mapa de Empatia
![mapa_de_empatia](https://user-images.githubusercontent.com/103450981/163734793-a85d75cf-f142-444a-9cec-e3f0840f22a7.png)

## Mapa de Stakeholders
![Stakeholders](https://user-images.githubusercontent.com/103450981/163734758-a24aa7ba-f847-4d60-bc11-f140b2617247.png)



## Histórias de Usuários

Com base na análise das personas foram identificadas as seguintes histórias de usuários:

|EU COMO... `PERSONA`| QUERO/PRECISO ... `FUNCIONALIDADE` |PARA ... `MOTIVO/VALOR`                 |
|--------------------|------------------------------------|----------------------------------------|
|Maria Julia         | um espaço de fácil comunicação com ONGs | começar sua própria ONG           |
|Marcia              | que exista um serviço em que facilite a alocação de animais encontrados na reserva que foram abandonados e precisam ser tratados| expandir sua ONG e atrair mais membros para a equipe |
|Fernanda            | um software para adotar cachorros resgatados e abrir a própria clínica veterinária | ajudar animais resgatados |



## Requisitos

As tabelas que se seguem apresentam os requisitos funcionais e não funcionais que detalham o escopo do projeto.

### Requisitos Funcionais

|ID    | Descrição do Requisito  | Prioridade |
|------|-----------------------------------------|----|
|RF-001| O site deve oferecer um cadastro de usuário | ALTA | 
|RF-002| O site deve conter perfil completo dos cachorros disponíveis para adoção | ALTA |
|RF-003| O site deve mostrar as ONGs parceiras que precisam ser atendidas e que precisam ser ajudadas | ALTA |
|RF-004| O site deve demonstrar a maneira correta de se denunciar maus-tratos | MÉDIA |
|RF-005| O site deve conter informações diversas sobre como cuidar do seu pet | BAIXA |


### Requisitos não Funcionais

|ID     | Descrição do Requisito  |Prioridade |
|-------|-------------------------|----|
|RNF-001| O sistema deve ser responsivo para rodar em um dispositivos móvel | MÉDIA | 
|RNF-002| Deve processar requisições do usuário em no máximo 3s |  BAIXA | 
|RNF-003|O sistema deve funcionar tanto em dispositivos Android, quanto em iOS |  BAIXA | 

> - [Requisitos Funcionais (RF)](https://pt.wikipedia.org/wiki/Requisito_funcional):
>   correspondem a uma funcionalidade que deve estar presente na
>   plataforma (ex: cadastro de usuário).
>
> - [Requisitos Não Funcionais (RNF)](https://pt.wikipedia.org/wiki/Requisito_n%C3%A3o_funcional):
>   correspondem a uma característica técnica, seja de usabilidade,
>   desempenho, confiabilidade, segurança ou outro (ex: suporte a
>   dispositivos iOS e Android).


## Restrições

O projeto está restrito pelos itens apresentados na tabela a seguir.

|ID| Restrição                                             |
|--|-------------------------------------------------------|
|RE-01| O sistema não garante a resolução do problema, busca-se apenas minimizá-lo |
|RE-02| O sistema exige conexão com a internet para seu funcionamento        |
|RE-03| O sistema deve se restringir às tecnologias básicas da Web no Frontend | 




# Projeto de Interface

O projeto de interface foi elaborado a fim de facilitar a utilização do sistema por parte do cliente principal, que são tanto as ONGs que desejam se cadastrar a fim de adquirir mais recursos, quanto àqueles que desejam ajudar os animais em tratamento, seja com adoção, ou com a doação de recursos. As interfaces de solução foram elaboradas a partir de entrevistas de pessoas do ramo, que tiveram como principal queixa o fato de que poucos animais são adotados, enquanto mutos outros são deixados na ONG e lá permanecem durante toda a vida. As personas estudadas foram apresentadas como pessoas que ou já possuem conhecimento sobre ONGs e já participam de uma, ou querem aprimorar e fazer parte de uma. Após a união de todos esses fatores foi possível construir uma interface que atenda às necessidades dos cientes que são os mais interessados na resolução do problema.


## User Flow

> A seguir segue uma esquematização de como será o fluxo do usuário pretendido ao final:

> ![userflow](https://user-images.githubusercontent.com/103450981/163736028-afb297d9-ef90-41bc-8404-bb2305ee68ef.PNG)


## Wireframes

## Tela Inicial
> Nesta tela, o usuário tem acesso à pagina inicial do site e, através dela, poderá acessar ao perfil dos cães que estão disponíveis para adoção, acessar ao painel de ONGs que estão precisando de recursos para permanecerem com seu funcionamento, além de um painel com dicas para cuidar de seu pet

> ![wireframe1](https://user-images.githubusercontent.com/103450981/163735000-c7b05867-4ead-4edb-9cdb-f68889f6f85c.PNG)

## Tela de Adoção
> Nessa tela, encontram-se descritas as ONGs parceiras que precisam receber as doações, como doação de dinheiro ou doação de recursos no geral
> 
> ![wireframe2](https://user-images.githubusercontent.com/103450981/163735121-39cda9b4-b8ab-4ece-be85-d03545e1f7f9.PNG)

## Página de adoção 
> Na seguinte página, estarão as fotos dos animais disponíveis para serem adotados, assim como um filtro para que os interessados escolham adequadamente o animal que pretende adotar
> 
> ![wireframe3](https://user-images.githubusercontent.com/103450981/163735176-b8b3483a-e00c-4dde-a4ae-7d0d0cda2c0a.PNG)



# Metodologia

O presente trabalho tem por objetivo apontar um problema e propor uma forma de solucioná-lo, analisando a visão de pessoas que estão diretamente ligadas ao problema e que desejam solucioná-lo, fazendo necessária a realização de pesquisas qualitativas. A pesquisa qualitativa foi a escolhida, uma vez que possibilita o aprofundamento de ideias por parte do entrevistado, de forma que este fica livre para responder questões abertas da maneira que interpretar mehor, fornecendo uma visão ampla do problema estudado.

![matrizCSD](https://user-images.githubusercontent.com/103450981/163734843-6d181f58-3e07-48fd-99bf-b1c4fc0392f4.png)

## Divisão de Papéis

O trabalho foi desenvolvido através de uma divisão simples de tarefas, sem que haja um hierarquia específica em que os alunos que se sentem mais confortáveis com certas áreas de atuação tomaram a frente para assumir as tarefas de cada "setor". Os alunos Caio e Pedro ficaram responsáveis pela construção dos wireframes, enquanto os demias, Caroline, Danillo e Giulia focaram na parte da documentação do projeto. Anteriormente, cada integrante ficou responsável por estudar as diferentes personas possíveis para o projeto e, também, responsáveis por entrevistar e coletar dados sobre os clientes atraves de pesquisas qualitativas.



## Ferramentas


| Ambiente  | Plataforma              |Link de Acesso |
|-----------|-------------------------|---------------|
|Processo de Design Thinkgin  | Miro | https://miro.com/app/board/uXjVOB18Ug8=/  | 
|Repositório de código | GitHub | https://github.com/ICEI-PUC-Minas-PMGCC-TI/tiaw-pmg-cc-t-20221-g4-maus-tratos-aos-animais |  
|Protótipo Interativo | Figma | https://www.figma.com/file/4kpFmua7PFnK17rxomAxzQ/Maus-tratos-a-c%C3%A3es-framework?node-id=0%3A1 | 

> As ferramentas empregadas no projeto são:
> 
> - Editor de código: Visual Studio Code
> - Ferramentas de comunicação: WhatsApp e Teams
> - Ferramentas de diagramação: Bizagi
> - Plataforma de hospedagem: GitHub
> 
> O editor de código foi escolhido porque ele possui uma integração com o
> sistema de versão. As ferramentas de comunicação utilizadas possuem
> integração semelhante e por isso foram selecionadas. Por fim, para criar
> diagramas utilizamos essa ferramenta por melhor captar as
> necessidades da nossa solução.


## Controle de Versão


> Discuta como a configuração do projeto foi feita na ferramenta de
> versionamento escolhida. Exponha como a gerência de tags, merges,
> commits e branchs é realizada. Discuta como a gerência de issues foi
> realizada.
> A ferramenta de controle de versão adotada no projeto foi o
> [Git](https://git-scm.com/), sendo que o [Github](https://github.com)
> foi utilizado para hospedagem do repositório `upstream`.
> 
> O projeto segue a seguinte convenção para o nome de branchs:
> 
> - `master`: versão estável já testada do software
> - `unstable`: versão já testada do software, porém instável
> - `testing`: versão em testes do software
> - `dev`: versão de desenvolvimento do software
> 
> Quanto à gerência de issues, o projeto adota a seguinte convenção para
> etiquetas:
> 
> - `bugfix`: uma funcionalidade encontra-se com problemas
> - `enhancement`: uma funcionalidade precisa ser melhorada
> - `feature`: uma nova funcionalidade precisa ser introduzida

# **############## SPRINT 1 ACABA AQUI #############**


# Projeto da Solução

A ideia do software é unir as pessoas que queiram ajudar uma ong ou adotar um pet e ongs no mesmo ambiente, entre outras ferramentas como calculadora média de gastos.

## Tecnologias Utilizadas

......  COLOQUE AQUI O SEU TEXTO ......

> Descreva aqui qual(is) tecnologias você vai usar para resolver o seu
> problema, ou seja, implementar a sua solução. Liste todas as
> tecnologias envolvidas, linguagens a serem utilizadas, serviços web,
> frameworks, bibliotecas, IDEs de desenvolvimento, e ferramentas.
> Apresente também uma figura explicando como as tecnologias estão
> relacionadas ou como uma interação do usuário com o sistema vai ser
> conduzida, por onde ela passa até retornar uma resposta ao usuário.
> 
> Inclua os diagramas de User Flow, esboços criados pelo grupo
> (stoyboards), além dos protótipos de telas (wireframes). Descreva cada
> item textualmente comentando e complementando o que está apresentado
> nas imagens.

## Arquitetura da solução

......  COLOQUE AQUI O SEU TEXTO E O DIAGRAMA DE ARQUITETURA .......

> Inclua um diagrama da solução e descreva os módulos e as tecnologias
> que fazem parte da solução. Discorra sobre o diagrama.
> 
> **Exemplo do diagrama de Arquitetura**:
> 
> ![Exemplo de Arquitetura](images/arquitetura-exemplo.png)


# Avaliação da Aplicação

Executamos como o esperado para ver se funcionaria corretamente, depois executamos como o não esperado para ver as mensagens de erros e achar problemas para resolvermos e nas ferramentas de mais de uma opção tentamos todas para certificar que tudo estava correto.

> Apresente os cenários de testes utilizados na realização dos testes da
> sua aplicação. Escolha cenários de testes que demonstrem os requisitos
> sendo satisfeitos.

## Plano de Testes

Teste do formulário para aprovação e reprovação no processo de adoção: Primeiramente foi respondido o formulário com respostas que desclassificariam o usuário do processo de adoção para certificar-se de que o usuário iria ser desclassificado, após isso foi respondido o formulário sem respostas eliminatórias para certificar-se de que o usuário seria aprovado.

Teste das ferramentas básicas do site como registro e login de usuário: Foi criado um usuário e após isso logamos no site com o mesmo usuário para certificar-se de que tudo funcionaria corretamente.

> Enumere quais cenários de testes foram selecionados para teste. Neste
> tópico o grupo deve detalhar quais funcionalidades avaliadas, o grupo
> de usuários que foi escolhido para participar do teste e as
> ferramentas utilizadas.
> 
> **Links Úteis**:
> - [IBM - Criação e Geração de Planos de Teste](https://www.ibm.com/developerworks/br/local/rational/criacao_geracao_planos_testes_software/index.html)
> - [Práticas e Técnicas de Testes Ágeis](http://assiste.serpro.gov.br/serproagil/Apresenta/slides.pdf)
> -  [Teste de Software: Conceitos e tipos de testes](https://blog.onedaytesting.com.br/teste-de-software/)

## Ferramentas de Testes (Opcional)

......  COLOQUE AQUI O SEU TEXTO ......

> Comente sobre as ferramentas de testes utilizadas.
> 
> **Links Úteis**:
> - [Ferramentas de Test para Java Script](https://geekflare.com/javascript-unit-testing/)
> - [UX Tools](https://uxdesign.cc/ux-user-research-and-user-testing-tools-2d339d379dc7)

## Registros de Testes

Teste de cadastrar um pet no site: Sucesso, não houve problemas.
Teste do formulário para aprovação no processo de adoção: Sucesso, tanto a aprovação quanto a reprovação baseada nas respostas do usuário.
Teste da calculadora média de gastos: Sucesso, totalmente funcional.
Teste das ferramentas básicas do site como resgistro e login de usuário: Sucesso, todas funcionais.

# Referências

> - CANGUÇU, Rafael. O que são Requisitos Funcionais e Requisitos Não Funcionais?. [S.l.]: Codificar, 2021. Disponíve em: <https://codificar.com.br/requisitos-funcionais-nao-funcionais/>. Acesso em: 10 abr. 2022.
> - PAROLE, Versio. Objetivos, Problema de pesquisa e Justificativa. [S.l.: s.n.], 2015. Disponível em: <https://medium.com/@versioparole/objetivos-problema-de-pesquisa-e-justificativa-c98c8233b9c3>. Acesso em: 10 abr. 2022.
> - PRADA, Charles. Brainstorming: o que é e como aplicar na geração de novas ideias. Santa Catarina: EUAX Consulting, 2018. Disponível em: <https://www.euax.com.br/2018/09/brainstorming/>. Acesso em : 11 abr. 2022.
