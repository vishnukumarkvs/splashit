package com.vishnu.unsplash.controller;

import com.vishnu.unsplash.model.ImageEntity;
import com.vishnu.unsplash.model.UserEntity;
import com.vishnu.unsplash.pojo.request.ImageUpload;
import com.vishnu.unsplash.repository.ImageRepository;
import com.vishnu.unsplash.service.ImageService;
import com.vishnu.unsplash.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@RequestMapping("/images")
public class ImageController {
    ImageService imageService;
    UserService userService;
    private final ImageRepository imageRepository;

    @PostMapping
    public ResponseEntity<?> addImage(@RequestBody ImageUpload imageUpload){
        UserEntity user1 = userService.getUserById(imageUpload.getUserId());
        ImageEntity imageEntity = ImageEntity.builder()
                .url(imageUpload.getUrl())
                .title(imageUpload.getTitle())
                .description(imageUpload.getDescription())
                .user(user1)
                .build();
        imageRepository.save(imageEntity);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
