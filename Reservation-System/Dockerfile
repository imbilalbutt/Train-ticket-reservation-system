FROM openjdk:17

EXPOSE 8080

ADD target/reservation-system.jar reservation-system.jar

# Commannd to run the jar
ENTRYPOINT  ["java", "-jar", "/reservation-system.jar"]
