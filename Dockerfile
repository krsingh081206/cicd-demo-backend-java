# Stage 1: Build Stage
FROM maven:3.9.4-eclipse-temurin-17 AS build

# Set the working directory inside the container
WORKDIR /app

# Copy the Maven configuration files
COPY pom.xml .

# Download dependencies, so that we can leverage Docker cache
RUN mvn dependency:go-offline -B

# Copy the entire project source code
COPY src ./src

# Package the application
RUN mvn clean package -DskipTests=true

# Stage 2: Runtime Stage
FROM eclipse-temurin:17-jre-alpine

# Set the working directory
WORKDIR /app

# Copy the packaged JAR file from the build stage
COPY --from=build /app/target/*.jar app.jar

# Expose port 8080
EXPOSE 8080

# Run the JAR file
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
