package com.vishnu.unsplash.configuration;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AwsConfiguration {
    @Value("${aws.access_key_id}")
    private String accessKeyId;
    @Value("${aws.secret_access_key}")
    private String secretAccessKey;
    @Value("${aws.region}")
    private String region;

    @Bean
    public AmazonSQS amazonSQS() {
        return AmazonSQSClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(
                        new BasicAWSCredentials(accessKeyId, secretAccessKey)))
                .withRegion(Regions.fromName(region))
                .build();
    }
}
