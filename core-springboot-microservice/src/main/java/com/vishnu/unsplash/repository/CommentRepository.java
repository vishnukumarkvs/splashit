package com.vishnu.unsplash.repository;

import com.vishnu.unsplash.model.CommentEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends CrudRepository<CommentEntity,Long> {
}
