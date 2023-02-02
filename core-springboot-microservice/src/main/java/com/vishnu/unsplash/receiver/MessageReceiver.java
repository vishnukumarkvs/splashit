package com.vishnu.unsplash.receiver;


import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.QueueDoesNotExistException;
import com.amazonaws.services.sqs.model.ReceiveMessageResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MessageReceiver {

//    String receiveMessage = sqs.receiveMessage("https://sqs.us-west-2.amazonaws.com/895656015678/qq").getMessages().get(0).getBody();
//    log.info(receiveMessage);
//    @QueueListener("https://sqs.us-west-2.amazonaws.com/895656015678/qq")

    private final AmazonSQS amazonSQSClient;

    @Scheduled(fixedDelay = 3000) // executes on every 5 second gap.
    public void receiveMessages() {
        try {
            String queueUrl = "https://sqs.us-west-2.amazonaws.com/895656015678/qq";
            log.info("Reading SQS Queue done: URL {}", queueUrl);
            ReceiveMessageResult receiveMessageResult = amazonSQSClient.receiveMessage(queueUrl);
            if (!receiveMessageResult.getMessages().isEmpty()) {
                Message message = receiveMessageResult.getMessages().get(0);
                log.info("Incoming Message From SQS {}", message.getMessageId());
                log.info("Message Body {}", message.getBody());
                processInvoice(message.getBody());
                amazonSQSClient.deleteMessage(queueUrl, message.getReceiptHandle());
            }
        } catch (QueueDoesNotExistException e) {
            log.error("Queue does not exist {}", e.getMessage());
        }
    }

    private void processInvoice(String body) {
        log.info("Processing invoice generation and sending invoice emails from here..");
    }

}
