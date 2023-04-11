package com.example.cruddemo.model;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.data.annotation.Id;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
@JsonInclude(JsonInclude.Include.NON_NULL)
@Document("sl_shop")
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class Shop implements Serializable {
	

	private static final long serialVersionUID = 1L;
	
	@DateTimeFormat(iso = ISO.TIME)
	@JsonProperty("sp_acc_created_date")
	private Date sp_acc_created_date = new Date();

	@JsonProperty("sp_nm")
	private String sp_nm;
	
	@JsonProperty("sp_id")
	@Id
	private String sp_id;
	@JsonProperty("sp_addr")
	private Address sp_addr;
	@JsonProperty("mobileNumber")
	private String mobileNumber;
	@JsonProperty("sp_pts")
	private List<String> sp_pts; //
	@JsonProperty("sp_loc")
	private Location sp_loc;
	@JsonProperty("sp_rating")
	private String sp_rating;
	@JsonProperty("sp_dspn")
	private String sp_dspn;
	@JsonProperty("sp_opn_tm")
	private String sp_opn_tm;
	@JsonProperty("sp_cls_tm")
	private String sp_cls_tm;
	@JsonProperty("sp_act_sts")
	private boolean sp_act_sts;
	
	@DBRef
	@JsonProperty("sp_booking")
	List<Booking> sp_booking;
	
	List<Map<String, String>> sp_services;
	
	@DBRef
	@JsonProperty("sp_own")
	Owner sp_own;

}
