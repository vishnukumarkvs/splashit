package com.unsplash.upload.controller;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.MultipartUpload;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.unsplash.upload.Pojo.Response.ErrorPojo;
import com.unsplash.upload.utils.ImageValidator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/upload")
public class ImageUploadController {

    @PostMapping
    public ResponseEntity<?> handleFileUpload(@RequestParam("files") List<MultipartFile> files){
        if(!ImageValidator.areImages(files)){
            return new ResponseEntity<>(new ErrorPojo("Not Valid File. Upload only Images"), HttpStatus.BAD_REQUEST);
        }
        String bucketName = "unspash-images-vishnu";
        AmazonS3 s3client = AmazonS3ClientBuilder.standard()
                .withRegion(Regions.US_EAST_1).build();
        for(MultipartFile file: files){
            String fileName = file.getOriginalFilename();
            String keyName = "images/" + fileName;

            try {
                s3client.putObject(new PutObjectRequest(bucketName, keyName, file.getInputStream(), new ObjectMetadata()));
            } catch (AmazonServiceException e) {
                return new ResponseEntity<>(new ErrorPojo("File upload failed. Please try again."),HttpStatus.BAD_REQUEST);
            } catch (IOException e) {
                return new ResponseEntity<>(new ErrorPojo("File upload failed. Please try again."), HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity<>(HttpStatus.OK);

    }
}
