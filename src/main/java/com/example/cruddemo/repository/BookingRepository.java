package com.example.cruddemo.repository;

import java.util.List;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.cruddemo.model.Booking;

@Repository
public interface BookingRepository extends MongoRepository<Booking, String>
{


}
