# CNAB Project
Projeto desenvolvido para processamento de arquivos CNAB para fluxo de caixas. O projeto encontra-se no path `cnab_project` e é recomendável que toda a parte de configuração da aplicação ocorra dentro dela.

## Requisitos e ferramentas utilizadas
- Python 3.9
- Pipenv

## Instalação
Configure o arquivo `.env_example` e renomeie para `.env` e utilize os comandos:

### Usando Pipfile
```
pipenv install && pipenv run manage makemigrations && pipenv run manage migrate
```
Para rodar o servidor utilize:
```
pipenv run app
```
### Usando Gunicorn + Pipenv
```
pipenv run manage makemigrations && pipenv run manage migrate
```
Para rodar o servidor utilize:
```
pipenv run gunicorn
```

### Testes

Para testar a aplicação digite o comando:
```
pipenv run manage test
```


