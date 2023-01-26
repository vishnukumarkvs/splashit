package com.vishnu.unsplash.service;

import com.vishnu.unsplash.model.ImageEntity;
import com.vishnu.unsplash.model.UserEntity;
import com.vishnu.unsplash.repository.ImageRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@FieldDefaults(makeFinal = true,level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class ImageService {
    ImageRepository imageRepository;

    public ImageEntity getImageById(long id){
        return imageRepository.findById(id).orElse(null);
    }

    public void saveImage(ImageEntity image) {
        imageRepository.save(image);
    }
    
    public void addLike(long id) {
        ImageEntity imageEntity = imageRepository.findById(id).orElse(null);
        if (imageEntity != null) {
            imageEntity.setLikes(imageEntity.getLikes() + 1);
        }

    }
    public void removeLike(long id) {
        ImageEntity imageEntity = imageRepository.findById(id).orElse(null);
        if (imageEntity != null) {
            long likes = imageEntity.getLikes();
            if (likes > 0) {
                imageEntity.setLikes(imageEntity.getLikes()-1);
            }
            
        }
        
    }
}
