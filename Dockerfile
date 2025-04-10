# Build stage - Maven + JDK
FROM maven:3.8.5-openjdk-11 AS build
WORKDIR /app
COPY . .
RUN mvn clean package

# Run stage - JDK only
FROM openjdk:11-jre-slim
WORKDIR /app
COPY --from=build /app/target/poultry1-0.0.1-SNAPSHOT.jar .
CMD ["java", "-jar", "poultry1-0.0.1-SNAPSHOT.jar"]