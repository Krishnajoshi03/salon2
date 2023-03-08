package com.example.cruddemo.repository;


import com.example.cruddemo.model.Shop;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;



@Repository
public interface ShopRepository extends MongoRepository<Shop,String>
{
	
	Shop findBymobileNumber(String mobile_num);
	

}
