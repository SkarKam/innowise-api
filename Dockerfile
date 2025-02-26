FROM openjdk:22
WORKDIR /app
COPY target/innowise-api-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]