package com.vishnu.unsplash.service;

import com.vishnu.unsplash.model.CommentEntity;
import com.vishnu.unsplash.model.ImageEntity;
import com.vishnu.unsplash.model.TopicEntity;
import com.vishnu.unsplash.model.UserEntity;
import com.vishnu.unsplash.repository.ImageRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@Service
@FieldDefaults(makeFinal = true,level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class ImageService {
    ImageRepository imageRepository;
    UserService userService;

    LoadBalancerClient loadBalancerClient;

    public ImageEntity getImageById(long id){
        return imageRepository.findById(id).orElse(null);
    }

    public void saveImage(ImageEntity image, Long userId) {
        UserEntity userEntity = userService.getUserById(userId);
        if(userEntity.getImages() == null){
            userEntity.setImages(Collections.emptyList());
        }
        userEntity.getImages().add(image);
        image.setUser(userEntity);
        imageRepository.save(image);
    }

//    // external join
//    public List<Long> getAllCommentIdsUnderImage(Long imageId){
//        ServiceInstance serviceInstance = loadBalancerClient.choose("comment-service");
//        String uri = serviceInstance.getUri()+"";
//        RestTemplate restTemplate = new RestTemplate();
//
//
//    }
    
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
                imageEntity.setLikes(imageEntity.getLikes() - 1);
            }
        }
    }

//    public List<CommentEntity> getAllComments(long id){
//        ImageEntity imageEntity = this.getImageById(id);
//        if(imageEntity != null){
//            return imageEntity.getComments();
//        }
//        return null;
//    }
}
