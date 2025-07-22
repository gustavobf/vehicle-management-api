FROM eclipse-temurin:23-jdk-alpine

WORKDIR /app

COPY target/vehicle-management-api-1.0.0.jar /app/vehicle-management-api-1.0.0.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/vehicle-management-api-1.0.0.jar"]