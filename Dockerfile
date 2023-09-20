#
# Build stage
#
FROM maven AS build
COPY src /app/src
COPY pom.xml /app
WORKDIR /app
RUN mvn clean package

FROM openjdk:17-alpine
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]
