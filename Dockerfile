FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

COPY build/libs/EventController-0.0.1-SNAPSHOT.jar /app/EventController-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java", "-jar", "/app/EventController-0.0.1-SNAPSHOT.jar"]