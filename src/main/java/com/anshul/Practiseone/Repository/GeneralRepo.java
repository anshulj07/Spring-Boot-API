package com.anshul.Practiseone.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.anshul.Practiseone.Entity.General;

@Repository
public interface GeneralRepo extends MongoRepository<General, Integer>{

} 
