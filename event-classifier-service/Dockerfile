FROM openjdk:11-jdk-slim
MAINTAINER ITAcademy

COPY target/event-classifier-service-1.0.0.jar event-classifier-service-1.0.0.jar

ENTRYPOINT ["java", "-jar", "/event-classifier-service-1.0.0.jar"]