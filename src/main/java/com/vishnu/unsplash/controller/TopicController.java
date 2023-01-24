package com.vishnu.unsplash.controller;

import com.vishnu.unsplash.model.ImageEntity;
import com.vishnu.unsplash.model.TopicEntity;
import com.vishnu.unsplash.service.ImageService;
import com.vishnu.unsplash.service.TopicService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@RequestMapping("/topics")
public class TopicController {
    TopicService topicService;
    ImageService imageService;

    @GetMapping
    public ResponseEntity<?> getAllTopics(){
        return new ResponseEntity<>(topicService.getAllTopics(), HttpStatus.OK);
    }

    @PostMapping("/saveImage/{id}/{userId}")
    public ResponseEntity<?> saveImageToTopic(@PathVariable long id, @PathVariable long userId, @RequestBody ImageEntity imageEntity){

        topicService.addImageToTopic(id,userId, imageEntity);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping
    public ResponseEntity<?> createTopic(@RequestBody TopicEntity topicEntity){
        topicService.saveTopic(topicEntity);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
