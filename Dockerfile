FROM maven:3.8-openjdk-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean install -DskipTests

FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

COPY --from=build target/bff-agendador-tarefas-0.0.1-SNAPSHOT.jar bff-agendador-tarefas.jar

EXPOSE 8083

CMD ["java", "-jar", "bff-agendador-tarefas.jar"]