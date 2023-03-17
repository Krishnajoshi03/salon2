package com.example.cruddemo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.cruddemo.model.Owner;

@Repository
public interface OwnerRepository  extends MongoRepository<Owner, String>
{
	
}
