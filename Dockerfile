# Dockerfile for bundling React, Spring Boot, and PostgreSQL

# Stage 1: Build the React frontend
FROM node:14 AS react-build
WORKDIR /app/frontend
COPY frontend/package.json frontend/package-lock.json ./
RUN npm install
COPY frontend ./
RUN npm run build

# Stage 2: Build the Spring Boot backend
FROM maven:3.8-jdk-11 AS spring-build
WORKDIR /app/backend
COPY backend/pom.xml ./
RUN mvn dependency:go-offline
COPY backend/src ./src
RUN mvn package

# Stage 3: Create the final image
FROM openjdk:11-jre-slim
WORKDIR /app

# Copy React build from the first stage
COPY --from=react-build /app/frontend/build ./frontend

# Copy Spring Boot jar from the second stage
COPY --from=spring-build /app/backend/target/backend.jar ./backend.jar

# Install PostgreSQL client (if needed)
# RUN apt-get update && apt-get install -y postgresql-client

# Expose ports (adjust these based on your application's requirements)
EXPOSE 8080
EXPOSE 3000

CMD ["java", "-jar", "backend.jar"]