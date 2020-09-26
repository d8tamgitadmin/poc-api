FROM adoptopenjdk/openjdk11
COPY . /usr/src/d8tam/pocapi/
WORKDIR /usr/src/d8tam/pocapi/
EXPOSE 8080
ENTRYPOINT ["java","-Dspring.profiles.active=dev -Dspring.config.location=classpath:application.yml -Dspring.banner.location=classpath:banner.txt","-jar","daedalus-api-0.0.1-SNAPSHOT.jar"]

