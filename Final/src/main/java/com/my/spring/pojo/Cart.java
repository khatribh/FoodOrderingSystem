package com.my.spring.pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import javax.persistence.Table;

@Entity
@Table(name = "cart_table")
public class Cart implements Serializable {
	

	@Id
	@ManyToOne
	@JoinColumn(name = "userID")
	private User user;

	@Id
	@ManyToOne
	@JoinColumn(name = "foodId")
	private FoodItem foodItem;

	@Column(name = "quantity")
	private int quantity;

	public Cart() {
		super();
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public FoodItem getFoodItem() {
		return foodItem;
	}

	public void setFoodItem(FoodItem foodItem) {
		this.foodItem = foodItem;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	   
	   


	
	
	
}
