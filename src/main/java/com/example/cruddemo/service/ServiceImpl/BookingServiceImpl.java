package com.example.cruddemo.service.ServiceImpl;

import java.time.LocalDateTime;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.data.mongodb.core.MongoTemplate;

import org.springframework.stereotype.Service;

import com.example.cruddemo.controller.BookingController;
import com.example.cruddemo.model.Booking;
import com.example.cruddemo.model.Shop;
import com.example.cruddemo.model.Users;
import com.example.cruddemo.model.supportClass.BookingStatus;
import com.example.cruddemo.repository.BookingRepository;
import com.example.cruddemo.service.BookingService;
import com.example.cruddemo.service.ShopService;
import com.example.cruddemo.service.UserService;

@Service
public class BookingServiceImpl implements BookingService {

	@Autowired
	private BookingRepository bookingRepo;
	@Autowired
	MongoTemplate mongotemplate;
	@Autowired
	UserService userService;
	
	@Autowired
	ShopService shopService;
	private static final Logger logger = LogManager.getLogger(BookingController.class);

	
	@CachePut(key = "booking",value = "#p0")
	@Override
	public Booking saveBooking( String bk_id,String sp_id,String u_id) {
			
		Shop shop = shopService.findShopByMobile(sp_id);
		Users user = userService.getUserById(u_id);
		
		Booking booking = new Booking(bk_id,shop,user);		
		booking.setBk_status(BookingStatus.BOOKED);
		booking.setBk_bk_time(LocalDateTime.now());
		Booking booked = bookingRepo.save(booking);
		userService.updateBooking(booked);
		shopService.updateBooking(booked);
		return booked;
	}
	
	@Override
	public Booking findBooking(String id) {
		Booking book = bookingRepo.findById(id).orElse(null);
		logger.info("In Find  Booking");
		return book;

	}

	@Override
	public List<Booking> getAllBookings() {
		
		return bookingRepo.findAll();

	}

}
