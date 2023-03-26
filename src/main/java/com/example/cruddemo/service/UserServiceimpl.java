package com.example.cruddemo.service;

import java.util.*;

import javax.swing.text.Document;

import org.apache.catalina.webresources.Cache;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bson.types.ObjectId;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import com.example.cruddemo.model.Users;

import com.example.cruddemo.repository.UserRepositoy;
import springfox.documentation.spring.web.json.Json;

@Service
public class UserServiceimpl implements UserService {

	//private static final Logger logger = LogManager.getLogger(UserServiceimpl.class);
	private static final Logger logger = LogManager.getLogger(UserServiceimpl.class);
	public UserServiceimpl() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Autowired
	MongoTemplate mongoTemplate;
	@Autowired
	UserRepositoy repositoy;

	@Override
	@CachePut(value = "user",key = "#users.u_mn")
	public Users createUser(Users users) {
		logger.trace("in creeate User ");
		Users user = repositoy.findById(users.getU_mn()).orElse(null);
		if (user != null) {
			logger.info("User Alredy Exist with Mobile Number" + users.getU_mn());
			return null;
		}
		logger.error("User Added Successfully With Mobile Num" + users.getU_mn());
		return repositoy.save(users);
	}

	@Override
	@Cacheable(value = "user")
	public List<Users> getAllUsers() {
//		System.out.println("in getall serivce");
//		UnwindOperation operation= Aggregation.unwind("u_fav");
//		MatchOperation aggregation2=Aggregation.match(Criteria.where("u_mn").exists(true));
//		Aggregation aggregation =Aggregation.newAggregation(operation,aggregation2);
		//logger.info("Get All Users(Service) ");
		return repositoy.findAll();// @formatter:off
	}

	@Override
	@Cacheable(value = "user",key = "#p0")
	public Users getUserById(String id) {
			System.out.println(id);
		logger.info("Get User By Id "+ id);
		Users user= repositoy.findById(id).orElse(null);
		System.out.println(user);
		return user;
	}
	@Override
	@CachePut(value = "user",key = "#u_mn")
	public Users updateById(String u_mn, Users users) {
		Users existingUser = repositoy.findById(u_mn).orElse(null);
		if (existingUser == null) {
			logger.error("User Does Not Exist With Id"+u_mn);
			return null;
		}
		existingUser.setU_fn(users.getU_fn());
		existingUser.setU_ln(users.getU_ln());
		existingUser.setU_addr(users.getU_addr());
		existingUser.setU_em(users.getU_em());
		existingUser.setU_fav(users.getU_fav());
		existingUser.setU_pic(users.getU_pic());
		logger.info("User Updated With Id "+u_mn);
		repositoy.save(existingUser);
		return existingUser;
	}

	@Override
	@CacheEvict(value = "user",key = "#id")
	public Users deleteById(String id) {
		Users deletedUser = repositoy.findById(id).orElse(null);
		System.out.println(deletedUser);
		if (deletedUser == null)
			{
			logger.error("User Does Not Exists with Id"+id);
			return null;
			}
		logger.info("User Deleted With ID"+id);
		repositoy.deleteById(id);
		return deletedUser;

	}

	@Override
	@CachePut(value = "user",key = "#p0")
	public String addToFavourites(String userId, String shopId) {
		System.out.println("in servimpl");
		org.springframework.data.mongodb.core.query.Query query = new 
				org.springframework.data.mongodb.core.query.Query().addCriteria(Criteria.where("u_mn").is(userId));
		logger.debug("below Criteria");
		Update update = new Update().push("u_fav", shopId);
		logger.debug("below Update");
		FindAndModifyOptions options = new FindAndModifyOptions().returnNew(true).upsert(true);
		
		logger.info("In Add to Favourites");
		String id1 = mongoTemplate.findAndModify(query, update, options, Users.class).getU_mn();
		logger.info("Added to Favourites for User with Id "+userId);
		return id1;

	}
	@Override
	@CachePut(value = "user",key = "#p1")
	public String removeFromFav(String shopId, String userId) {
		org.springframework.data.mongodb.core.query.Query query = new org.springframework.data.mongodb.core.query.Query()
				.addCriteria(Criteria.where("u_mn").is(userId));
		Update update = new Update().pull("u_fav", shopId);
		FindAndModifyOptions options = new FindAndModifyOptions().returnNew(true).upsert(true);
		logger.info("In Remove from Favourites");
		
		String id1 = mongoTemplate.findAndModify(query, update, options, Users.class).getU_mn();
		logger.info("Removed from  Favourites of User with Id "+userId);
		return id1;
	}

}
