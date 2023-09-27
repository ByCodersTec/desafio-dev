# CNAB File Upload

 Este é um projeto Java Spring Boot para fazer upload e análise de arquivos CNAB (Cadastro Nacional de Atividades Bancárias). Ele inclui uma API REST para fazer upload de arquivos CNAB e exibir operações de lojas com seus saldos totais.

## Pré-requisitos

Certifique-se de ter os seguintes pré-requisitos instalados em seu sistema:

Java Development Kit (JDK) 17 ou superior

Docker (opcional, se você deseja executar o PostgreSQL em um contêiner Docker)

Certifique-se de ter o Git instalado em sua máquina. Você pode baixá-lo aqui.

## Clonando o repositório
1. Abra o terminal (ou prompt de comando) em sua máquina.

2. Navegue até o diretório onde você deseja clonar o projeto.

3. Execute o seguinte comando para clonar o repositório:

  git clone https://github.com/seu-usuario/seu-repositorio.git

## Configuração do Banco de Dados

O projeto utiliza o PostgreSQL como banco de dados. Você pode configurar o banco de dados editando o arquivo application.properties. As configurações padrão são as seguintes:

spring.datasource.url=jdbc:postgresql://localhost:5432/db_manager_file
spring.datasource.username=postgres
spring.datasource.password=admin

Certifique-se de que o PostgreSQL esteja em execução na porta 5432 e que o banco de dados db_manager_file tenha sido criado.

## Executando o Projeto

1. Navegue até o diretório raiz do projeto:

cd seu-repositorio

2. Inicie o projeto usando Maven:

 ./mvnw spring-boot:run

Isso iniciará a aplicação Spring Boot.

3. Abra um navegador da web e acesse 'http://localhost:8080' para acessar a página inicial da aplicação.

## Fazendo Upload de um Arquivo CNAB

Você pode fazer upload de um arquivo CNAB usando a API REST fornecida. Use uma ferramenta como cURL ou Postman para fazer uma solicitação POST para http://localhost:8080/api/upload, enviando o arquivo CNAB como um parâmetro de formulário com o nome "file".

Exemplo de cURL:

curl -X POST -F "file=@/caminho/para/seu/arquivo.cnab" http://localhost:8080/api/upload

## Visualizando Operações de Loja

Você pode visualizar as operações de lojas e seus saldos totais acessando a seguinte URL em seu navegador:

http://localhost:8080/api/store-operations

Isso exibirá uma lista de operações de loja com seus respectivos saldos totais.

## Desenvolvedor

Este projeto foi desenvolvido por Gustavo Wendell.

## Licença
Este projeto está licenciado sob a Licença MIT - consulte o arquivo LICENSE para obter detalhes.

Espero que este README ajude a configurar e entender o projeto. Você pode personalizá-lo conforme necessário para incluir informações específicas do seu projeto.


