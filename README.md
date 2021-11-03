#Financial Transactions API

##Aplicação responsável por salvar lista de transações através de upload de arquivos CNAB

###Técnologias e frameworks utilizados:
- Java 13
- Spring 
  - Spring Data JPA
  - Spring Web
  - SpringDoc OpenAPI (Swagger)
- MySQL8
- Flyway (Versionamento do Banco de Dados)
- RestAssured (Testes de Integração)
- JUnit 5 (Testes unitários)
- Docker

Foi pensado que a estrutura do Banco deveria ser versionado, portando criaremos 2 usuários, 
um para a aplicação, com permissão apenas de leitura e escrita e outro para o Flyway, com
mais privilégios, podendo criar, apagar e alterar a estrutura das tabelas.  

### Preparando o ambiente:

####Banco de dados:

Criar container do MySQL à partir da imagem padrão do docker hub:

`docker run --name mysql -e MYSQL_ROOT_PASSWORD=root -p 3306:3306 -d mysql:8.0.27`

Conectar no bash do container MySQL:

`docker exec -it mysql mysql -uroot -p`

Estando dentro do bash do mysql (`mysql >`), executar os comandos à seguir:

```
CREATE DATABASE FINANCIAL_TRANSACTIONS;

CREATE USER 'ft_user'@'%' IDENTIFIED BY 'ft_pwd';
CREATE USER 'migration_user'@'%' IDENTIFIED BY 'migration_pwd';

GRANT ALL ON FINANCIAL_TRANSACTIONS.* TO 'migration_user'@'%' WITH GRANT OPTION;
GRANT INSERT, UPDATE, DELETE, SELECT ON FINANCIAL_TRANSACTIONS.* TO 'ft_user'@'%' WITH GRANT OPTION;
FLUSH PRIVILEGES;
```

####Rodando a API:
Para criar a estrutura inicial das tabelas, no terminal, na raiz do projeto, executar:
`./gradlew flywayMigrate`

Para rodar a aplicação:
`./gradlew bootRun`

Após isso abrir o navegador no endereço: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)



