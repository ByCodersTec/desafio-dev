# Desafio, Desenvolvedor Java Sênior - Andrelino


O desafio foi versionado no GITHUB, dividido em dois subdiretórios no repositório, back-end e front-end.


# Back-end
Desenvolvido com o ecossistema Spring, utilizando toda facilidade do spring boot, testes automatizados, documentação swagger. Possui somente um endpoint onde envia o arquivo e recebe um json contendo as informações importadas com o saldo atual da conta.
<br>
O banco de dados utilizado foi o postgres tem um arquivo docker-compose.yml que levanta um container com o banco, a configuração de acesso da API ao banco está no projeto, no pacote resource, application.properties um dos principais ponto de configuração, o servidor embedado vai subir na porta 8001.
<br>
#Instalando em ambiente local
<br>
1 - pré requisito, Java versão 11+ e Maven 3+, Docker e Git.
<br>
2 - Faça um clone do projeto em terminal digite <b>git clone https://github.com/andregoiania2/desafio-dev.git</b>.
<br>
3 - Vamos levantar o container com o banco de dados, na raiz do diretório <b>desafio-dev</b> existe um arquivo com o nome docker-compose.yml vamos executá-lo com o código em terminal <b>docker-compose up -d</b>.
<br>
4 - Verifique se realmente subiu o container <docker ps> deverá ter como resposta a informação dos dois contêineres o do banco <b>postgres</b> e o <b>pgadmin4</b> no container postgres copie o CONTAINER ID. Caso no resultado não apareçam os dois container, rode novamente o docker-compose no passo 3 e repita esse passo 4.
<br>
5 - Vamos pegar o numero de IP para ser usado na configuração do banco de dados, execute o comando <b>docker inspect <container_id> | grep "IPAddress"</b> lembre de substituir o container ID, copie o número de IP.
<br>
6 - Entre no diretório <b>/desafio-dev/back-end/src/main/resources</b>, abra o arquivo application.properties, e coloque o numero de IP que vc copiou no passo anterior na linha 3 <b>spring.datasource.url=jdbc:postgresql://172.23.0.2:5432/dados
</b>, salva a alteração.
<br>
7- Vamos compilar o projeto para gerar o artefato <b>desafio-dev-andrelino
.jar</b> entre no diretório <b>/desafio-dev</b> no terminal digite o comando <b>mvn clean install</b>
<br>.
8 - Com o comando acima executado após obter sucesso, vai ser criado uma pasta <b>target</b>, vamos agora subir a API, entre na pasta no terminal e execute o comando <b>java -jar desafio-dev-andrelino.jar </b> <br> 
9 - Para acessar o swagger da API, no browser digite http://localhost:8001/swagger-ui/index.html.
<br>
10 - Para testar no Postman import o comando <b>curl --location 'http://localhost:8001/transacoes/cnab/upload' \
--form 'cnab=@"/home/andrelino/temp/CNAB.txt"'
</b>.




# Front-end


Desenvolvido usando o ecossistema Spring, utilizando o benefício do spring boot, com Thymeleaf usando Bootstrap, uma página simples mas capaz de interagir com o back-end.
<br>
Existe apenas duas pagina Html, uma que o usuário escolhe o arquivo em sua máquina e submetê, onde é enviado para o back-end e feito o processamento do arquivo e o seu retorno é uma lista agrupada por loja e com as operações que foram extraídas do arquivo, junto com um totalizador do saldo da conta.
<br>


#Instalando em ambiente local
<br>
1 - Como já fizemos o clone do projeto para instalar o back-end, vamos apenas compilar o projeto, para gerar o artefato <b>desafio-dev-andrelino-front.jar</b>. Já dentro da pasta /desafio-dev/front-end no terminal digite o comando <b>mvn clean install</b>
<br>
2 - Com o comando acima executado após obter sucesso, vai ser criado uma pasta <b>target</b>, vamos agora subir a API, entre na pasta no terminal e execute o comando <b>java -jar desafio-dev-andrelino-front.jar </b> 
<br>
3 - Para acessar a página, em um web browser digite http://localhost:8002/cnab, escolha o arquivo CNAB.txt e submete.


#Divida Técnica
<br>
1 - Corrigir testes, em TransacoesControllerTest do back-end e UploadControllerTest do front-end ambos estão com problemas com o MockMultipartFile de enviar o arquivo nulo.
<br>
2 - Não foi testado e nem desenvolvido o comportamento de enviar um arquivo fora do leiaute, que com certeza terá muitas exceções.


