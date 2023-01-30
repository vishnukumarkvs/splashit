package com.unsplash.upload.controller;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.unsplash.upload.pojo.request.ImageUploadPojo;
import com.unsplash.upload.pojo.request.SqsImageUploadPojo;
import com.unsplash.upload.pojo.response.ErrorPojo;
import com.unsplash.upload.service.SqsMessageProducer;
import com.unsplash.upload.utils.ImageValidator;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/upload")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class ImageUploadController {

    SqsMessageProducer sqsMessageProducer;

    @PostMapping
    public ResponseEntity<?> handleFileUpload(@ModelAttribute ImageUploadPojo imageUploadPojo) {
        if(!ImageValidator.areImages(imageUploadPojo.getFiles())){
            return new ResponseEntity<>(new ErrorPojo("Not Valid File. Upload only Images"), HttpStatus.BAD_REQUEST);
        }
        String bucketName = "unspash-images-vishnu";
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
                SqsImageUploadPojo sqsImageUploadPojo=SqsImageUploadPojo.builder()
                        .url(objectUrl)
                        .title(imageUploadPojo.getTitle())
                        .description(imageUploadPojo.getDescription())
                        .meta(imageUploadPojo.getMeta())
                        .build();
                sqsMessageProducer.send(sqsImageUploadPojo,headers);
            } catch (AmazonServiceException e) {
                return new ResponseEntity<>(new ErrorPojo("File upload failed. Please try again."),HttpStatus.BAD_REQUEST);
            } catch (IOException e) {
                return new ResponseEntity<>(new ErrorPojo("File upload failed. Please try again."), HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity<>(HttpStatus.OK);

    }
}
