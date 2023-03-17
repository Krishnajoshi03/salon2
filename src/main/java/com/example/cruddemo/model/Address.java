package com.example.cruddemo.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@AllArgsConstructor
public class Address {
	String addressLine;
	public Address() {
		super();
		// TODO Auto-generated constructor stub
	}
	String city;
	String state;
	String landmark;
	String pin;
	
}
