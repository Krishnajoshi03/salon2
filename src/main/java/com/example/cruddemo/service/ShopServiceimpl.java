package com.example.cruddemo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.example.cruddemo.model.Shop;
import com.example.cruddemo.repository.ShopRepository;

@Service
public class ShopServiceimpl implements ShopService{

	@Autowired
	private ShopRepository repository;
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

}
