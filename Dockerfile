# Stage 1: Build the application
FROM gradle:8.6.0-jdk21 as builder

COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src

RUN gradle build --no-daemon

# Stage 2: Run the application
FROM amazoncorretto:21

EXPOSE 8080
COPY --from=builder /home/gradle/src/build/libs/*.jar /app/

ENTRYPOINT ["java", "-jar", "/app/guest-list-0.0.1-SNAPSHOT.jar"]
