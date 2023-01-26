package com.vishnu.unsplash.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vishnu.unsplash.model.CommentEntity;
import com.vishnu.unsplash.model.ImageEntity;
import com.vishnu.unsplash.model.UserEntity;
import com.vishnu.unsplash.pojo.request.AddCommentPojo;
import com.vishnu.unsplash.pojo.request.AuthPojo;
import com.vishnu.unsplash.pojo.request.ImageUpload;
import com.vishnu.unsplash.pojo.response.UserImages;
import com.vishnu.unsplash.repository.UserRepository;
import com.vishnu.unsplash.service.CommentService;
import com.vishnu.unsplash.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Enumeration;
import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@RequestMapping("/core/users")
public class UserController {
    UserService userService;
    CommentService commentService;
    private final UserRepository userRepository;

    private ObjectMapper objectMapper;

    @GetMapping
    public ResponseEntity<?> getAllUsers(HttpServletRequest request) throws JsonProcessingException {
            AuthPojo user = objectMapper.readValue(request.getHeader("user"),AuthPojo.class);
            System.out.println(user.getUserId());
            return new ResponseEntity<>(userService.getAllUsers(),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable long id){
        return new ResponseEntity<>(userService.getUserById(id).getImages(),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody UserEntity userEntity){
        userService.saveUser(userEntity);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping()
    public ResponseEntity<?> update(@RequestBody UserEntity userEntity){
        userService.saveUser(userEntity);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/images")
    public ResponseEntity<?> getImages(HttpServletRequest httpServletRequest) throws JsonProcessingException {
        AuthPojo user = objectMapper.readValue(httpServletRequest.getHeader("user"),AuthPojo.class);
        UserEntity userEntity = userService.getUserById(user.getUserId());
        return new ResponseEntity<>(userEntity.getImages(),HttpStatus.OK);
    }

    @PostMapping("/addComment")
    public ResponseEntity<?> addComment(@RequestBody AddCommentPojo addCommentPojo, HttpServletRequest httpServletRequest) {
        System.out.println("inside cont");
        try{
           AuthPojo user = objectMapper.readValue(httpServletRequest.getHeader("user"),AuthPojo.class);
           commentService.addCommentByUser(addCommentPojo,user);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
