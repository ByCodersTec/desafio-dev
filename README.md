# REQUISITOS
#### Composer,
#### PHP 8.0
#### NPM (caso rode sem o docker)

## USUARIO E SENHA
### email: coders@email.com
### senha: desafio2021

## INSTALAÇÃO COM DOCKER COMPOSE
#### composer install
#### cp .env.dev .env
#### ./sail up -d
#### ./sail artisan storage:link
#### ./sail artisan migrate --seed
#### ./sail npm i
#### ./sail npm run prod
#### ./sail artisan serve

## INSTALAÇÃO SEM DOCKER
#### composer install
####  npm i
#### cp .env.dev .env
#### artisan storage:link
#### artisan migrate --seed

#### npm run prod
#### artisan serve