FROM openjdk:11

COPY ./jar/data-analysis-system.jar ./

ENV JAVA_OPTS="-Xms64m -Xmx64m -Duser.timezone=America/Sao_Paulo"

EXPOSE 8080

ENTRYPOINT java $JAVA_OPTS -jar /data-analysis-system.jar