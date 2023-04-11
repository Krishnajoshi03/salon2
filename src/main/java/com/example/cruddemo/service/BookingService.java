package com.example.cruddemo.service;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.cruddemo.model.Booking;

@Service
public interface BookingService  
{
	

	public Booking findBooking(String id);

	public List<Booking> getAllBookings();



  public 	Booking saveBooking(String bk_id, String sp_id, String u_id);

}
