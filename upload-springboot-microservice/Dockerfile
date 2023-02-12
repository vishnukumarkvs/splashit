FROM openjdk:19-alpine
WORKDIR workspace
ARG JAR_FILE=target/upload-service-0.0.1-SNAPSHOT*.jar
COPY ${JAR_FILE} app.jar

EXPOSE 8002

ENTRYPOINT java -Xms2048m -Xmx3072m -XX:+UseG1GC \
          	-DAWS_ACCESS_KEY=${AWS_ACCESS_KEY} \
          	-DAWS_SECRET_KEY=${AWS_SECRET_KEY} \
          	-DAWS_REGION=${AWS_REGION} \
          	-DSQS_QUEUE_NAME=${SQS_QUEUE_NAME} \
          	-DS3_BUCKET_NAME=${S3_BUCKET_NAME} \
          	-jar app.jar