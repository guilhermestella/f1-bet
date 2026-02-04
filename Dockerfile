FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app

# Cache dependencies first
COPY pom.xml .
RUN mvn dependency:go-offline

# Build the app
COPY src ./src
RUN mvn clean package -DskipTests

# ---- Runtime stage ----
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

# Copy the built jar
COPY --from=build /app/target/*.jar app.jar

# Spring Boot default port
EXPOSE 8080

# Run the app
ENTRYPOINT ["java","-jar","app.jar"]
