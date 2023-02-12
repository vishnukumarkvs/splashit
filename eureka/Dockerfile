FROM openjdk:19-alpine
WORKDIR workspace
ARG JAR_FILE=target/eureka-0.0.1-SNAPSHOT*.jar
COPY ${JAR_FILE} app.jar

EXPOSE 8761

ENTRYPOINT java -Xms2048m -Xmx3072m -XX:+UseG1GC \
          	-jar app.jar