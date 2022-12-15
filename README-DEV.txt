Instruções básicas

#Levantando o ambiente
OBS: É preciso ter instalado na máquina docker e docker-compose, caso o usuário não tenha permissão para acessar o docker sempre adicione o comando sudo antes. Certifique-se que as portas 80 e 3306 não estejam sendo usadas e liberadas .

1 - Acesse o terminal e navegue até a pasta raiz do projeto, desafio-dev.
2 - Rode o comando docker-compose up -d.
3 – Para garantir que  aplicação tenha acesso aos arquivos rode o comando,
sudo chmod -R 777 www/ . 
4 - Acesse o container do webserver para rodar as migrações de banco de dados.
	4.1 - Rode o comando docker exec -it webserver /bin/bash.
	4.2 - Rode o comando  php yii migrate.
	4.3 – Rode o comando exit.

#Para navegação via web
1 - Acesse o link http://localhost/web/index.php, link raiz da aplicação.
2 - No menu superior acesse o link Enviar arquivo cnab. Essa página é responsável pelo envio do arquivo e salvar os registros no banco.
3 - Para visualização dos registros cnab acesse o menu Registros CNAB no menu lateral. Esse crud foi realizado pelo build do próprio framework yii.

#Para consumo da API

1 - Acesse o link http://localhost/web/index.php/api, link raiz da api, módulo de api criando pelo build do framework yii onde segue o padrão rest.

OBS: Para trabalhar com json no header da requisição adicione o parâmetro
content-type application/json.

2 - Para interação com api.
	2.1 – Listagem, usando o método GET acesse o link raiz.
	2.2 – View, usando o método GET acesse http://localhost/web/index.php/api + id	
	2.3 – Delete, usando o método DELETE acesse http://localhost/web/index.php/api + id	
	2.4 – Update, usando o método PUT, acesse http://localhost/web/index.php/api  + id.
	2.4.1 – No corpo da requisição envio os campos e valores que serão alterados.
	2.4.2 – Campos e tipos de dados para update.
		tipo_transacao => inteiro tamanho 1, ID conforme readme.
		data => string tamanho 8, yyyymmdd.
		valor => string precisão de 2 casas, 125,50.  
		cpf => string tamanho 11.
		cartao => string tamanho 12.
		hora => string tamanho 6, hhmmss 145607.
		dono_loja => string tamanho 14.
		nome_loja =>  string tamanho 19.