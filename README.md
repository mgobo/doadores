# Aplicação Doadores
Responsável por devolver doadores de sangue.
Aplicação Roda em Container ou **_Standalone_**.

# Requisitos
## Executando em container:

<ol>
<li><b>Execute o POSTGRES SQL em container <i>Script 1:</i></b></li>
<li><b>Execute o CONTAINER Público "DOADORES" <i>Script 2:</i></b></li>
</ol>

## Script 1:

## Script to Create a network:
    docker network create citel 

## Script to Running a postgres container:
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
    -d mgobo/doadores:latest

**_O ARQUIVO "docker-run.sh" presente no diretório "src/main/docker", cria a network, executa o container do postgres e também executa o container da aplicação (doadores-app)_**

## Executando standalone:

<ul>
    <li>Configure previamente o PostgresSQL na máquina;</li>
    <li>Crie o banco de dados: doadores</li>
    <li>Crie o usuário e senha: doadoresdb / doadoresdb</li>
</ul>

## Registre as variáveis de ambiente:

<ul>
    <li>URL         = jdbc:postgresql://localhost:5432/doadores</li>
    <li>USERNAME    = doadoresdb</li>
    <li>PASSWORD    = doadoresdb</li>
    <li>ENVIRONMENT = PRD</li>
    <li>Copie o arquivo data.json para o diretório "/opt/workspace/"</li>
    <li>Execute o arquivo doadores.jar</li>
</ul>

## PARA AMBOS AMBIENTES, O CONTEXT-ROOT DA APLICACAO é: http://localhost:<<SUA_PORTA>>/ui-doadores/all
