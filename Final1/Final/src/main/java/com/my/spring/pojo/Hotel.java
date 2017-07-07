package com.my.spring.pojo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TimeZone;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

@Entity
@Table(name = "hotel_table")

public class Hotel {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "hotelID", unique=true, nullable = false)
	private long hotelID;
	
	
	@Column(name = "hotelName")
	private String hotelName;
	
	@Column(name = "username")
	private String username;

	@Column(name = "password")
	private String password;
	
	@Column(name = "cuisines")
	private String cuisines;
	
	@Column(name = "rate")
	private double rate;
	
	@OneToOne(mappedBy = "hotel", cascade = CascadeType.ALL)
	private Email email;
	
	@OneToOne(mappedBy = "hotel", cascade = CascadeType.ALL)
	private PhoneNo phoneNo;
	
	@OneToOne(mappedBy = "hotel", cascade = CascadeType.ALL)
    private Address address;
	
	@OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL)
	private List<FoodItem> menu=new ArrayList<FoodItem>();
	
	@OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL)
	private Set<Photos> photos=new HashSet<Photos>();
	
	@Transient
	private Set<CommonsMultipartFile> photo = new HashSet<CommonsMultipartFile>();
	
	@Column(name = "status")
	private String status;
	
	
	
	public Hotel(){
		
	}
	public Hotel(String userName, String password){
		this.username=userName;
		this.password=password;
		
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Email getEmail() {
		return email;
	}
	public void setEmail(Email email) {
		this.email = email;
	}
	public PhoneNo getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(PhoneNo phoneNo) {
		this.phoneNo = phoneNo;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public List<FoodItem> getMenu() {
		return menu;
	}
	public void setMenu(List<FoodItem> menu) {
		this.menu = menu;
	}
	
	
	public Set<Photos> getPhotos() {
		return photos;
	}
	public void setPhotos(Set<Photos> photos) {
		this.photos = photos;
	}
	public Set<CommonsMultipartFile> getPhoto() {
		return photo;
	}
	public void setPhoto(Set<CommonsMultipartFile> photo) {
		this.photo = photo;
	}
	public long getHotelID() {
		return hotelID;
	}
	public void setHotelID(long hotelID) {
		this.hotelID = hotelID;
	}
	public String getHotelName() {
		return hotelName;
	}
	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}
	public String getCuisines() {
		return cuisines;
	}
	public void setCuisines(String cuisines) {
		this.cuisines = cuisines;
	}
	public double getRate() {
		return rate;
	}
	public void setRate(double rate) {
		this.rate = rate;
	}
	
	
	
	
	
}
