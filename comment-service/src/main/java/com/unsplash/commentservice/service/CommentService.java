package com.unsplash.commentservice.service;

import com.unsplash.commentservice.model.CommentEntity;
import com.unsplash.commentservice.repository.CommentRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true,level = AccessLevel.PRIVATE)
public class CommentService {
    CommentRepository commentRepository;

    public List<CommentEntity> getCommentsForImage(Long imageId){
        return commentRepository.findAllByImageId(imageId);
    }
}
