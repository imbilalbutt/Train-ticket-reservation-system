# 1 container but 2 different environment


# Build an image using Dockerfile. Give a name and tag to image using -t
# docker build -t app_name:tag <container_directory>

## Docker build image command
# docker build -t reservation-system:v1 .
# docker build -t reservation-system:v2 .

## Docker run command
# docker run -p 8081:4000 --name reservation-system-v1 reservation-system:v1
# docker run -p 8082:4000 --name reservation-system-v2 reservation-system:v2
# docker run -p 3000:4000 --name reservation-system-v4 reservation-system:4

# If change dockerfile ==> always do docker build

# STAGE 1: Solve dependencies in container,
#          Copy code in container,
#          Package jar file
FROM maven:3.8.4-openjdk-17 AS build_project
#FROM openjdk:17 AS build_project

#Deine env variables inside Docker container
ENV POSTGRES_DB=lhr_rsv
ENV POSTGRES_USER=imbilalbutt
ENV POSTGRES_PASSWORD=password
#ENV POSTGRES_PASSWORD=password@123


## RUN mkdir -p /home/app

# Create directory insider Docker
WORKDIR /app

# Copy pom file from my laptop to container /home/runner/app/.
COPY pom.xml .

# Install dependencies
# RUN can run any linux command
RUN mvn dependency:go-offline -B

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

COPY --from=build_project /app/target/reservation-system.jar ./app.jar
# Copy the templates directory
COPY --from=build_project  /app/src/main/resources/ ./resources/


# Expose container port, container will listen here, (but still you need to attach it to your host port)
# Jetty server port inside CONTAINER
EXPOSE 4000

# Define environmental variables
ENV NAME Global
#ENV PORT 5432
#ENV MY_DATABASE_NAME lhr_rsv
#
#ENV SPRING_DATASOURCE_URL=jdbc:postgresql://postgres:${PORT}/${MY_DATABASE_NAME}
#ENV SPRING_DATASOURCE_USERNAME=${POSTGRES_USER}
#ENV SPRING_DATASOURCE_PASSWORD=${POSTGRES_PASSWORD}
#ENV SPRING_JPA_HIBERNATE_DDL_AUTO=update

# These are defaults that can be overridden by docker-compose
### Match with application.properties, and also put the same port in docker-compose
# THis port number allocated port in DOCKER container (not in your local host machine)
# This is where container will run Spring application
ENV SERVER_PORT=4000
#ENV SPRING_DATASOURCE_URL=jdbc:postgresql://reservation-system:5432/lhr_rsv
ENV SPRING_DATASOURCE_URL=jdbc:postgresql://db-service:5432/lhr_rsv
ENV SPRING_DATASOURCE_USERNAME=imbilalbutt
ENV SPRING_DATASOURCE_PASSWORD=password
ENV SPRING_JPA_HIBERNATE_DDL_AUTO=update
ENV SPRING_SQL_INIT_MODE=always

# Commannd to run the jar
#ENTRYPOINT  ["java", "-jar", "/app/reservation-system.jar"]
ENTRYPOINT  ["java", "-jar", "app.jar"]
# CMD java -jar /app/reservation-system.jar



#FROM tomcat:9.0
#
#EXPOSE 4001
#
#COPY target/*.jar /usr/local/tomcat/webapps/


### Concept


# Build an image using Dockerfile. Give a name and tag to image using -t
# Dockerfile creates environments in containter, and Docker-compose actually runs Dockerfile.
# Basically, if you remember, you used to pull images / or create your own images using
# some commands. But it was very annoyting to run same commands again and again.
# Thus, to remove this issues of running same command again and again, all commands are
# combined / composed in a single file called docker-compose

## Docker run command to create image
# docker run -p 8081:4000 --name reservation-system-v1 reservation-system:v1
# docker run -p 8082:4000 --name reservation-system-v2 reservation-system:v2

#To execute dockerfile: (Think of dockerfile as a recipe to create a custom dish (container image))
#docker build -t name:tag .

## Docker build image command
# docker build -t reservation-system:v1 .
# docker build -t reservation-system:v2 .

# To execute docker-compose
#docker-compose up


# Execute a shell command in docker
# docker exec -it myPostgres psql -U imbilalbutt -d lhr_rsv -c "SELECT * FROM users;"



# Why it was not connecting to db?
# First create a docker container of postgres db


# docker run == is equal to == docker compose
#docker run -p 5400:5432 \
#-v  /Users/imbilalbutt/Developer/Projects2/Java/reservation_system_data:/var/lib/postgresql/data \
#--env POSTGRES_USER=admin_user \
#--env POSTGRES_PASSWORD=password \
#--env POSTGRES_DB=db \
#--name reservation-system-db \
#--pull missing \
#--network app-network \
# postgres:9.3

# then run