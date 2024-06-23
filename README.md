
# Vehicle Management API using Spring Boot

A simple CRUD (Create, Read, Update, Delete) application built with Spring Boot, demonstrating basic RESTful API operations for managing vehicles, brands, and models.

## Introduction

This project is a basic CRUD application developed using Spring Boot. It provides RESTful API endpoints for managing vehicle-related entities, demonstrating how to perform standard CRUD operations within a Spring Boot application.

## Features

- **Create:** Add new entities via POST requests.
- **Read:** Retrieve entities via GET requests.
- **Update:** Modify existing entities via PUT or PATCH requests.
- **Delete:** Remove entities via DELETE requests.

## Technologies

- **Java**
- **Spring Boot:** Framework for creating production-grade Spring-based applications quickly.
- **Spring Data JPA:** Simplifies data access for Spring applications, including support for CRUD operations.
- **H2 Database:** In-memory database for storing data during development.
- **Maven:** Dependency management and build automation tool.

## Setup

To run this application locally using Docker, follow these steps:

1. **Pull the Docker image from Docker Hub:**

   ```bash
   docker pull gustavobfig/vehicle-management-api:latest
   ```

2. **Run the Docker container:**

   ```bash
   docker run -p 8080:8080 gustavobfig/vehicle-management-api:latest
   ```

3. **Access the API documentation:**

   Open your web browser and navigate to [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html) to access the Swagger UI and interact with the API.

### Explanation:

- **Pull the Docker image:** This command pulls the pre-built Docker image from Docker Hub.
- **Run the Docker container:** This command runs a Docker container from the pulled image, mapping port 8080 of the host to port 8080 of the container so you can access the application via `http://localhost:8080`.
- **Access the API documentation:** Open the specified URL in your web browser to access the Swagger UI, which provides an interactive interface for testing the API endpoints.

### Notes:

- Ensure Docker is installed and running on your local machine.
- Adjust the commands (`docker pull`, `docker run`) to match your specific Docker Hub username and image tag if necessary.
