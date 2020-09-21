FROM openjdk:8-alpine

RUN printf 'cd  /opt \nmkdir -m 777 workspace \ncd workspace' >> create_workspace.sh

CMD ["sh", "create_workspace.sh"]

COPY maven/doadores.jar /opt/workspace/doadores.jar
COPY maven/resources/data.json /opt/workspace/data.json

RUN sh -c 'touch /opt/workspace/doadores.jar'

VOLUME /tmp

WORKDIR /

RUN printf 'cd /opt/workspace/ \njava -Xmx1024m -XX:MaxPermSize=1024m -jar doadores.jar' >> docker-entrypoint.sh

ENTRYPOINT ["sh", "docker-entrypoint.sh"]