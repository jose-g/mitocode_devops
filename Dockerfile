FROM maven:3.6.3-jdk-11-slim AS builder
WORKDIR /app
COPY pom.xml .
RUN mvn -B -e -C -T 1C org.apache.maven.plugins:maven-dependency-plugin:3.0.2:go-offline
COPY src ./src
RUN ls -la ./src
RUN mvn clean package -Dmaven.test.skip=true

FROM openjdk:11.0.6-jdk
LABEL maintainer="jose.guerreror@inpe.gob.pe"
WORKDIR /workspace
COPY --from=builder /app/target/mitocode-calculator.jar app.jar
ENTRYPOINT exec java -jar /workspace/app.jar