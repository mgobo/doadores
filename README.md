# doadores

# Aplicação Doadores
> Responsável por devolver doadores de sangue.
Aplicação Roda em Container ou **_Standalone_**

# Requisitos
## Executando em container:

<ol>
<li><b>Execute o POSTGRES SQL em container <i>Script 1:</i></b></li>
<li><b>Execute o CONTAINER Público "DOADORES"<i>Script 2:</i></b></li>
</ol>

## Script 1:
#NETWORK
docker network create citel \

#POSTGRES
docker run --name postgres-doadores --network citel \
-e POSTGRES_USER=doadoresdb \
-e POSTGRES_PASSWORD=doadoresdb \
-e POSTGRES_DB=doadores \
-p 5432:5432 \
-v /usr/local/opt/dockerdata/pgsql-doadores:/var/lib/postgresql/data \
-d postgres:9.6.19

## Script 2:
docker run --name doadores-app --network citel \
-e URL=jdbc:postgresql://postgres-doadores:5432/doadores \
-e USERNAME=doadoresdb \
-e PASSWORD=doadoresdb \
-e ENVIRONMENT=PRD \
-p 8082:8080 \
-v /usr/local/opt/dockerdata/pgsql-doadores:/var/lib/postgresql/data \
-it mgobo/doadores:latest


## Executando standalone:

>> Configure previamente o PostgresSQL na máquina;
Crie o banco de dados: doadores
Crie o usuário e senha: doadoresdb / doadoresdb

Registre as variáveis de ambiente:
URL         = jdbc:postgresql://localhost:5432/doadores
USERNAME    = doadoresdb
PASSWORD    = doadoresdb
ENVIRONMENT = PRD

Copie o arquivo data.json para o diretório "/opt/workspace/";
Execute o arquivo doadores.jar

## PARA AMBOS AMBIENTES, O CONTEXT-ROOT DA APLICACAO é: http://localhost:<<SUA_PORTA>>/ui-doadores/all