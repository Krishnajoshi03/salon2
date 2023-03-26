package com.example.cruddemo.controller;

import java.util.*;

import org.slf4j.LoggerFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.cruddemo.model.Users;
import com.example.cruddemo.service.UserServiceimpl;

@RestController
@RequestMapping("/api")
public class UserController {

	private static final Logger logger = LogManager.getLogger(UserController.class);

	@Autowired
	UserServiceimpl userService;

	public UserController(UserServiceimpl userservice) {
		this.userService = userservice;
	}
	
		@PostMapping("/user")
	public ResponseEntity<Users> createUser(@RequestBody Users user) {
		Users user1 = userService.createUser(user);
		if (user1 == null) {
			logger.error("User Already Exist With Given Id" + user.getU_mn());
			return new ResponseEntity<Users>(HttpStatus.IM_USED);
		}
		logger.info("User Created With Id " + user.getU_mn());
		return new ResponseEntity<Users>(user1, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public List<Users> getAll() {
		logger.info("In Get All Users");
		return userService.getAllUsers();
	}

	@GetMapping("/user/{id}")
	public ResponseEntity<Users> getUserByid(@PathVariable("id") String userId) {
		System.out.println(userId);
		logger.info("in get id");
		Users user = userService.getUserById(userId);
		if (user == null) {
			logger.error("No User With Id " + userId);
			return new ResponseEntity<Users>(HttpStatus.NO_CONTENT);
		}
		logger.info("Fetched User With Id " + userId);
		return new ResponseEntity<Users>(user, HttpStatus.OK);
	}

	public UserController() {
		super();
		// TODO Auto-generated constructor stub
	}

	@PutMapping("/user/{id}")
	public ResponseEntity<Users> updateUser(@PathVariable String id, @RequestBody Users user) {
		Users user1 = userService.updateById(id.toString(), user);
		logger.info("In Update User ");
		return new ResponseEntity<Users>(user1, HttpStatus.OK);

	}

	@DeleteMapping("/user/{id}")
	public ResponseEntity<Users> deleteById(@PathVariable("id") String id) {
		Users user = userService.deleteById(id);
		if (user == null) {
			logger.error("User Does Not Exist With Id " + id);
			return new ResponseEntity<Users>(HttpStatus.NO_CONTENT);
		}
		logger.info("User Deleted With Id " + id);
		return new ResponseEntity<Users>(user, HttpStatus.OK);

	}

	@PutMapping("/user/fav/")
	public ResponseEntity<String> addToFavourites(@RequestParam("ShopId") String shopId,
			@RequestParam("UserId") String id) {
		String id1 = userService.addToFavourites(id, shopId);
		logger.info("In Add To Favourites");
		return new ResponseEntity<String>(id1, HttpStatus.OK);

	}

	@PutMapping("user/fav/remove")
	ResponseEntity<String> removeFromFav(@RequestParam("ShopId") String shopId, @RequestParam("UserId") String id) {
		String id1 = userService.removeFromFav(shopId, id);
		logger.info("In Remove From Favourites");
		return new ResponseEntity<String>(id1, HttpStatus.OK);
	}

}
