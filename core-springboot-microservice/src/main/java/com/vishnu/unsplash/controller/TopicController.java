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

import java.util.List;

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

    @GetMapping("/{id}")
    public ResponseEntity<?> getTopicById(@PathVariable long id){
        return new ResponseEntity<>(topicService.getTopicById((id)),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody TopicEntity topicEntity){
        topicService.saveTopic(topicEntity);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody TopicEntity topicEntity){
        topicService.saveTopic(topicEntity);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    // 5 - images from topic
    @GetMapping("/images")
    public ResponseEntity<?> getImagesFromTopic(@RequestBody Long topicId){
        List<ImageEntity> images = topicService.getAllImagesUnderTopic(topicId);
        return  new ResponseEntity<>(images,HttpStatus.OK);
    }
}
