package com.vishnu.unsplash.communication;

import com.vishnu.unsplash.model.CommentEntity;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class GetComments {
    LoadBalancerClient loadBalancerClient;

//    public List<CommentEntity> getCommentsOfImage(Long id){
//        ServiceInstance serviceInstance = loadBalancerClient.choose("comment-service");
//        String uri = serviceInstance.getUri()+"/"+id;
//        RestTemplate restTemplate = new RestTemplate();
//
//        List<CommentEntity> comments = restTemplate.getForObject(url, List.class);
//        return comments;
//
//    }

}
