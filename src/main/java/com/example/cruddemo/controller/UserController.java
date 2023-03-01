package com.example.cruddemo.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.RestController;

import com.example.cruddemo.model.Users;
import com.example.cruddemo.service.UserService;


@RestController
@RequestMapping("/api")
public class UserController 
{
	@Autowired
	UserService userService; 
	
	public UserController(UserService userservice) {
		this.userService=userservice;
	}
	
	
	@PostMapping("/create")
	public ResponseEntity<Users> createUser(@RequestBody Users user)
	{
		return new ResponseEntity<Users>(userService.createUser(user),HttpStatus.CREATED );
	}
	
	@GetMapping("/users")
	public List<Users> getAllUsers()
	{
		return userService.getAllUsers();
	}
	@GetMapping("/ajit")
	public String name()
	{
		return "Ajit Bhaiya JAdhav";
	}
	
	@GetMapping("/user/{id}")
	public ResponseEntity<Users> getUserByid(@PathVariable("id") String userId)
	{
		return new ResponseEntity<Users>(userService.getUserById(userId), HttpStatus.OK);
	}
	@PutMapping("/user/update/{id}")
	 public ResponseEntity<Users> updateUser( @PathVariable String id,@RequestBody Users user)
	 {
		 return new ResponseEntity<Users>(userService.updateById(id.toString(), user),HttpStatus.OK);
		 
	 }
	@DeleteMapping("/user/delete/{id}")
	public ResponseEntity<Users> deleteById(@PathVariable String id)
	{
		return new ResponseEntity<Users>(userService.deleteById(id),HttpStatus.OK);
		
	}
	
	

}
