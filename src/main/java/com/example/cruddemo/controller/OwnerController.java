package com.example.cruddemo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.cruddemo.model.Owner;
import com.example.cruddemo.repository.OwnerRepository;
import com.example.cruddemo.service.OwnerService;
import com.example.cruddemo.service.OwnerServiceimpl;

@RestController
@RequestMapping("/api")
public class OwnerController
{
	@Autowired
	OwnerService service;
	
	@PostMapping("/owner")
	ResponseEntity<String> createOwner(String userId)
	{
		String id=service.createOwner(userId);
		if(id=="")
		{
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<String>(id,HttpStatus.CREATED);
	}
	@GetMapping("/owner")
	ResponseEntity<Owner> getOwner(String mob_num)
	{
		Owner owner= service.getOwner(mob_num);
		if(owner==null)
		{
			return new ResponseEntity<Owner>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Owner>(owner,HttpStatus.OK);
	}
	
}
