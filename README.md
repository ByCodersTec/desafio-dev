# interview

## Descrição da Solução

De acordo com o desafio proposto, para cada item descrevi os principais pontos que acho importante, descrevendo a solução adotada, bem como o que acredito ser um ponto de atenção. 

> 1. Ter uma tela (via um formulário) para fazer o upload do arquivo(pontos extras se não usar um popular CSS Framework )

* Na parte Web evitei utilizar qualquer css framework (como Bootstrap), porém se pudesse utilizar um framework css eu utilizaria [tailwind](https://tailwindcss.com/).
* Como não se falou na utilização de um JS framework, escolhi vue.js pela familiaridade que tenho, e escrevi o CSS na mão a maneira antiga.
* Desta forma criei todo o desafio dentro de uma mesma tela, evitando complexidades do projeto, até pela natureza de "desafio".
* Para o upload do **CNAB.txt** criei uma funcionalidade de upload do arquivo e um [endpoint](http://127.0.0.1:5000/api/apidocs/#/default/post_api_byCoders_upload) no backend.
  * Pequenas validações, e maneiras de autilizar o UX, como não foi frisado absolutamente nada sobre a parte frontend, foquei os esforços no backend.

> 2. Interpretar ("parsear") o arquivo recebido, normalizar os dados, e salvar corretamente a informação em um banco de dados relacional, se atente as documentações que estão logo abaixo.
* O [endpoint]() de upload de arquivo, recebe o arquivo lê linha a linha, extrai os dados, transforma os dados e então insere na base de dados.
* Para armazenar os dados, criei uma tabela de transação e uma tabela de loja.
* A loja é inserida com um primary key, e cada transação tem uma loja como foreign key.
  * A chave da loja para inserir é somente o nome dela, como é feriado e acabei não tirando essa dúvida, interpretei desta maneira.
* No tratamento das string, todas fiz o decode byte para utf-8 normalizando as acentuações, e fazendo trim dos espaços.
* No campo valor, converti para float, dividi por 100 para ter os centavos, e caso o tipo da transação fosse saída, multipliquei por -1 para auxiliar em pontos mais a frente.
* No tratamento do campo data, juntei data e hora em um mesmo campo, localizei e gravei com timezone no timezone do Brasil -3, então converti para UTC para facilitar.
  * Gravei no formato UTC pois acredito que o frontend na exibição dos dados tem a "obrigação" de converter a data para o timezone que o browser esta configurado.
  * Ressalto esta parte da conversão da data no código abaixo.
````python
def _convert_to_date(date_str: str, date_format: str='%Y%m%d%H%M%S') -> datetime:
    """
    Function will convert a str date with date_format
     
    :param date_str: the date str
    :param date_format: date format on @date_str. default value is '%Y%m%d%H%M%S'
    
    :return: the datetime object 
    """
    date_format = datetime.strptime(date_str, date_format)
    loc_sp = sao_paulo.localize(date_format)
    return loc_sp.astimezone(utc)
````

> 3. Exibir uma lista das operações importadas por lojas, e nesta lista deve conter um totalizador do saldo em conta

* Na mesma tela de upload de arquivos, o frontend ao carregar a página carrega uma tela de sumário das lojas e o respectivo saldo.
* Para isso o frontend chama o [endpoint](http://127.0.0.1:5000/api/apidocs/#/default/get_api_byCoders_list_all).
  * Este endpoint irá agregar todas as lojas e fazer um SUM do valor, por este motivo que guardei o valor com sinal.
  * Este [endpoint](http://127.0.0.1:5000/api/apidocs/#/default/get_api_byCoders_list_all), também evita carregar uma quantidade enorme de dados, sem a real necessidade do uso, por este motivo que decidi implementar desta forma.
* No frontend quando clicado em cima da loja, ele carrega a lista de transações daquela respectiva loja.
  * Para isso o frontend chama o [endpoint](http://127.0.0.1:5000/api/apidocs/#/default/get_api_byCoders_list__store_id_) passando a **store_id** como parâmetro.

* **Como ficou em aberto o item 3, se era pra mostrar um listão de todas as transações importadas, ou se agregar. Bem como criar um saldo. Então decidi criar dois endpoints um para cada, e fazer esta navegação simplificada no frontend.**

> 4. Ser escrita na sua linguagem de programação de preferência

Escrito em Python, utilizando o framework Flask e no frontend usando vue.js. A moça do RH assim solicitou, e é a Stack que utilizo e tenho maior familiaridade hoje.

> 5. Ser simples de configurar e rodar, funcionando em ambiente compatível com Unix (Linux ou Mac OS X). Ela deve utilizar apenas linguagens e bibliotecas livres ou gratuitas.

Para simplificar o run, configurei tudo dentro de dockers, e um docker compose, e também estou disponibilizando no servidor []()
TODO finalziar configuração DOCKER e passo a passo de como rodar...

> 6. Git com commits atomicos e bem descritos

* Todo o desafio esta em um [repositório público](https://github.com/BRomano/desafio-dev) e devidamente documentado.
* Porém a atomicidade dos commits foi por fases de funcionalidades. Evitei fazer 1 commit para cada modificação por achar péssima abordagem.

> 7. PostgreSQL, MySQL ou SQL Server

* Utilizando Mysql e configurado dentro de docker-compose

> 9. Docker compose (Pontos extras se utilizar)

Cada pasta interna tem o dockerfile respectivo do serviço, sendo separado em:
1. interview é o backend, configurado em Dockerfile para rodar um python
2. Front é a pasta que esta toda a parte do projeto frontend e nessa configuração irá compilar todo o projeto frontend e configurar um nginx para servir como reverse proxy. Sendo que tudo que vier na URL /api será redirecionado para o backend, senão serve a aplicação vue.js.
3. Em docker-compose.yml temos as configurações:
   1. Serviço db, configuração de um mysql, propositalmente ele não tem um binded volume, desta forma se for reiniciado o serviço zera o database. Este banco de dados roda em uma VPC chamada **backnet**, para manter acesso ao banco privado.
   2. Serviço backend, é o docker python, com acesso 

> 11. Incluir informação descrevendo como consumir o endpoint da API

* Toda documentação dos endpoints foi configurado utilizando [openAPI 3.0](https://swagger.io/), entendo que esta é uma ótima solução para disponibilizar configurações, e é um padrão muito bem aceito.
* Para acessar a documentação dos 3 endpoints configurados [acessar](http://127.0.0.1:5000/api/apidocs/#/).
* Inclusive a documentação auxilia para consumir e testar os endpoints, bem como o frontend também os consome.

### Aplicação Não precisa fazer...

> 1. Lidar com autenticação ou autorização (pontos extras se ela fizer, mais pontos extras se a autenticação for feita via OAuth).

Decidi não fazer a autenticação, apesar de ter criado uma rota para uma possivel autenticação, bem como uma tabela de usuario, decidi por não continuar, por causa do tempo, do feriado, e de outros testes que estou aplicando também.

> 2. Ser escrita usando algum framework específico (mas não há nada errado em usá-los também, use o que achar melhor).

Utilizei Python, Flask

> 3. Documentação da api.(Será um diferencial e pontos extras se fizer)

Toda explicação da documentação da API esta descrita no item 11 deste documento, bem como em acessível pelo link [acessar](http://127.0.0.1:5000/api/apidocs/#/) 

### Observações
> Configurado autoflush: True na session connection, para resolver problema de store_id no bulk insert.
> O correto seria o bulk insert ser realizado por uma task do celery, e não em uma request.

> 
