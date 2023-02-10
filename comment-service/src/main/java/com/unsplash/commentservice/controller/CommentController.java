package com.unsplash.commentservice.controller;

import com.unsplash.commentservice.model.CommentEntity;
import com.unsplash.commentservice.pojo.request.AddCommentPojo;
import com.unsplash.commentservice.repository.CommentRepository;
import com.unsplash.commentservice.service.CommentService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@RequestMapping("/comments")
public class CommentController {

    CommentRepository commentRepository;
    CommentService commentService;

    @PostMapping
    public ResponseEntity<?> addComment(@RequestBody AddCommentPojo addCommentPojo){
        CommentEntity commentEntity =  CommentEntity.builder()
                .description(addCommentPojo.getDescription())
                .userId(addCommentPojo.getUserId())
                .imageId(addCommentPojo.getImageId())
                .build();

        commentRepository.save(commentEntity);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{imageId}")
    public List<CommentEntity> getAllImageComments(@PathVariable Long imageId){
        return commentService.getCommentsForImage(imageId);
    }

}
