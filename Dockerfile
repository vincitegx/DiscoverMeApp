FROM openjdk:17
MAINTAINER baeldung.com
WORKDIR /app07564928b8aa4c5791cb0f82ac4b0107
COPY target/backend-0.0.1-SNAPSHOT.jar app.jar
CMD ["java", "-jar", "app.jar"]