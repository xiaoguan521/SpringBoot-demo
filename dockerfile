FROM java:8
EXPOSE 8019
ARG JAR_FILE
ADD target/${JAR_FILE} /niceyoo.jar
ENTRYPOINT ["java", "-jar","/niceyoo.jar"]
