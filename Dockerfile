# Build stage - Maven + JDK 21
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app
COPY . .
RUN mvn clean package

# Run stage - JDK 21
FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /app/target/poultry1-0.0.1-SNAPSHOT.jar .
EXPOSE 8081
# Changed to 8081 to match application.properties
CMD ["java", "-jar", "poultry1-0.0.1-SNAPSHOT.jar"]