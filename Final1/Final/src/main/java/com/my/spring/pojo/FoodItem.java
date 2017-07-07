package com.my.spring.pojo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "foodItem_table")
public class FoodItem {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "foodId", unique = true, nullable = false)
	private long foodId;

	@Column(name = "foodName")
	private String foodName;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "price")
	private double price;
	
	@ManyToOne
	private Hotel hotel;
	
	@OneToMany(mappedBy = "foodItem")
    private List<Cart> cart = new ArrayList<Cart>();
	
	@OneToMany(mappedBy = "foodItem")
    private List<Order> order = new ArrayList<Order>();
	
	public FoodItem(){
		
	}
	
	public FoodItem(String foodName, String description, double price){
		this.foodName=foodName;
		this.description=description;
		this.price=price;
	}
	
	

	public long getFoodId() {
		return foodId;
	}

	public void setFoodId(long foodId) {
		this.foodId = foodId;
	}

	public String getFoodName() {
		return foodName;
	}

	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
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

	
	
	
	
}
