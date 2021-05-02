# Data Analysis System
Serviço batch dedicado a leitura de arquivos ```.dat``` que geram ao final um relatório de vendas.

***

## Requisitos:
- Java 11
- Gradle
- Docker

***

## Métricas de qualidade
### Sonarqube
- Para subir o container:</br>
  ```docker-compose up```</br></br>
  
- Para executar relatório:</br>
  ```./gradlew clean test sonarqube```</br></br>

- Para o acesso: http://localhost:9000/ <br></br>

- Imagem da última execução na data de 01/05/2021:</br>

![Relatorio](src/main/resources/images/sonarqube0105.png)

***
### Pitest
- Para gerar relatório:</br>
  ```./gradlew clean test pitest```</br></br>

- Imagem da última execução na data de 01/05/2021:</br>

![Relatorio](src/main/resources/images/pitest0105.png)

***