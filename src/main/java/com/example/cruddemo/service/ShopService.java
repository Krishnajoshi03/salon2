package com.example.cruddemo.service;
import java.util.*;

import org.springframework.stereotype.Service;

import com.example.cruddemo.model.Shop;
@Service
public interface ShopService {
	List<Shop> getAllShops();

}
