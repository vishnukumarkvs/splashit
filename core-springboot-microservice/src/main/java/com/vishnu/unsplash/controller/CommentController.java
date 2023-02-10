package com.vishnu.unsplash.controller;


import com.vishnu.unsplash.pojo.response.GetCommentsPojo;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import java.util.Arrays;
import java.util.List;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class CommentController {

    LoadBalancerClient loadBalancerClient;

    @GetMapping("/{imageId}/comments")
    public List<GetCommentsPojo> getCommentsForImage(@PathVariable Long imageId) {
        RestTemplate restTemplate = new RestTemplate();
        ServiceInstance instance = loadBalancerClient.choose("comment-service");
        String url = String.format("http://%s:%s/comments/%d", instance.getHost(), instance.getPort(), imageId);

        ResponseEntity<GetCommentsPojo[]> response = restTemplate.getForEntity(url, GetCommentsPojo[].class);
        return Arrays.asList(response.getBody());
    }
}
