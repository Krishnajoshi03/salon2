package com.example.cruddemo.model;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
@JsonInclude(JsonInclude.Include.NON_NULL)
@Document("sl_shop")

public class Shop implements Serializable {
	

	/**
	 * 
	 */
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
	Owner sp_own;
	
	

	public boolean isSp_act_sts() {
		return sp_act_sts;
	}



	public void setSp_act_sts(boolean sp_act_sts) {
		this.sp_act_sts = sp_act_sts;
	}



	public Date getSp_acc_created_date() {
		return sp_acc_created_date;
	}



	public void setSp_acc_created_date(Date sp_acc_created_date) {
		this.sp_acc_created_date = sp_acc_created_date;
	}



	public String getSp_nm() {
		return sp_nm;
	}



	public void setSp_nm(String sp_nm) {
		this.sp_nm = sp_nm;
	}



	public String getSp_id() {
		return sp_id;
	}

	public void setSp_id(String sp_id) {
		this.sp_id = sp_id;
	}



	public Address getSp_addr() {
		return sp_addr;
	}

	public void setSp_addr(Address sp_addr) {
		this.sp_addr = sp_addr;
	}



	public String getMobileNumber() {
		return mobileNumber;
	}



	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}



	public List<String> getSp_pts() {
		return sp_pts;
	}



	public void setSp_pts(List<String> sp_pts) {
		this.sp_pts = sp_pts;
	}



	public Location getSp_loc() {
		return sp_loc;
	}



	public void setSp_loc(Location sp_loc) {
		this.sp_loc = sp_loc;
	}



	public String getSp_rating() {
		return sp_rating;
	}



	public void setSp_rating(String sp_rating) {
		this.sp_rating = sp_rating;
	}



	public String getSp_dspn() {
		return sp_dspn;
	}



	public void setSp_dspn(String sp_dspn) {
		this.sp_dspn = sp_dspn;
	}



	public String getSp_opn_tm() {
		return sp_opn_tm;
	}



	public void setSp_opn_tm(String sp_opn_tm) {
		this.sp_opn_tm = sp_opn_tm;
	}



	public String getSp_cls_tm() {
		return sp_cls_tm;
	}



	public void setSp_cls_tm(String sp_cls_tm) {
		this.sp_cls_tm = sp_cls_tm;
	}



	public Shop() {
		//sp_id=new ObjectId();
		
	}
	
	

}
