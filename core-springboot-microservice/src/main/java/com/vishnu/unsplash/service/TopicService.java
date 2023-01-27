package com.vishnu.unsplash.service;

import com.vishnu.unsplash.model.ImageEntity;
import com.vishnu.unsplash.model.TopicEntity;
import com.vishnu.unsplash.model.UserEntity;
import com.vishnu.unsplash.repository.TopicRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TopicService {

    final TopicRepository topicRepository;
    final UserService userService;

    public TopicService(TopicRepository topicRepository, UserService userService){
        this.topicRepository = topicRepository;
        this.userService = userService;
    }

    public List<TopicEntity> getAllTopics(){
        return (List<TopicEntity>) topicRepository.findAll();
    }
    public TopicEntity getTopicById(long id){
        return (TopicEntity) topicRepository.findById(id).orElse(null);
    }

    public void saveTopic(TopicEntity topicEntity){
        topicRepository.save(topicEntity);
    }

    public List<ImageEntity> getAllImagesUnderTopic(long Id){
        TopicEntity topic = getTopicById(Id);
        if(topic!=null){
            return topic.getImages(); // getter setter method of topic entity
        }
        return null;
    }


    public void addImageToTopic(long topicId,long userId,ImageEntity imageEntity){
        UserEntity user = userService.getUserById(userId);
        imageEntity.setUser(user);


        TopicEntity topicEntity = this.getTopicById(topicId);

        if(topicEntity.getImages()==null){
            topicEntity.setImages(Collections.emptyList());
        }
        if(imageEntity.getTopics()==null){
            imageEntity.setTopics(Collections.emptyList());
        }
        topicEntity.getImages().add(imageEntity);
        imageEntity.getTopics().add(topicEntity);

        topicRepository.save(topicEntity);
    }
}
