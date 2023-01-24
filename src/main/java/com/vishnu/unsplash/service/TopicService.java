package com.vishnu.unsplash.service;

import com.vishnu.unsplash.model.ImageEntity;
import com.vishnu.unsplash.model.TopicEntity;
import com.vishnu.unsplash.repository.TopicRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class TopicService {
    TopicRepository topicRepository;

    public List<ImageEntity> getAllImagesUnderTopic(){
        return (List<ImageEntity>) topicRepository.findAll();
    }
    public TopicEntity getTopicById(long id){
        return (TopicEntity) topicRepository.findById(id).orElse(null);
    }

    public void addImageToTopic(long topicId,ImageEntity imageEntity){
        TopicEntity topic = this.getTopicById(topicId);
        List<TopicEntity> topicsOfImage = new ArrayList<TopicEntity>();
        topicsOfImage.add(topic);
        imageEntity.setTopics(topicsOfImage);
    }
}
