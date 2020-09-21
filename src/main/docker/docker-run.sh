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

SLEEP 20
echo 'Waiting startup database finish...'

#APP
docker run --name doadores-app --network citel \
-e URL=jdbc:postgresql://postgres-doadores:5432/doadores \
-e USERNAME=doadoresdb \
-e PASSWORD=doadoresdb \
-e ENVIRONMENT=PRD \
-p 8082:8080 \
-v /usr/local/opt/dockerdata/pgsql-doadores:/var/lib/postgresql/data \
-it mgobo/doadores:latest