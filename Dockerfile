# Dockerfile for bundling React, Spring Boot, and PostgreSQL

# Stage 1: Build the React frontend
#FROM node:14 AS react-build
#WORKDIR /app/frontend
#COPY frontend/package.json frontend/package-lock.json ./
#RUN npm install
#COPY frontend ./
#RUN npm run build

# Stage 2: Build the Spring Boot backend
FROM maven:3.8.3-openjdk-17 AS spring-build
WORKDIR extracted
COPY backend/pom.xml ./
RUN mvn dependency:go-offline
COPY backend/src ./src
RUN mvn package

# Stage 3: Create the final image
FROM maven:3.8.3-openjdk-17
WORKDIR application
COPY --from=spring-build application/target/backend-0.0.1-SNAPSHOT.jar ./backend.jar

# Copy React build from the first stage
#COPY --from=react-build /app/frontend/build ./frontend

# Copy Spring Boot jar from the second stage
COPY --from=spring-build application/target/backend.jar ./backend.jar

# Install PostgreSQL client (if needed)
# RUN apt-get update && apt-get install -y postgresql-client

# Expose ports (adjust these based on your application's requirements)
EXPOSE 8080
#EXPOSE 3000

ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher"]