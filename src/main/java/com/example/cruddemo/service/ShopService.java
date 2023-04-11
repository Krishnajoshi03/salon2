package com.example.cruddemo.service;
import java.math.BigInteger;
import java.util.*;

import org.springframework.stereotype.Service;

import com.example.cruddemo.model.Booking;
import com.example.cruddemo.model.Shop;
@Service
public interface ShopService {
	List<Shop> getAllShops();
	public String createShop(Shop shop);
	public Shop findShopByMobile(String mobile);
	public String updateBooking(Booking booking);
	public Shop findShopById(String id);
}
