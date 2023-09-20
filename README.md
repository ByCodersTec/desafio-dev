[Para executar o projeto, rodar o comando]

docker-compose up -d

[Para enviar um arquivo CNAB, chamar o seguinte endpoint]

curl --location 'localhost:8080/cnab/file' \
--header 'Content-Transfer-Encoding: UTF-8' \
--header 'Cookie: adminer_key=e4592fe9d1847f236409c8a4ef6f6934; adminer_sid=shggs4ajt5sdnipt29a7tpmcr3; adminer_key=d486ed23673014c5631edbaad0a749fa; adminer_sid=dd0tg4bdlv9blqq16c25v4u1rh' \
--form 'file=@"/C:/Users/hugos/OneDrive/Hering/bycoders/bycoders/desafio-dev/CNAB.txt"'

[Para consultar a lista dos CNABs, chamar o seguinte endpoint]

curl --location 'localhost:8080/cnab' \
--header 'Cookie: adminer_key=d486ed23673014c5631edbaad0a749fa; adminer_sid=dd0tg4bdlv9blqq16c25v4u1rh'

[Para verificar o banco de dados Mysql, utilizar a URL]

http://localhost:8081/
Servidor: mysqldb
Usuário: bycoders
Senha: 123456
Base de Dados: bycoders
