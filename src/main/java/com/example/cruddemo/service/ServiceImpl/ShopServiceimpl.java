package com.example.cruddemo.service.ServiceImpl;

import java.math.BigInteger;
import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.example.cruddemo.model.Booking;
import com.example.cruddemo.model.Shop;
import com.example.cruddemo.repository.ShopRepository;
import com.example.cruddemo.service.ShopService;

@Service
public class ShopServiceimpl implements ShopService{

	@Autowired
	private ShopRepository repository;
	
	@Autowired
	MongoTemplate mongoTemplate; 
	@Override
	@Cacheable(value = "shop")
	public List<Shop> getAllShops() {
		
		
		return repository.findAll();
		 
	}
	@Override
	@CachePut(value = "shop",key = "#shop.sp_id")
	public String createShop(Shop shop) {
		Shop existingShop=repository.findBymobileNumber(shop.getMobileNumber());
		if(existingShop!=null )
		{
			return "-1";
		}
		return repository.save(shop).getSp_id().toString();
		
	}
	@Override
	@Cacheable(value = "shop",key ="#mobile")
	public Shop findShopByMobile(String mobile) {
		
		Shop existingShop=repository.findBymobileNumber(mobile);
		if(existingShop==null )
		{
			return null;
		}
		return existingShop;
		
	}
	
	@Override
	@CachePut(value = "shop",key ="#booking.bk_sp_id")
	public String updateBooking(Booking  booking)
	{
		Query query = new Query().addCriteria(Criteria.where("sp_id").is(booking.getBk_sp_id()));
		
		Update update = new Update().push("sp_booking",booking.getBk_id() );
		
		FindAndModifyOptions options = new FindAndModifyOptions().returnNew(true).upsert(true);
		 String id= mongoTemplate.findAndModify(query, update,options, Shop.class).getSp_id();
		  System.out.println(id);
		  return id;
	}
	@Override
	@CachePut(value = "shop",key ="#id")
	public Shop findShopById(String id) {
		
		return repository.findById(id).orElse(null);
	}

}
