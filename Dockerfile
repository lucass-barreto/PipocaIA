FROM eclipse-temurin:17
LABEL maintainer="lucasbo.dev@gmail.com"
WORKDIR /pipocaIA
COPY target/pipocaIA-0.0.1-SNAPSHOT.jar pipocaIA.jar
ENTRYPOINT ["java", "-jar", "pipocaIA.jar"]