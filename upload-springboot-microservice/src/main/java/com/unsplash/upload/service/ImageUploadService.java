package com.unsplash.upload.service;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.unsplash.upload.pojo.request.ImageUploadPojo;
import com.unsplash.upload.pojo.request.SqsImageUploadPojo;
import com.unsplash.upload.pojo.response.AuthPojo;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true,level = AccessLevel.PRIVATE)
public class ImageUploadService {

    SqsMessageProducer sqsMessageProducer;

    public void fileUploadAndSendMessage(@ModelAttribute ImageUploadPojo imageUploadPojo, HttpServletRequest request, String bucketName) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        AmazonS3 s3client = AmazonS3ClientBuilder.standard()
                .withRegion(Regions.US_EAST_1).build();
        for(MultipartFile file: imageUploadPojo.getFiles()){
            String fileName = file.getOriginalFilename();
            String keyName = "images/" + fileName;

            try {
                s3client.putObject(new PutObjectRequest(bucketName, keyName, file.getInputStream(), new ObjectMetadata()));
                String objectUrl = s3client.getUrl(bucketName, keyName).toString();
                imageUploadPojo.setUrl(objectUrl);
                Map<String,Object> headers = new HashMap<>();
                headers.put("Content-Type","application/json");
                AuthPojo user = objectMapper.readValue(request.getHeader("user"), AuthPojo.class);
                SqsImageUploadPojo sqsImageUploadPojo=SqsImageUploadPojo.builder()
                        .url(objectUrl)
                        .title(imageUploadPojo.getTitle())
                        .description(imageUploadPojo.getDescription())
                        .meta(imageUploadPojo.getMeta())
                        .userId(user.getUserId())
                        .build();

                sqsMessageProducer.send(sqsImageUploadPojo,headers);
            }catch (Exception e){
                throw new Exception(e.getMessage());
            }
        }
    }
}
