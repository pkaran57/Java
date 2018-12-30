FROM openjdk:8

WORKDIR /usr/myapp

COPY ./target/spring-boot-app.jar .

EXPOSE 7777

CMD ["java", "-jar", "spring-boot-app.jar"]