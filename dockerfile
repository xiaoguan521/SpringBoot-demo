FROM openjdk:8-jdk-alpine
EXPOSE 8019
ARG JAR_FILE
COPY target/${JAR_FILE} xiaochen.jar
ENTRYPOINT ["java","-jar","/xiaochen.jar"]