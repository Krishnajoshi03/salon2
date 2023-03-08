package com.example.cruddemo.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.cruddemo.model.Users;

@Repository
public interface MongoTemplateRepository {

	List<Users> getAll();

}
