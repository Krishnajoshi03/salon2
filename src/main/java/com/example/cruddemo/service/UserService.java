package com.example.cruddemo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.cruddemo.model.Users;

public interface UserService {
	public Users createUser(Users user);//post
	public List<Users> getAllUsers();//get all
	public Users getUserById(String id);
	public Users updateById(String id,Users user);
	public Users deleteById(String id);
	

}
