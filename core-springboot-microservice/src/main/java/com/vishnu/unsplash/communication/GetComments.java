package com.vishnu.unsplash.communication;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class GetComments {
    LoadBalancerClient loadBalancerClient;

    public String getCommentsOfImage(){
        ServiceInstance serviceInstance = loadBalancerClient.choose("comment-service");
        String uri = serviceInstance.getUri()+"";
        RestTemplate restTemplate = new RestTemplate();

        String response = restTemplate.getForObject(uri,String.class);

        return response;

    }

}
