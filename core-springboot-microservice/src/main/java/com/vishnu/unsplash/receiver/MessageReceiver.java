package com.vishnu.unsplash.receiver;


import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.QueueDoesNotExistException;
import com.amazonaws.services.sqs.model.ReceiveMessageResult;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vishnu.unsplash.model.ImageEntity;
import com.vishnu.unsplash.model.UserEntity;
import com.vishnu.unsplash.pojo.request.ImageUpload;
import com.vishnu.unsplash.repository.ImageRepository;
import com.vishnu.unsplash.service.UserService;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MessageReceiver {

    final AmazonSQS amazonSQSClient;


    final ImageRepository imageRepository;

    public MessageReceiver(AmazonSQS amazonSQSClient, ImageRepository imageRepository, UserService userService) {
        this.amazonSQSClient = amazonSQSClient;
        this.imageRepository = imageRepository;
        this.userService = userService;
    }

    final UserService userService;


    @Value("${cloud.aws.end-point.uri}")
    String url;

    @Scheduled(fixedDelay = 5000) // executes on every 5 second gap.
    public void receiveMessages() {
        String queueUrl = url;
        ReceiveMessageResult receiveMessageResult = amazonSQSClient.receiveMessage(queueUrl);
        try {
            log.info("Reading SQS Queue done: URL {}", queueUrl);
            if (!receiveMessageResult.getMessages().isEmpty()) {
                Message message = receiveMessageResult.getMessages().get(0);
                amazonSQSClient.deleteMessage(queueUrl, message.getReceiptHandle());
                log.info("Incoming Message From SQS {}", message.getMessageId());
                log.info("Message Body {}", message.getBody());
                ObjectMapper objectMapper = new ObjectMapper();
                ImageUpload imageUpload = objectMapper.readValue(message.getBody(), ImageUpload.class);
                System.out.println(imageUpload);
                UserEntity user1 = userService.getUserById(imageUpload.getUserId());
                ImageEntity imageEntity = ImageEntity.builder()
                        .url(imageUpload.getUrl())
                        .title(imageUpload.getTitle())
                        .description(imageUpload.getDescription())
                        .user(user1)
                        .build();
                imageRepository.save(imageEntity);
                processInvoice(message.getBody());

            }
        } catch (QueueDoesNotExistException e) {
            log.error("Queue does not exist {}", e.getMessage());
        } catch (JsonMappingException e) {
            log.error("Json processing exception",e);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }catch (Exception e){
            log.error("Something went wrong in SqsListener",e);
        }
    }

    private void processInvoice(String body) {
        log.info("Processing invoice generation and sending invoice emails from here..");
    }
}
