package com.anshul.Practiseone.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.anshul.Practiseone.Entity.User;
import org.bson.types.ObjectId;


public interface UserRepo extends MongoRepository<User, ObjectId>{
    User findByUserName(String userName);
}
