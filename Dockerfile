# Check dependencies
FROM maven:3-openjdk-17-slim AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline

# Build project
COPY src ./src
RUN mvn clean package -DskipTests

# Run project
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/target/url-shortener-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]