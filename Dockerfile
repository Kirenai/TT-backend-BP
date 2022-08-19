FROM openjdk:11-slim
COPY target/fullstackbp-0.0.1-SNAPSHOT.jar fullstackbp.jar
CMD ["java", "-jar", "fullstackbp.jar"]