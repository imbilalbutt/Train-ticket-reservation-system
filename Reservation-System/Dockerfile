# 1 container but 2 different environment


# Build an image using Dockerfile. Give a name and tag to image using -t
# docker build -t app_name:tag <container_directory>

## Docker build image command
# docker build -t reservation-system:v1 .
# docker build -t reservation-system:v2 .

## Docker run command
# docker run -p 8081:4000 --name reservation-system-v1 reservation-system:v1
# docker run -p 8082:4000 --name reservation-system-v2 reservation-system:v2

# If change dockerfile ==> always do docker build

# STAGE 1: Solve dependencies in container,
#          Copy code in container,
#          Package jar file
FROM maven:3.8.4-openjdk-17 AS build_project
#FROM openjdk:17 AS build_project

#Deine env variables inside Docker container
ENV POSTGRES_DB=lhr_rsv \
    POSTGRES_USER=imbilalbutt \
#    POSTGRES_PASSWORD=password
    POSTGRES_PASSWORD=password@123


## RUN mkdir -p /home/app

# Create directory insider Docker
WORKDIR /app

# Copy pom file from my laptop to container /home/runner/app/.
COPY pom.xml .

# Install dependencies
# RUN can run any linux command
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


# STAGE 2: COPY JAR file and resource file to /app/ folder insider container
# Expose a Container port (but inside docker-compose you need to attach it to your own laptop/host port)
FROM openjdk:17-jdk-slim AS run_application

WORKDIR /app

COPY --from=build_project /app/target/reservation-system.jar .
# Copy the templates directory
COPY --from=build_project  /app/src/main/resources/ ./resources/


# Expose container port, container will listen here, (but still you need to attach it to your host port)
EXPOSE 4000

# Define environmental variables
ENV NAME Global

# Commannd to run the jar
ENTRYPOINT  ["java", "-jar", "/app/reservation-system.jar"]
# CMD java -jar /app/reservation-system.jar


#FROM tomcat:9.0
#
#EXPOSE 4001
#
#COPY target/*.jar /usr/local/tomcat/webapps/


