# APP - Importação de arquivo financeiro.
> APP com view para importação de arquivo e controle de movimentação e API para consultas.

O projeto tem por objeto fornecer uma plataforma para que o usuário importe arquivos CNAB em formato de texto e tenha o controle dos registros por relatório e por loja importada.

Na aba de importação é possível utilizar o campo de importação de arquivo através do botão de "Procure" ou arrastando o arquivo até o campo demarcado. Também é possível definir um titulo para o relatório, por exemplo, "Relatório de Maio/2022". Após a importação, o usuário pode acessar a aba de relatório onde apresenta a listagem de relatório importados. Cada relatório listado disponibiliza as movimentações financeiras importadas naquele relatório. Na página por relatório é possível filtrar o saldo por loja através do filtro superior. 
Ainda na página de listagem de relatórios, é possível utilizar o botão "Consultar por Lojas", onde a tela lista as lojas importadas e o Saldo total historico da loja. Também é possível consultas todas as movimentações dessa loja em especifico.

Ao utilizar um arquivo em um formato que não seja txt, uma mensagem avisará que o usuário pode apenas utilizar arquivos de texto. Caso o arquivo tenha linhas não válidas, o sistema irá importar apenas as linhas válidas e ignorará linhas com erro. (Tratamento das linhas com erros em tela não implementado)

A tela no navegador não utiliza de bootstrap, a estrutura foi realizada em display flex e em css grids. Responsividade para mobile não implementada.

Na API, utilizei o método do rack attack para limitar 5 requests a cada 5 segundos.

## Dependências necessárias para rodar
<li>Docker
<li>docker-compose

## Instalação

Para buildar os containers:

```sh
docker-compose build

```
Após o build, o APP poderá ser ligado através do comando:

```sh
docker-compose up

```

Para acessar o bash, utilizar o comando:

```sh
docker exec -it finance-report /bin/bash

```
Dentro do bash, é possível utilizar os comando de database para criação do banco de dados de desenvolvimento e de teste

```sh
rails db:create
rails db:migrate
```

Para rodar os testes automatizados, utilizar o comando abaixo dentro do bash:

```sh
bundle exec rspec spec

```

## Documentação da API

A API poderá ser utilizada para consultas dos relatórios, movimentações e Lojas importadas.

Para exemplos e rotas, consulta a [Documentação da API](https://documenter.getpostman.com/view/15882001/Uz5DrHru)

# Tecnologias e ferramentas utilizadas

<li>Ruby on Rails
<li>Mysql
<li>Docker
<li>docker-compose

<b>Principais gem de teste</b>
<li>Gem: <a href="https://github.com/thoughtbot/shoulda-matchers" >Shoulda Matchers</a>
<li>Gem: <a href="https://github.com/simplecov-ruby/simplecov">SimpleCov</a>
<li>Gem: <a href="https://github.com/thoughtbot/factory_bot">Factory Bot</a>

## Informações do desenvolvedor

[Milles Dyson Schroeder](https://www.linkedin.com/in/milles-schroeder-85144b14b/) – milles.schroeder@gmail.com

[https://github.com/MillesDyson](https://github.com/MillesDyson)