FROM adoptopenjdk/openjdk11
ADD target/d8tam-poc-api.jar d8tam-poc-api.jar
ENTRYPOINT ["java","-jar","./d8tam-poc-api.jar"]
EXPOSE 8080
