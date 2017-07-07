package com.my.spring.pojo;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "photoHotel_table")
public class Photos {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "photoId", unique = true, nullable = false)
	private long id;
	
	
	@Column(name="filename")
	private String filename;
	
	@ManyToOne
	@JoinColumn(name="hotelID")
	private Hotel hotel;
	
	@ManyToOne
	@JoinColumn(name="userID")
	private User user;
	
	public Photos(){
		super();
	}
	public Photos(String filename){
		this.filename=filename;
	}
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	
	
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	
	
	
}
