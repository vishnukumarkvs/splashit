package com.vishnu.unsplash.controller;

import com.vishnu.unsplash.model.ImageEntity;
import com.vishnu.unsplash.model.UserEntity;
import com.vishnu.unsplash.pojo.request.ImageUpload;
import com.vishnu.unsplash.pojo.response.UserImages;
import com.vishnu.unsplash.service.UserService;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@RequestMapping("/users")
public class UserController {
    UserService userService;

    @GetMapping
    public ResponseEntity<?> getUsers(){
        return  new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @PostMapping
    public  ResponseEntity<?> saveUser(@RequestBody UserEntity userEntity){
        userService.saveUser(userEntity);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/addImage")
    public  ResponseEntity<?> addImage(@RequestBody ImageUpload imageUpload){
        ImageEntity imageEntity = ImageEntity.builder()
                .url(imageUpload.getUrl())
                .title(imageUpload.getTitle())
                .description(imageUpload.getDescription())
                .build();
        UserEntity user=userService.addImage(imageUpload.getUserId(),imageEntity);
        UserImages userImages = new UserImages(user.getName(),user.getImages());
        return new ResponseEntity<>(userImages,HttpStatus.CREATED);
    }
}
