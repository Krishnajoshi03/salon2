package com.example.cruddemo.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.redis.core.RedisHash;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mongodb.lang.NonNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;


@Document("sl_user")
@Data
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@RequiredArgsConstructor
public class Users  implements Serializable{

	private static final long serialVersionUID = 1L;
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
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

	

}
