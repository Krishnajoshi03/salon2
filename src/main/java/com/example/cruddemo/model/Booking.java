package com.example.cruddemo.model;

import java.awt.print.Book;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import org.bson.codecs.jsr310.LocalDateTimeCodec;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.cruddemo.model.supportClass.BookingStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@AllArgsConstructor
@Document("sp_bkng")
public class Booking  implements Serializable
{
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;



	public Booking()
	 {
		 bk_id=new ObjectId().toString();
		 bk_bk_time= LocalDateTime.now();
	 }
	  public Booking(String bk_id,Shop sp_id,Users u_id)
	  {
			
			 this.bk_id = bk_id;
		  this.bk_sp_id= sp_id;
		  this.bk_u_id= u_id;
	  }
	 @Id
	 @JsonProperty("bk_id")
	 String bk_id;
	 
	 @DateTimeFormat(pattern = "DD/MM/YYYY HH-MM")
	 @JsonProperty("bk_bk_time")
	 LocalDateTime bk_bk_time;
	 
	 @JsonProperty("bk_sp_id")
	 @DBRef
	 Shop bk_sp_id;
	 
	 @JsonProperty("bk_u_id")
	 @DBRef
	  Users bk_u_id;
	 
	 
	 
	 @JsonProperty("bk_status")
	 BookingStatus bk_status;
	 
	
}
