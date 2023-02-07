package com.vishnu.unsplash.controller;

import com.vishnu.unsplash.communication.GetComments;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@RequestMapping("/temp")
public class TempController {
    GetComments getComments;

    @GetMapping
    public ResponseEntity<?> getInfo(){
        String s = getComments.getCommentsOfImage();
        return new ResponseEntity<>(s, HttpStatus.OK);
    }
}
