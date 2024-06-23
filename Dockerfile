FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY target/vehicle-management-api-1.jar /app/vehicle-management-api-1.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","/app/crup-app-1.jar"]
