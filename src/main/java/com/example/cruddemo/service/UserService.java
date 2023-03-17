package com.example.cruddemo.service;

import java.util.List;

import javax.swing.text.Document;

import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.cruddemo.model.Users;

public interface UserService {
	public Users createUser(Users user);//post
	public Iterable<Users> getAllUsers();//get all
	public Users getUserById(String id);
	public Users updateById(String id,Users user);
	public Users deleteById(String id);
	String addToFavourites(String userId, String shopId);
	public String removeFromFav(String shopId, String id);
	

}
