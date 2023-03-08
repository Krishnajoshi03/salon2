package com.example.cruddemo.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.CriteriaDefinition;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.example.cruddemo.model.Users;
@Repository
public class UserRepositoryimpl implements MongoTemplateRepository
{
	@Autowired
	private MongoTemplate mongoTemplate;
	@Override
	public List<Users> getAll() {
		Object value="0";
		Query query= new  Query();
		query.addCriteria(Criteria.where("u_nm").gt(value));
		return mongoTemplate.find(query,Users.class);
	}

}
