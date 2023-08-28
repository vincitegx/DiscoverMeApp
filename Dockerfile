FROM openjdk:17
MAINTAINER baeldung.com
WORKDIR /app
COPY target/backend-0.0.1-SNAPSHOT.jar app.jar
CMD ["java", "-jar", "app.jar"]