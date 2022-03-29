# Instruções de setup do projeto.

#Backend
1. Clonar o projeto do fork acrochajr
2. Em seguida, verificar se o tem instalado o docker na maquina
3. O projeto esta dividio em 2 partes, dos dockers para instalação
4. A parte do front desenvolvido em angular e a parte do backend desenvolido em node+express utilizando a base de dados  postgresql.
5. Para Iniciar o projeto siga os seguintes passos:
    - Na pasta do projeto entrar no diretorio server, listando os arquivos vc encontrara o docker-compose.yml, em seguida execulte o comando a baixo:
    docker-compose up
    - O serviço se inicia com o start no node e postgres(com a importação da tabela), para testar pode acessar essa url: http://localhost:8000/ 
    - Para a documentação da api utilizei o swagger , apos levantar o serviço do backend(node+postgres), vc pode acessar o link a seguir http://localhost:8000/api-docs/#/default para visualizar as rotas.
- Backend esta divido em 4 pastas com seguintes diretorios e arquivos
    -src/ 
        - config/config.js: Configurações de conexão com o banco
        - routes/index.js: pagina de teste.
        - routes/upload.js: esta divido em 2 rotas, uma para enviar os txt a outra para lista as informações do arquivo.
        - services/db.js: faz o acesso ao banco execultando a query
        - services/readcnab.js: contem a regra de upload e leitura do arquivo.
        - uploads: contem os arquivos enviados.
    - index: contem a inicialização dos modulos do express e as rotas criadas.

#Front

1. Iniciar o front end, acesse a para principal do projeto vc encontrara o dockerFile, onde estão os comandos para fazer o build e iniciar o front, segue: 
    docker build -t bycoder_angular . 
    docker run -p 8081:80 bycoder_angular

2. Agora vc pode acessar a url: http://localhost:8081/ , abrir a pagina home com as funções do projeto.    
OBS: foi criada a pagina de login, mas devido as demandas do serviço(entrega de projeto agora no final do mes) nao consegui concluir segue o link http://localhost:8081/login e os testes.

- Front esta divido na estrutura do angular
  - src/app
  - src/app/components
    - footer
    - table-grid-parser
    - upload-file 
  - src/app/pages
    - home
    - login
  - src/app/pipes
  - src/app/share
  - assets
  - environments
    - environments: informações 