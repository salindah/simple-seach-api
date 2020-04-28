FROM openjdk:8-jdk-alpine

MAINTAINER Salinda Hettiarachchi

ARG JAR_FILE=target/simple-search-api-*.jar

COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java","-jar","/app.jar"]