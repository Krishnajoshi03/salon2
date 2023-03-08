package com.example.cruddemo.model;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mongodb.lang.NonNull;
import lombok.Data;
import lombok.Getter;

@Data
@Document("sl_user")
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Users {

	@JsonCreator
	public Users() {
		super();
		// TODO Auto-generated constructor stub
	}

	@JsonProperty("u_fn")
	private String u_fn;
	@JsonProperty("u_ln")
	private String u_ln;
	@JsonProperty("u_em")
	private String u_em;
	@JsonProperty("u_mn")
	@Id
	private String u_mn;
	@JsonProperty("u_pic")
	private String u_pic;

	@JsonProperty("u_addr")
	private Address u_addr;
	@DBRef
	@JsonProperty("u_fav")
	private List<Shop> u_fav;

	public List<Shop> getU_fav() {
		return u_fav;
	}

	public void setU_fav(List<Shop> u_fav) {
		this.u_fav = u_fav;
	}

	public String getU_fn() {
		return u_fn;
	}

	public void setU_fn(String u_fn) {
		this.u_fn = u_fn;
	}

	public String getU_ln() {
		return u_ln;
	}

	public void setU_ln(String u_ln) {
		this.u_ln = u_ln;
	}

	public String getU_em() {
		return u_em;
	}

	public void setU_em(String u_em) {
		this.u_em = u_em;
	}

	public String getU_mn() {
		return u_mn;
	}

	public void setU_mn(String u_mn) {
		this.u_mn = u_mn;
	}

	public String getU_pic() {
		return u_pic;
	}

	public void setU_pic(String u_pic) {
		this.u_pic = u_pic;
	}

	public Address getU_addr() {
		return u_addr;
	}

	public void setU_addr(Address u_addr) {
		this.u_addr = u_addr;
	}

}
