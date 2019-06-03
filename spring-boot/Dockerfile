FROM openjdk:11-slim

RUN addgroup --system --gid 500 apps && adduser --system --shell /bin/bash --gid 500 appuser
USER appuser

WORKDIR /usr/myapp

COPY ./target/spring-boot-app.jar .

EXPOSE 7777

CMD ["java", "-jar", "spring-boot-app.jar"]