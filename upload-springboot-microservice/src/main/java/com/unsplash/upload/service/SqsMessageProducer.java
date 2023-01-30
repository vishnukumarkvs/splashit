package com.unsplash.upload.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SqsMessageProducer {

    final QueueMessagingTemplate queueMessagingTemplate;

    public SqsMessageProducer(QueueMessagingTemplate queueMessagingTemplate) {
        this.queueMessagingTemplate = queueMessagingTemplate;
    }

    @Value("${aws.queue.name}")
    String imageQ;


    public <T> void send(T message, Map<String, Object> headers) {
        if (message == null) {
            return;
        }

        queueMessagingTemplate.convertAndSend(imageQ, message, headers);
    }

}