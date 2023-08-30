# desafio-dev

# Rodando o Projeto

Iniciar a aplicação normalmente na porta 8080


# Insert dos tipos de transações
 Executar o Insert abaixo no banco
 no desafio é informado para usarmos o banco de nossa preferencia e logo abaixo pede pra usar Postgre ou MYSQL
 optei por usar o H2 que funcina bem e resolveu o problema.


 #Acessar o H2
 link depois e rodar o projeto : 
 
 http://localhost:8080/h2-console

 usuario : sa
 password : 

 #Executar o script

 INSERT INTO tb_transaction_type (description, transaction_type, signal, dt_insert)
VALUES
    ('Débito', 'Entrada', '+', CURRENT_TIMESTAMP),
    ('Boleto', 'Saída', '-', CURRENT_TIMESTAMP),
    ('Financiamento', 'Saída', '-', CURRENT_TIMESTAMP),
    ('Crédito', 'Entrada', '+', CURRENT_TIMESTAMP),
    ('Recebimento Empréstimo', 'Entrada', '+', CURRENT_TIMESTAMP),
    ('Vendas', 'Entrada', '+', CURRENT_TIMESTAMP),
    ('Recebimento TED', 'Entrada', '+', CURRENT_TIMESTAMP),
    ('Recebimento DOC', 'Entrada', '+', CURRENT_TIMESTAMP),
    ('Aluguel', 'Saída', '-', CURRENT_TIMESTAMP);


# Acessar a o index da aplicação

http://localhost:8080/


Selecione o arquivo CNAB e clique em enviar, após isso o tratamento de dados é realizado e salvo na base

# Buscar o total de movimentação por Proprietario
importar o curl url abaixo no postman e realizar a requisição passando o nome do proprietario 

curl --location 'http://localhost:8080/cnab/store?name=MARIA'


