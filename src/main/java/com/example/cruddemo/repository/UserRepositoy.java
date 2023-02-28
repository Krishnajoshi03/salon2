package com.example.cruddemo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.cruddemo.model.Users;

@Repository
public interface UserRepositoy extends MongoRepository<Users, String>{
	
	

}
