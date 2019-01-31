FROM openjdk:8-alpine

RUN addgroup -S appgroup && adduser -S -G appgroup appuser
USER appuser

WORKDIR /usr/myapp

COPY ./target/spring-boot-app.jar .

EXPOSE 7777

CMD ["java", "-jar", "spring-boot-app.jar"]