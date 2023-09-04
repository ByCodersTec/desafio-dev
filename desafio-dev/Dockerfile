FROM maven:3.8.2-jdk-11 AS build
WORKDIR /app
COPY . .
RUN mvn clean install

FROM openjdk:11-jre-slim
WORKDIR /app
COPY --from=build /app/target/desafio-dev.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
