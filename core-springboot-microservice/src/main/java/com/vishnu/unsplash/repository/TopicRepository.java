package com.vishnu.unsplash.repository;

import com.vishnu.unsplash.model.TopicEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicRepository extends CrudRepository<TopicEntity, Long> {

}
