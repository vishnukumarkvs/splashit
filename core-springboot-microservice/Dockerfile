FROM openjdk:19-alpine
WORKDIR workspace
ARG JAR_FILE=target/unsplash-0.0.1-SNAPSHOT*.jar
COPY ${JAR_FILE} app.jar

EXPOSE 8001

ENTRYPOINT java -Xms2048m -Xmx3072m -XX:+UseG1GC \
          	-DDB_URL=$DB_URL \
          	-DDB_USER=$DB_USER \
          	-DDB_PWD=$DB_PWD \
          	-DAWS_ACCESS_KEY=${AWS_ACCESS_KEY} \
          	-DAWS_SECRET_KEY=${AWS_SECRET_KEY} \
          	-DQUEUE_URI=${QUEUE_URI} \
          	-DEUREKA_SERVER_URL=${EUREKA_SERVER_URL} \
          	-jar app.jar