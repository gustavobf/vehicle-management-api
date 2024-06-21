# CRUD App using Spring Boot

A simple CRUD (Create, Read, Update, Delete) application built with Spring Boot, demonstrating basic RESTful API operations.

## Introduction

This project is a basic CRUD application developed using Spring Boot. It provides RESTful API endpoints for managing entities, demonstrating how to perform standard CRUD operations within a Spring Boot application.

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

To run this application locally, follow these steps:

1. **Clone the repository:**

   ```bash
   git clone https://github.com/gustavobf/crud-app-spring.git
   cd crud-app-spring

2. **Build the application:**

   ```bash
   mvn package

3. **Run the application:**

   ```bash
   java -jar target/crud-app-spring-1.0.jar


### Explanation:

- **Clone the repository:** This command clones the project repository from GitHub to your local machine and navigates into the project directory.

- **Build the application:** This command uses Maven (`mvn`) to build the application. The `package` phase compiles the code, runs tests, and packages the application into a JAR file located in the `target` directory.

- **Run the application:** This command executes the generated JAR file to start the Spring Boot application locally on your machine.

### Notes:

- Make sure you have Maven installed and configured properly on your local machine.
- Adjust the commands (`git clone`, `mvn package`, `java -jar ...`) as per your project's specific requirements and environment setup.
