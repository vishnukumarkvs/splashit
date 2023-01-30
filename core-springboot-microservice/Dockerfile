FROM openjdk:19-alpine
WORKDIR workspace
ARG JAR_FILE=target/unsplash-0.0.1-SNAPSHOT*.jar
COPY ${JAR_FILE} app.jar

EXPOSE 8080

ENTRYPOINT java -Xms2048m -Xmx3072m -XX:+UseG1GC \
          	-DDB_URL=$DB_URL \
          	-DDB_USER=$DB_USER \
          	-DDB_PWD=$DB_PWD \
          	-jar app.jar