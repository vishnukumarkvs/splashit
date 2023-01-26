package com.vishnu.unsplash.service;

import com.vishnu.unsplash.model.CommentEntity;
import com.vishnu.unsplash.model.ImageEntity;
import com.vishnu.unsplash.model.UserEntity;
import com.vishnu.unsplash.pojo.request.AddCommentPojo;
import com.vishnu.unsplash.pojo.request.AuthPojo;
import com.vishnu.unsplash.repository.CommentRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true,level = AccessLevel.PRIVATE)
public class CommentService {
    CommentRepository commentRepository;

    UserService userService;
    ImageService imageService;

    // add a comment by user on image
    public CommentEntity addCommentByUser(AddCommentPojo addCommentPojo, AuthPojo authPojo){
        System.out.println("inside service");
        UserEntity user = userService.getUserById(authPojo.getUserId());
        ImageEntity imageEntity = imageService.getImageById(addCommentPojo.getImageId());
        System.out.println(user.getId()+" "+imageEntity.getId());
        CommentEntity commentEntity = CommentEntity.builder()
                .description(addCommentPojo.getDescription())
                .image(imageEntity)
                .user(user)
                .likes(0L)
                .build();
        user.getComments().add(commentEntity);
        imageEntity.getComments().add(commentEntity);

        commentRepository.save(commentEntity);
        return commentEntity;
    }
}
