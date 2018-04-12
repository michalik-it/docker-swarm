FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD demo-0.0.2-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]