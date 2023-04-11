package com.example.cruddemo.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.config.RepositoryNameSpaceHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.cruddemo.model.Booking;
import com.example.cruddemo.service.BookingService;

import io.swagger.annotations.Info;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/api")
public class BookingController {
	
	
	 private static final Logger logger = LogManager.getLogger(BookingController.class);
	
	@Autowired
	private BookingService  bookingService;
	
	@PostMapping("/booking")
	ResponseEntity<Booking> newBooking( @RequestParam String sp_id, @RequestParam String u_id)
	{

		String bk_id = new Booking().getBk_id();
		logger.info("In New Booking "+bk_id);
		Booking savedBooking = bookingService.saveBooking(bk_id,sp_id,u_id);
		return new ResponseEntity<Booking>(savedBooking, HttpStatus.CREATED);
		
	}
	
	@GetMapping(value = "/booking/{id}")
	ResponseEntity<Booking> findBooking(@PathVariable ("id") String id)
	{
		logger.info("In Find booking By id");
		Booking booking = bookingService.findBooking(id);
		if(booking==null)
		{
			logger.error("No Booking With Id "+id);
			return new ResponseEntity<Booking>(HttpStatus.NO_CONTENT);
		}
		logger.info("Fetched Booking With Id "+id);
		return new ResponseEntity<Booking>(booking, HttpStatus.OK);
	}
	//ResponseEntity<Booking> findBookingByUserId()
	@Cacheable(key="booking")
	@GetMapping("/bookings")
	ResponseEntity<List<Booking>> findAllBooking()
	{
		logger.info("In get all booking");
		return new ResponseEntity<List<Booking>>(bookingService.getAllBookings(), HttpStatus.OK);
	}
	

}
