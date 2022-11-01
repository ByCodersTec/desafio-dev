# Store Manager

Esse projeto consiste em parsear [este arquivo de texto(CNAB)](https://github.com/ByCodersTec/desafio-ruby-on-rails/blob/master/CNAB.txt) e salvar suas informações(transações financeiras) em uma base de dados.

# Sobre

Esse projeto é composto por uma interface web que aceita upload do [arquivo CNAB](https://github.com/ByCodersTec/desafio-ruby-on-rails/blob/master/CNAB.txt) (que contêm informações sobre movimentações financeiras de várias lojas), normaliza os dados e armazena em um banco de dados relacional MySQL. As informações das movimentações são exibidas na interface web.


# Tecnologias Utilizadas

## Back-end

- [Python](https://www.python.org/)
- [Django](https://www.djangoproject.com/)
- [Django Rest Framework](https://www.django-rest-framework.org/)
- [MySQL](https://www.mysql.com/)

## Front-end

- HTML
- [Jinja](https://jinja.palletsprojects.com/en/3.0.x/)

## Testes

- Testes de unidade = [Pytes](https://docs.python.org/3/library/unittest.html)
```bash
pytest -v 
```
- Testes de Padronização e Formatação
[Pylint](https://pypi.org/project/pylint/)
[Flake8](https://pypi.org/project/flake8/)

```bash
pylint apps.users.views.py

flake8 apps.users.views.py
```


# Como Utilizar?

```bash
# Clonar esse repositório
$ https://github.com/MiqSA/desafio-dev.git

# Entrar no pasta do projeto
$ cd desafio-dev

# Subir aplicação pelo docker
$ docker-compose up

# Mude para branch de desenvolvimento 
$ git checkout features

# Sincronize com o repositório
$ git pull

# Garantir que o docker desktop está ativo 

$ docker-compose up --build

# A aplicação estará funcionando em http://0.0.0.0:8000/

```

# Observações

Dados iniciais

```bash
python manage.py loaddata transactions
```

# Melhorias
- Front-end com uso de CSS e JavaScript para melhorar experiência do usuário.
- Testes de carga devem ser efetuados.
