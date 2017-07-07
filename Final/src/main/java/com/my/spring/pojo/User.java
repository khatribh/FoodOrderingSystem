package com.my.spring.pojo;



import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
@Table(name = "user_table")

public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "userID", unique=true, nullable = false)
	private long userID;
	
	@Column(name = "firstName")
	private String firstName;
	
	@Column(name = "lastName")
	private String lastName;
	
	@Column(name = "userName")
	private String username;

	@Column(name = "password")
	private String password;
	
	
	
	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
	private Email email;
	
	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
	private PhoneNo phoneNo;
	
	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Address address;
	
	@OneToMany(mappedBy = "user" , cascade = CascadeType.ALL)
    private List<Cart> cart = new ArrayList<Cart>();
	
	@OneToMany(mappedBy = "user" , cascade = CascadeType.ALL)
    private List<Order> order = new ArrayList<Order>();
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private Set<Photos> photos=new HashSet<Photos>();
	
	@Transient
	private Set<CommonsMultipartFile> photo = new HashSet<CommonsMultipartFile>();
	
	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public User() {
	
	}
	

	public long getUserID() {
		return userID;
	}

	public void setUserID(long userID) {
		this.userID = userID;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
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

	public List<Cart> getCart() {
		return cart;
	}

	public void setCart(List<Cart> cart) {
		this.cart = cart;
	}

	public List<Order> getOrder() {
		return order;
	}

	public void setOrder(List<Order> order) {
		this.order = order;
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
	
	
	
	
	
}