# CNAE Transactions Importer 

## Features

- Import transactions from CNAE file
- Show a paginable and sortable list of transactions
- Proccess transactions over a queue on RabbitMQ
- Real time update UI using SignalR socket connection


## Requirements

This project runs over Docker so you gonna need [docker desktop](https://www.docker.com/products/docker-desktop/ "docker desktop") up and running on your machine(linux, mac or windows).

You will also need some libraries/frameworks installed

- [.Net core 6.0](https://dotnet.microsoft.com/pt-br/download/dotnet/6.0 ".Net core 6.0")
- [Angular 16](https://angular.io/guide/setup-local "Angular 16")
- [NodeJs 18+](https://nodejs.org/en "NodeJs 18+")
- [Angular Cli 16](https://angular.io/guide/setup-local "Angular Cli 16")

Also, make sure that the ports 8002, 8080, 4200 and 15672 are not being used or change the url's on the project to use another ones

## Building

This project is using [Visual Studio Community](https://visualstudio.microsoft.com/pt-br/thank-you-downloading-visual-studio/?sku=Community&channel=Release&version=VS2022&source=VSLandingPage&passive=false&cid=2030 "Visual Studio Community") (For windows or mac and you can also use visual studio code) and docker

## Setup startup project

Before running the application, you gonna need to setup the docker compose project as the startup project.

To do that open the solution on visual studio and do as the following picture shows:
[![Setup](https://github.com/charlesfranca/desafio-dev/blob/main/assets/set-startup-project.png?raw=true "Setup")](https://github.com/charlesfranca/desafio-dev/blob/main/assets/set-startup-project.png?raw=true "Setup")

## Running the project

Running this project is acctually very easy.

After having all the previous steps checked you just need to click the run buttom and that's it. Like exampled in the following picture:
[![Running](https://github.com/charlesfranca/desafio-dev/blob/main/assets/running-project.png?raw=true "Running")](https://github.com/charlesfranca/desafio-dev/blob/main/assets/running-project.png?raw=true "Running")

## Application URL's

After running the docker compose project, you will have 4 applications running. The api, the frontend, the worker that proccess RabbitMQ messages and last but not least the RabbitMQ server.

Here are the URL's you will use:

- Frontend: http://localhost:4200/
- API Swagger documentation: http://localhost:8080/swagger/index.html
- RabbitMQ: http://localhost:15672/#/

## Rabbit MQ connection info
- Username: guest
- Password: guest

## SqlServer Connection info
- Host: localhost,8002
- Username: sa
- Password: S3cur3P@ssW0rd!
