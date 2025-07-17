# A Train Reservation System meets Java Spring boot 🎫

A fully featured train reservation REST API built with Java framework Spring boot.

[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)
![Java](https://img.shields.io/badge/Java-17%2B-orange)
![SpringBoot](https://img.shields.io/badge/Spring%20Boot-3.1.5-brightgreen)
![SpringJPA](https://img.shields.io/badge/Spring%20JPA-3.1.5-blue)
![SpringSecurity](https://img.shields.io/badge/Spring%20Security-6.1.5-red)
![Docker](https://img.shields.io/badge/Docker-20.10%2B-blue)
![Jenkins](https://img.shields.io/badge/Jenkins-2.414%2B-lightgrey)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-15%2B-336791)
![Maven](https://img.shields.io/badge/Maven-3.9.3%2B-yellowgreen)
![Jetty](https://img.shields.io/badge/Jetty-12.0%2B-9cf)
![GitHub Actions](https://img.shields.io/badge/GitHub%20Actions-4.0-lightgrey)

## 🚀 Overview

This project implements a train reservation system backend exposing secure, RESTful endpoints for managing trains, reservations, users, authentication, and authorization. It demonstrates real-world enterprise techniques, including:

* **Spring Boot** for streamlined application setup
* **Spring MVC** for REST controller routing
* **Spring Security** for role-based access control (e.g., `ADMIN`, `USER`)
* **Dependency Injection** using Spring IoC
* **Aspect-Oriented Programming (AOP)** for cross-cutting concerns like logging and audit
* **Functional Programming** with `Function<Model, DTO>` converters for clean Model ↔️ DTO transformation
* Solid architecture: Controllers → Services → Repositories
*  Multi-stage Docker builds for optimized containerization
* Jenkins CI/CD pipeline for automated testing and deployment
* Jetty server deployment (extensible to WildFly, GlassFish, etc.)
* Spring Security with role-based access control
* Comprehensive API documentation

---

## 📌 Features

### Core Application

* **User authentication & authorization** (sign-up, login, (TODO: session-based tokens))
* CRUD operations for:

  * Trains
  * Train schedules
  * Reservations
  * Users
    

* Global exception handling with meaningful HTTP responses
* Detailed DTO mapping, separating API models from entity models
* Logging and performance aspects using AOP

* TODO: Reservation validation (seat availability, trip conflicts)
* TODO: Payment checkouts

### Infrastructure

* Containerized deployment with Docker

* CI/CD automation with Jenkins

* Jetty server deployment (optimized for Spring Boot)

* PostgreSQL database integration

---

## 💻 Tech Stack

* Java 17+
* Spring Boot
* Spring Security
* Spring MVC
* Spring Data JPA (with PostgreSQL Database)
* AOP (`@Aspect`, `@Around`, `@Before`, etc.)
* Functional mapping: `Function<Train, TrainDto>` etc.
* Maven build system
* CI/CD Jenkins
* Container Docker
* JUnit & Mockito tests
* Jetty server and Apache Tomcat

---

## 📥 Getting Started

### Prerequisites

* JDK 17+ installed
* Spring boot 3
* Maven 3
* (Optional) Docker for a consistent environment
* PostGres SQL

### Environment Setup

1. Clone the repository:

   ```bash
   git clone https://github.com/imbilalbutt/spring-reservation-system.git
   cd spring-reservation-system
   git checkout cleaned-v1.2
   ```

2. Configure application settings in `application.properties` (or `application.yml`), including:

   * Database connection (default: PostgreSQL)
   * Spring Security credentials
   * Customizable server port

3. Build and run:

   ```bash
   ./mvnw clean package
   ./mvnw spring-boot:run
   ```

   The API will be available at `http://localhost:8080/reservation-system/`.

---

## 📘 API Endpoints

| Resource        | Endpoint                         | Methods                        | Roles           |
| --------------- | -------------------------------- | ------------------------------ | --------------- |
| `Auth`          | `/reservation-system/auth/signup`               | `POST` (register new users)    | —               |
|                 | `/reservation-system/auth/signin`               | `POST` (login & token)         | —               |
| `Train`         | `/reservation-system/trains`                    | `GET`, `POST`, `PUT`, `DELETE` | `ADMIN`         |
| `Schedule`      | `/reservation-system/trains/{id}/schedules`     | `GET`, `POST`, `PUT`, `DELETE` | `ADMIN`         |
| `Reservation`   | `/reservation-system/reservations`              | `GET`, `POST`, `PUT`, `DELETE` | `USER`, `ADMIN` |
| `User`          | `/reservation-system/users`                     | `GET`, `PUT`, `DELETE`         | `ADMIN`         |
| DTO conversions | Internal; invoked in controllers | —                              | —               |


---

## 🐳 Docker Deployment

The project includes a multi-stage `Dockerfile` for optimized container builds:

### Dockerfile Structure

```dockerfile
# STAGE 1: Build stage with Maven
FROM maven:3.8.4-openjdk-17 AS build_project
ENV POSTGRES_DB=lhr_rsv \
    POSTGRES_USER=imbilalbutt \
    POSTGRES_PASSWORD=password@123
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn package -DskipTests

# STAGE 2: Runtime stage
FROM openjdk:17-jdk-slim AS run_application
WORKDIR /app
COPY --from=build_project /app/target/reservation-system.jar .
COPY --from=build_project /app/src/main/resources/ ./resources/
EXPOSE 4000
ENTRYPOINT ["java", "-jar", "/app/reservation-system.jar"]
```

Key Features:
* Multi-stage build reduces final image size

* Dependency caching for faster rebuilds

* Environment variables for database configuration

* Port 4000 exposed for the application

Build & Run Commands:
```bash
# Build the image
docker build -t reservation-system:v1 .

# Run the container
docker run -p 8080:4000 --name reservation-system reservation-system:v1
```

## 🛠️ Jenkins CI/CD Pipeline
The project includes a Jenkinsfile for automated builds and deployments:

Pipeline Features:
* Multi-stage workflow: Checkout → Build → Test → Deploy

* Environment variables for consistent configuration

* Jetty server deployment (can be extended to WildFly/GlassFish)

* PostgreSQL integration with credentials management

* Artifact archiving for build outputs

Deployment Options:
```groovy
environment {
    DEPLOYMENT_TARGET = 'jetty'  // Can be 'jetty', 'wildfly', or other servers
    JETTY_HOME = '/path/to/jetty'
    SERVER_PORT = '9090'
}
```

Server Support:
Primary: Jetty 12.0+ (lightweight and fast)

Extensible: Can be configured for WildFly, GlassFish, or Tomcat

--- 

## 🚢 Deployment Options
### 1. Jetty Server (Default)
```bash
# Using included Dockerfile
docker build -t reservation-system .
docker run -p 8080:4000 reservation-system
```

### 2. Extending to Other Servers
   
Modify the DEPLOYMENT_TARGET in Jenkinsfile:

```groovy
environment {
    DEPLOYMENT_TARGET = 'wildfly'  // Change to desired server
    WILDFLY_HOME = '/path/to/wildfly'
}
``` 
Supported server options:

* Jetty (default)

* WildFly

* GlassFish

* Tomcat (requires WAR packaging)


---

## 🛡 Cross-Cutting & Security

* **AOP Logging** — Logs execution times of service methods
* **Security Config** — Roles, password encoder
* **Exception Aspect** — Handles and formats exceptions consistently

---

## 🔄 Model ↔️ DTO Conversion

Leverages Java Functional interfaces for clean mapping:

```java
Function<Train, TrainDto> trainToDto = train -> new TrainDto(...);
Function<TrainDto, Train> dtoToTrain = dto -> new Train(...);
```

This approach enhances readability and testability by decoupling conversion logic from controllers and services.

---

## 🧪 Testing

* Unit tests: JUnit + Mockito for services, controllers, and AOP aspects
* Integration tests: Load full context, mock security, validate end-to-end behavior

To run tests:

```bash
./mvnw test
```

---


## 🛠️ Contributing

Contributions are welcome! Please:

1. Fork and create a feature branch
2. Write meaningful tests
3. Submit a PR with descriptions of changes

✔️ Ensure all tests pass before submitting.

---

## 📄 License

This project is open source — MIT License.

Copyright (c) 2023 Bilal Ahmad Butt

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.

---

## 🧭 Roadmap & Improvements

Potential enhancements:

* Frontend client (Angular/React)
* JWT authentication & refresh tokens
* Pagination and filtering support
* Full Swagger/OpenAPI documentation


---

## 🙋‍♂️ Contact

For issues or contributions, please open a GitHub issue or reach out to [`@imbilalbutt`](https://github.com/imbilalbutt).

---

*Made with ❤️ using Spring Boot and Jetty*

```text

Key additions:
1. Added Dockerfile section with detailed explanation
2. Added Jenkins CI/CD pipeline documentation
3. Highlighted Jetty as primary server with extensibility to others
4. Added relevant badges (Docker, Jenkins, Jetty)
5. Included deployment options section
6. Maintained all existing content while integrating new information
```

--- 

### ⭐ Tips for polishing further

1. Add badges for build status, coverage, license.
2. Include example `curl` or Postman requests.
3. Add UML/architecture diagrams or API specs (e.g., Swagger UI screenshots).
4. Include a "Known Issues" or "FAQ" section.
