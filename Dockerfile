# Fase de construcción
FROM maven:3.9.6-eclipse-temurin-17-alpine AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn package -DskipTests

# Fase de ejecución
FROM eclipse-temurin:17-jdk-jammy
WORKDIR /app
COPY --from=build /app/target/franquicias-api-*.jar ./franquicias-api.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "franquicias-api.jar"]