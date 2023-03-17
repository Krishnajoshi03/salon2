package com.example.cruddemo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@Document("sl_owner")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Owner {
	
	@JsonProperty("own_fn")
	String firstName;
	@JsonProperty("own_ln")
	String lastName;
	@Id
	@JsonProperty("own_mn")
	String mobile_num;
	@JsonProperty("own_em")
	String e_mail;
	@JsonProperty("own_addr")
	Address address;
	@JsonProperty("own_shp")
	@DBRef 
	Shop shop;
	
}
