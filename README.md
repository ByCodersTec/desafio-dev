# Desafio programação - para vaga desenvolvedor
		Esta aplicação consiste em Ler um arquivo posicional no padrão/layout abaixo e após os devidos ajustes de typagem dos campos,
	os dados devem serão gravados no banco postgres. Após isso, os mesmos ficarão disponíveis para consulta.

# Estrutura do sistema
		O Sistema consiste em um frontend em Angular e um backend em quarkus/java. 
	**REPOSITORIOS**
		Por conveniência, coloquei no mesmo repositorio os arquivos do front, back e docker. Na seguinte estrutura:
			RAIZ:	
				- pasta code (onde se encontram os projetos front e backend);
				- Docker (onde contém as informações de dockerfile com as execuções presentes no SETUP deste documento;
				- Arquivo CNAB.txt para utilização
	**DOCKER**
		No diretório Docker na pasta raiz, encontramos o dockerfile e scripts para a criação do banco de dados postegres;
		Para o java/quarkus, consta dentro da pasta backend/src/main/docker os arquivos dockerfile para geração da build do mesmo.
		
	**FRONTEND**
		Para o front utilizamos a framework Angular com a biblioteca Angular material para parte designer e recursos dos componentes.
		Nesta forma serão encontradas 3 rotas, uma para a Home (página inicial), a de Upload do arquivo onde neste será possível a inserção de um arquivo formato cnab para processamento e uma rota para consulta dos dados.

	**BACKEND**
		No Backend foi utilizado a linguagem Java com o Quarkus para desenvolvimento e lógica do sistema. Foi inserido também o OpenApi para documentação das apis existentes no sistema.
		
		Segue abaixo a documentação do Swagger:
			openapi: 3.0.3
				info:
				  title: ByCoders Tests (development)
				  description: Consiste em executar um upload de arquivo CNAB e apresentar em tela
					as informaçoes gravadas
				  contact:
					name: Ednezer de Godoi Rezende
					email: godoirezende@gmail.com
				  license:
					name: Apache 2.0
					url: https://www.apache.org/licenses/LICENSE-2.0.html
				  version: 1.0.1
				paths:
				  /cnab:
					get:
					  tags:
					  - Cnab Resource
					  responses:
						"200":
						  description: OK
				  /cnab/upload:
					post:
					  tags:
					  - Cnab Resource
					  responses:
						"200":
						  description: OK

#  SETUP
	**DOCKER **
		Docker Postgres
			Execute os seguintes comandos na ordem:
				- docker build -t bycoders-dev .   ;
				- docker network create --driver bridge my_network_bycoders; ATENÇÃO (caso de erro, trocar o driver por ics)
				- docker run --rm --name teste-docker-postgres --network=my_network_bycoders -e "POSTGRES_PASSWORD=admin" -p 5432:5432 -d bycoders-dev
				
		Docker Quarkus
			Dentro da basta backend, execute os seguintes comandos:
				- ./mvnw package; 
				- docker build -f src/main/docker/Dockerfile.jvm -t quarkus/desafio-dev-java-jvm . ;
				- docker run -i --rm --network=my_network_bycoders -p 8080:8080 -d quarkus/desafio-dev-java-jvm 
					
		Docker FRONTEND
			Na pasta front, execute o comando abaixo:
				- docker-compose -f "docker-compose.yml" up -d --build;
				
		Documentação API da aplicação
			Utilizado o OpenApi: 
				http://localhost:8080/swagger-ui/index.html 
			