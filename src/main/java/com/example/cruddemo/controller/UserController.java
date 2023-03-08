package com.example.cruddemo.controller;

import java.util.*;

import javax.swing.text.Document;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.cruddemo.model.Users;
import com.example.cruddemo.service.UserServiceimpl;

@RestController
@RequestMapping("/api")
public class UserController {
	@Autowired
	UserServiceimpl userService;

	public UserController(UserServiceimpl userservice) {
		this.userService = userservice;
	}

	@PostMapping("/create")
	public ResponseEntity<Users> createUser(@RequestBody Users user) {
		Users user1 = userService.createUser(user);
		if (user1 == null) {
			return new ResponseEntity<Users>(HttpStatus.IM_USED);
		}
		return new ResponseEntity<Users>(user1, HttpStatus.CREATED);
	}

	@GetMapping("/users")
	public ResponseEntity<List<Users>> getAllUsers() {
		return new ResponseEntity<List<Users>>(userService.getAllUsers(),HttpStatus.OK);
	}

	@GetMapping("/user/{id}")
	public ResponseEntity<Users> getUserByid(@PathVariable("id") String userId) {
		Users user = userService.getUserById(userId);
		if (user == null) {
			return new ResponseEntity<Users>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Users>(user, HttpStatus.OK);
	}

	public UserController() {
		super();
		// TODO Auto-generated constructor stub
	}

	@PutMapping("/user/{id}")
	public ResponseEntity<Users> updateUser(@PathVariable String id, @RequestBody Users user) {
		return new ResponseEntity<Users>(userService.updateById(id.toString(), user), HttpStatus.OK);

	}

	@DeleteMapping("/user/{id}")
	public ResponseEntity<Users> deleteById(@PathVariable("id") String id) {
		Users user = userService.deleteById(id);
		if (user == null) {
			return new ResponseEntity<Users>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Users>(user, HttpStatus.OK);

	}
		
	@PutMapping("/user/fav/")
	public ResponseEntity<String> addToFavourites(@RequestParam("ShopId") String shopId,@RequestParam("UserId") String id) {
		String id1=userService.addToFavourites(id, shopId);
		
		if(id1=="-1")
		{
			return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
		}
		if(id1=="-2")
		{
			return new ResponseEntity<String>(HttpStatus.CONFLICT);
		}
		return new ResponseEntity<String>(id1, HttpStatus.OK);

	}
	@PutMapping("user/fav/remove")
	ResponseEntity<String> removeFromFav(@RequestParam("ShopId") String shopId,@RequestParam("UserId") String id)
	{
		return new ResponseEntity<String>(userService.removeFromFav(shopId,id),HttpStatus.OK);
	}

}
