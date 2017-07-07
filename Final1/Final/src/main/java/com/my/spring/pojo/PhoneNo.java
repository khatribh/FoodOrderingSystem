package com.my.spring.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "phoneNo_table")
public class PhoneNo {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "phoneNoID", unique = true, nullable = false)
	private long id;
	
	@Column(name = "phone_No")
	private String phoneNo;
	
	@OneToOne
	@JoinColumn(name="userID")
	private User user;
	
	@OneToOne
	
	private Hotel hotel;
	
	public PhoneNo() {
	}

	public PhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
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
	
}
