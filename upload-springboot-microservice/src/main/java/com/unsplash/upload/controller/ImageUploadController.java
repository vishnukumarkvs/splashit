package com.unsplash.upload.controller;

import com.unsplash.upload.pojo.request.ImageUploadPojo;
import com.unsplash.upload.pojo.response.ErrorPojo;
import com.unsplash.upload.service.ImageUploadService;
import com.unsplash.upload.utils.ImageValidator;
import jakarta.servlet.http.HttpServletRequest;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/image")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class ImageUploadController {

  ImageUploadService imageUploadService;

    @PostMapping
    public ResponseEntity<?> handleFileUploadAndSendMessage(@ModelAttribute ImageUploadPojo imageUploadPojo, HttpServletRequest request, @Value("${aws.s3.bucket-name}") String bucketName) {
        try {
            if(!ImageValidator.areImages(imageUploadPojo.getFiles())){
                return new ResponseEntity<>(new ErrorPojo("Not Valid File. Upload only Images"), HttpStatus.BAD_REQUEST);
            }
            imageUploadService.fileUploadAndSendMessage(imageUploadPojo,request,bucketName);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorPojo(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}