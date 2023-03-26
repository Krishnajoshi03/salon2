package com.example.cruddemo.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@AllArgsConstructor
public class Address  implements Serializable{
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
