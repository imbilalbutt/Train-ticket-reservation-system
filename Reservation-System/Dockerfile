# 1 container but 2 different environment


## Docker build image command
# docker build -t reservation-system:v1 .
# docker build -t reservation-system:v2 .

## Docker run command
# docker run -p 8081:4000 reservation-system:v1
# docker run -p 8081:4000 reservation-system:v2

FROM maven:3.8.4-openjdk-17 AS build_project
#FROM openjdk:17 AS build_project

WORKDIR /app

COPY pom.xml .

# Install dependencies
RUN mvn dependency:go-offline

# Copy the model training script into the container
# copy src folder inside Container/app
COPY src ./src
#COPY src /app/src

# Build the application
RUN mvn package -DskipTests
#RUN mvn package /app/src -DskipTests
#java package

# Thie below tries to package
#RUN #mvn install # pom.xml


# STAGE 2
FROM openjdk:17-jdk-slim AS run_application

WORKDIR /app

COPY --from=build_project /app/target/reservation-system.jar .
# Copy the templates directory
COPY --from=build_project  /app/src/main/resources/ ./resources/


EXPOSE 4000

# Define environmental variables
ENV NAME Global

# Commannd to run the jar
ENTRYPOINT  ["java", "-jar", "/app/reservation-system.jar"]
# CMD java -jar /app/reservation-system.jar


