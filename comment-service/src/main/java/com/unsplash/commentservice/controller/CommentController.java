package com.unsplash.commentservice.controller;

import com.unsplash.commentservice.model.CommentEntity;
import com.unsplash.commentservice.pojo.request.AddCommentPojo;
import com.unsplash.commentservice.pojo.request.UpdateCommentPojo;
import com.unsplash.commentservice.repository.CommentRepository;
import com.unsplash.commentservice.service.CommentService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@RequestMapping("/comments")
public class CommentController {

    CommentRepository commentRepository;
    CommentService commentService;

    LoadBalancerClient loadBalancerClient;

    @PostMapping
    public ResponseEntity<?> addComment(@RequestBody AddCommentPojo addCommentPojo){
        CommentEntity commentEntity =  CommentEntity.builder()
                .description(addCommentPojo.getDescription())
                .userId(addCommentPojo.getUserId())
                .imageId(addCommentPojo.getImageId())
                .build();

        commentRepository.save(commentEntity);
//        updateComment(new UpdateCommentPojo(addCommentPojo.getUserId(), addCommentPojo.getImageId(), commentEntity.getId()));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

//    public void updateComment(UpdateCommentPojo updateCommentPojo) {
//        RestTemplate restTemplate = new RestTemplate();
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//
//        HttpEntity<UpdateCommentPojo> request = new HttpEntity<>(updateCommentPojo, headers);
//
//        ServiceInstance serviceInstance = loadBalancerClient.choose("core-service");
//        String baseUrl = serviceInstance.getUri().toString();
//
//        restTemplate.exchange(baseUrl + "/comment", HttpMethod.POST, request, Void.class);
//    }

    @GetMapping("/{imageId}")
    public List<CommentEntity> getAllImageComments(@PathVariable Long imageId){
        return commentService.getCommentsForImage(imageId);
    }
//    @GetMapping
//    public String passHello(){
//        return "Hello corey";
//    }
}
