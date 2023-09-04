FROM maven:3.8.4-jdk-11 AS build
WORKDIR /app
COPY . .
RUN mvn clean install

FROM openjdk:11-jre-slim
WORKDIR /app
COPY --from=build /app/target/desafio-dev-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]