package com.example.cruddemo.service;

import java.util.*;

import javax.swing.text.Document;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ExecutableFindOperation;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.Mapping;

import com.example.cruddemo.model.Shop;
import com.example.cruddemo.model.Users;

import com.example.cruddemo.repository.UserRepositoy;
import com.fasterxml.jackson.core.JsonParser;
import com.mongodb.internal.operation.FindOperation;

import springfox.documentation.spring.web.json.Json;


@Service
public class UserServiceimpl implements UserService {

	public UserServiceimpl() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	@Autowired
	MongoTemplate mongoTemplate;
	@Autowired
	UserRepositoy repositoy;


	

	@Override
	public Users createUser(Users users) {
		Users user = repositoy.findById(users.getU_mn()).orElse(null);
		if (user != null) {
			return null;
		}
		return repositoy.save(users);
	}

	@Override
	public List<Users> getAllUsers() {
//		System.out.println("in getall serivce");
//		UnwindOperation operation= Aggregation.unwind("u_fav");
//		MatchOperation aggregation2=Aggregation.match(Criteria.where("u_mn").exists(true));
//		Aggregation aggregation =Aggregation.newAggregation(operation,aggregation2);

		return  repositoy.findAll();// @formatter:off
	}

	@Override
	public Users getUserById(String mn) {
		return repositoy.findById(mn).orElse(null);
	}

	@Override
	public Users updateById(String mn, Users user) {
		Users existingUser = repositoy.findById(mn).orElse(null);
		if (existingUser == null) {
			return null;
		}
		existingUser.setU_fn(user.getU_fn());
		existingUser.setU_ln(user.getU_ln());
		existingUser.setU_mn(user.getU_mn());
		existingUser.setU_mn(user.getU_mn());
		
		repositoy.save(existingUser);
		return existingUser;
	}

	@Override
	public Users deleteById(String id) {
		Users deletedUser = repositoy.findById(id).orElse(null);
		if (deletedUser == null)
			return null;
		repositoy.deleteById(id);
		return deletedUser;

	}

	@Override
	public String addToFavourites(String userId, String shopId) {

		org.springframework.data.mongodb.core.query.Query query = new org.springframework.data.mongodb.core.query.Query()
				.addCriteria(Criteria.where("u_mn").is(userId));
		Update update = new Update().push("u_fav", shopId);
		FindAndModifyOptions options = new FindAndModifyOptions().returnNew(true).upsert(true);
		Optional<Users> id = repositoy.findById(userId);
		System.out.println(id);
		if (id.isEmpty() || id == null) {
			return "-1";
		}
		Query query1 = new Query(Criteria.where("u_fav.$id").is(new ObjectId(shopId)));
//		List<Users> isFavExist = mongoTemplate.find(query1, Users.class);
//		if (isFavExist != null || !isFavExist.isEmpty()) {
//			return "-2";
//		}
		String id1 = mongoTemplate.findAndModify(query, update, options, Users.class).getU_mn();
		return id1;

	}
	@Override
	public String removeFromFav(String shopId, String userId) {
		org.springframework.data.mongodb.core.query.Query query = new org.springframework.data.mongodb.core.query.Query()
				.addCriteria(Criteria.where("u_mn").is(userId));
		Update update = new Update().pull("u_fav", new ObjectId(shopId));
		FindAndModifyOptions options = new FindAndModifyOptions().returnNew(true).upsert(true);
		
//		Query query1 = new Query(Criteria.where("u_fav.$id").is(shopId));
//		List isFavExist = mongoTemplate.find(query1, Users.class);
//		if (isFavExist == null || isFavExist.isEmpty()) {
//			return "-2";
//		}
		String id1 = mongoTemplate.findAndModify(query, update, options, Users.class).getU_mn();
		return id1;
	}

}
