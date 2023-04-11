package com.example.cruddemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.cruddemo.model.Shop;
import com.example.cruddemo.service.ServiceImpl.ShopServiceimpl;

import java.math.BigInteger;
import java.util.*;

@RestController
@RequestMapping("/api/")
public class ShopController {
	@Autowired
	ShopServiceimpl serviceimpl;

	@GetMapping("/shops")
	ResponseEntity<List<Shop>> getAllShops() {
		List<Shop> shops = serviceimpl.getAllShops();
		if (shops == null || shops.isEmpty()) {
			return new ResponseEntity<List<Shop>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Shop>>(shops, HttpStatus.OK);
	}

	@PostMapping("shop")
	ResponseEntity<String> createShop(@RequestBody Shop shop) {
		//shop.setSp_id(BigInteger.valueOf(100));
		String id = serviceimpl.createShop(shop);
		if (id == "-1") {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		return new ResponseEntity<String>(id, HttpStatus.CREATED);
	}

	@GetMapping("shop/{mobileNumber}")
	ResponseEntity<Shop> findShopByMobile(@PathVariable("mobileNumber") String mobile) {
		Shop shop = serviceimpl.findShopByMobile(mobile);
		if (shop == null) {
			return new ResponseEntity<Shop>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Shop>(shop, HttpStatus.OK);
	}

}
