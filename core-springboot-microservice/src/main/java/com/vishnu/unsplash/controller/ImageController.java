package com.vishnu.unsplash.controller;

import com.vishnu.unsplash.service.ImageService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@RequestMapping("/images")
public class ImageController {
    ImageService imageService;

    // 4 get comments from image
    @GetMapping("/comments")
    public ResponseEntity<?> getComments(@RequestBody Long id){
        return new ResponseEntity<>(imageService.getAllComments(id), HttpStatus.OK);
    }
}
