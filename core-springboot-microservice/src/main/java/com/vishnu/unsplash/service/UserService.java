package com.vishnu.unsplash.service;

import com.vishnu.unsplash.model.ImageEntity;
import com.vishnu.unsplash.model.UserEntity;
import com.vishnu.unsplash.pojo.request.LoginPojo;
import com.vishnu.unsplash.repository.ImageRepository;
import com.vishnu.unsplash.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@FieldDefaults(makeFinal = true,level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class UserService {
    UserRepository userRepository;
    private final ImageRepository imageRepository;

    public List<UserEntity> getAllUsers(){
        return (List<UserEntity>) userRepository.findAll();
    }

    public UserEntity getUserById(long id){
        return userRepository.findById(id).orElse(null);
    }

    public UserEntity saveUser(UserEntity user){
        userRepository.save(user);
        return  userRepository.findById(user.getId()).orElse(null);
    }

    public UserEntity login(LoginPojo loginPojo){
        return this.userRepository.findByEmailAndPassword(loginPojo.getEmail(),loginPojo.getPassword());
    }

    public UserEntity getUserByEmail(String email){
        return  userRepository.findByEmail(email);
    }

    public UserEntity addImage(long userId, ImageEntity imageEntity){
        UserEntity user = getUserById(userId);
        imageEntity.setUser(user);
        if(user!=null){
            user.getImages().add(imageEntity);
            saveUser(user);
        }
        return user;
    }
}
