package com.vishnu.unsplash.controller;

import com.vishnu.unsplash.model.ImageEntity;
import com.vishnu.unsplash.model.UserEntity;
import com.vishnu.unsplash.pojo.request.UpdateCommentPojo;
import com.vishnu.unsplash.pojo.response.GetCommentsPojo;
import com.vishnu.unsplash.service.ImageService;
import com.vishnu.unsplash.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class CommentController {
    UserService userService;
    ImageService imageService;

    LoadBalancerClient loadBalancerClient;

    @GetMapping("/{imageId}/comments")
    public List<GetCommentsPojo> getCommentsForImage(@PathVariable Long imageId) {
        RestTemplate restTemplate = new RestTemplate();
        ServiceInstance instance = loadBalancerClient.choose("comment-service");
        String url = String.format("http://%s:%s/comments/%d", instance.getHost(), instance.getPort(), imageId);

        ResponseEntity<GetCommentsPojo[]> response = restTemplate.getForEntity(url, GetCommentsPojo[].class);
        return Arrays.asList(response.getBody());
    }

//    @PostMapping
//    public ResponseEntity<?> updateComment(@RequestBody UpdateCommentPojo updateCommentPojo){
//        UserEntity userEntity = userService.getUserById(updateCommentPojo.getUserId());
////        userEntity.setCommentId(updateCommentPojo.getCommentId());
////        userService.saveUser(userEntity);
//
//        ImageEntity imageEntity = imageService.getImageById(updateCommentPojo.getImageId());
////        imageEntity.setCommentId(updateCommentPojo.getCommentId());
//        List<Long> commentIds = imageService.getAllCommentIdsUnderImage(updateCommentPojo.getImageId());
//        if (commentIds != null) {
//            commentIds.add(updateCommentPojo.getCommentId());
//        } else {
//            commentIds = new ArrayList<>();
//            commentIds.add(updateCommentPojo.getCommentId());
//        }
////        imageService.saveImage(imageEntity,userEntity.getId());
//
//        return new ResponseEntity<>(HttpStatus.CREATED);
//    }


}
