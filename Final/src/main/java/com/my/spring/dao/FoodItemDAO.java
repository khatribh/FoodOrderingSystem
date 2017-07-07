package com.my.spring.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.my.spring.exception.FoodItemException;
import com.my.spring.exception.HotelException;

import com.my.spring.pojo.FoodItem;
import com.my.spring.pojo.Hotel;
import com.my.spring.pojo.Order;


public class FoodItemDAO extends DAO {
	public FoodItemDAO() {
	}
	public FoodItem add(Long id,FoodItem f)
			throws FoodItemException {
		try {
			begin();
			System.out.println("inside DAO");
			
			FoodItem food=new FoodItem(f.getFoodName(), f.getDescription(),f.getPrice());
			Query q=getSession().createQuery("from Hotel where hotelID= :id");
			q.setLong("id", id);
            Hotel h=(Hotel)q.uniqueResult();
			food.setHotel(h);
			getSession().save(food);
			commit();
			return food;

		} catch (HibernateException e) {
			rollback();
			throw new FoodItemException("Exception while creating foodItem: " + e.getMessage());
		}
	}
	public List<FoodItem> getListMenu(Long id)
			throws FoodItemException {
		try {
			begin();
			System.out.println("inside DAO");
			System.out.println("idin dao"+id);
			//FoodItem food=new FoodItem(f.getFoodName(), f.getDescription(),f.getPrice());
			Query q=getSession().createQuery("from FoodItem where hotel_hotelID= :id");
			q.setLong("id", id);
			List<FoodItem> food = q.list();
			
			
			commit();
			return food;

		} catch (HibernateException e) {
			rollback();
			throw new FoodItemException("Exception while creating foodItem: " + e.getMessage());
		}
	}
	public List<Order> getOrderList(Long foodID) throws Exception{
		
		try {
			begin();
			//System.out.println("Photos"+hotelID);
			Query q=getSession().createQuery("from Order where foodId=:foodId");
			q.setLong("foodId", foodID);
			List<Order> list=q.list();
			commit();
			return list;
		} catch (HibernateException e) {
			rollback();
			throw new Exception("Exception while creating user: " + e.getMessage());
		}
	}
	public List<FoodItem> getListMenu1(String id)
			throws FoodItemException {
		try {
			begin();
			System.out.println("inside DAO");
			System.out.println("idin dao"+id);
			//FoodItem food=new FoodItem(f.getFoodName(), f.getDescription(),f.getPrice());
			Query q=getSession().createQuery("from FoodItem where hotel_hotelID= :id");
			q.setString("id", id);
			List<FoodItem> food = q.list();
			
			
			commit();
			return food;

		} catch (HibernateException e) {
			rollback();
			throw new FoodItemException("Exception while creating foodItem: " + e.getMessage());
		}
	}
	public FoodItem getFoodItem(String id)
			throws FoodItemException {
		try {
			begin();
			System.out.println("inside DAO Food Item");
			
			//FoodItem food=new FoodItem(f.getFoodName(), f.getDescription(),f.getPrice());
			Query q=getSession().createQuery("from FoodItem where foodId= :id");
			q.setLong("id", Long.parseLong(id));
			FoodItem food =  (FoodItem) q.uniqueResult();
			
			
			commit();
			return food;

		} catch (HibernateException e) {
			System.out.println("Hello"+e);
			rollback();
			throw new FoodItemException("Exception while creating foodItem: " + e.getMessage());
		}
	}
	public void updateFoodItem(String foodId,String foodName,String description,String price)
			throws FoodItemException {
		try {
			begin();
			System.out.println("inside DAO Food Item");
			
			//FoodItem food=new FoodItem(f.getFoodName(), f.getDescription(),f.getPrice());
			Query q=getSession().createQuery("update FoodItem set foodName=:foodName, description=:description, price=:price where foodId=:foodId ");
			q.setString("foodName", foodName);
			q.setString("description", description);
			q.setString("price", price);
			q.setString("foodId", foodId);
			int result=q.executeUpdate();
			
			
			commit();
			

		} catch (HibernateException e) {
			System.out.println("Hello"+e);
			rollback();
			throw new FoodItemException("Exception while creating foodItem: " + e.getMessage());
		}
	}
}
