package com.unsplash.commentservice.repository;

import com.unsplash.commentservice.controller.CommentController;
import com.unsplash.commentservice.model.CommentEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends CrudRepository<CommentEntity,Long> {
    List<CommentEntity> findAllByImageId(Long imageId);
}
