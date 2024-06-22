FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY target/crup-app-1.jar /app/crup-app-1.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","/app/crup-app-1.jar"]
