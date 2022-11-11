# Desafio programação

![Badge em Desenvolvimento](http://img.shields.io/static/v1?label=STATUS&message=CONCLUIDO&color=GREEN&style=for-the-badge)

##### Este projeto foi escrito em PHP utilizando o framework Laravel,tendo como banco de dados do tipo relacional o MySQL. A funcionalidade principal deste projeto é receber um arquivo .txt no padrão CNAB e parsear as informações do arquivo para salva-las em um banco de dados.
# :hammer: Funcionalidades do projeto
1- `Receber arquivo CNAB`: O arquivo CNAB é recebido através de uma rota do tipo POST que receber o arquivo e verifica cada linha afim de decifrar as informações nele contida e verificar se estão no padrão CNAB. <br>
2- `Salvar as informações parseadas em um banco de dados`: Após a leitura do arquivo, cada informação é direcionada a sua entidade. <br>
3- `Listar importações realizadas`: O programa lista as informações gravadas no banco de dados, assim como o saldo total de cada empresa.<br>

## ✔️ Técnicas e tecnologias utilizadas

- ``Laravel 8``
- ``MySQL``
- ``Paradigma de orientação a objetos``
- ``HTML``
- ``CSS``
- ``JavaScript``
- ``Docker``

## ✔️ Para rodar e ver como o projeto ficou bacana, você precisa: 

### Copiar o arquivo .envExample para o .env

```
.env
```
### Rodar o docker com o comando abaixo:

```
docker-compose up -d
```
### Rotas para formulário e listagem das empresas:

```
localhost:8009/create
localhost:8009/list
```
### Neste link esta a documentação para consumir as API's:

```
http://localhost:8009/swagger/
```

