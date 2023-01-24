package com.vishnu.unsplash.repository;

import com.vishnu.unsplash.model.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserEntity,Long> {
    UserEntity findByName(String name);

    UserEntity findByEmail(String email);

    UserEntity findByEmailAndPassword(String email, String password);


}

//    CrudRepository<Entity,PrimaryKey>

//    The CrudRepository interface provides several methods for performing basic CRUD operations, such as:
//
//        save() to save a new entity or update an existing one
//        findOne() to find an entity by its primary key
//        findAll() to find all entities of the specified type
//        delete() to delete an entity by its primary key
//        count() to count the number of entities of the specified type