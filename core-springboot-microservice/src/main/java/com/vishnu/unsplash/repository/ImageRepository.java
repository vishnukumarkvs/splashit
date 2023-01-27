package com.vishnu.unsplash.repository;

import com.vishnu.unsplash.model.ImageEntity;
import com.vishnu.unsplash.service.ImageService;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends CrudRepository<ImageEntity,Long> {
}
