package com.vishnu.unsplash.controller;

import com.vishnu.unsplash.model.UserEntity;
import com.vishnu.unsplash.pojo.request.LoginPojo;
import com.vishnu.unsplash.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    UserService userService;
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserEntity userEntity){
        UserEntity user = userService.saveUser(userEntity);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginPojo loginPojo){
        UserEntity user = userService.login(loginPojo);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
