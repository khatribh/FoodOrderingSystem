package com.my.spring.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.my.spring.exception.CartException;
import com.my.spring.exception.FoodItemException;
import com.my.spring.pojo.Cart;
import com.my.spring.pojo.FoodItem;
import com.my.spring.pojo.Hotel;
import com.my.spring.pojo.User;

public class CartDAO extends DAO {
	public CartDAO() {
	}
	public Cart add(FoodItem f,User user, int quantity)
			throws CartException {
		try {
			begin();
			System.out.println("inside DAO");
			Cart c=new Cart();
			c.setFoodItem(f);
			c.setQuantity(quantity);
			c.setUser(user);
			getSession().save(c);
			commit();
			return c;

		} catch (HibernateException e) {
			System.out.println("Hi"+e);
			rollback();
			throw new CartException("Exception while creating foodItem: " + e.getMessage());
		}
	}
	public boolean check(String foodID,Long userID)
			throws CartException {
		try {
			begin();
			System.out.println("inside DAO");
			Query q=getSession().createQuery("from Cart where foodId=:foodID and userID=:userID");
			q.setString("foodID", foodID);
			q.setLong("userID", userID);
			Cart c=(Cart) q.uniqueResult();
			close();
			if(c!=null)
			 return false;
			else
			 return true;
		} catch (HibernateException e) {
			System.out.println("Hi"+e);
			rollback();
			throw new CartException("Exception while creating foodItem: " + e.getMessage());
		}
	}
	public Cart getQuantity(String foodID,Long userID)
			throws CartException {
		try {
			begin();
			System.out.println("inside DAO");
			Query q=getSession().createQuery("from Cart where foodId=:foodID and userID=:userID");
			q.setString("foodID", foodID);
			q.setLong("userID", userID);
			Cart c=(Cart) q.uniqueResult();
			close();
			return c;

		} catch (HibernateException e) {
			System.out.println("Hi"+e);
			rollback();
			throw new CartException("Exception while creating foodItem: " + e.getMessage());
		}
	}
	public void updateQty(String foodID,Long userID,int quantity)
			throws CartException {
		try {
			begin();
			System.out.println("inside DAO"+quantity);
			Query q=getSession().createQuery("Update Cart set quantity=:quantity where foodId=:foodID and userID=:userID");
			q.setInteger("quantity", quantity);
			q.setString("foodID", foodID);
			q.setLong("userID", userID);
			int result=q.executeUpdate();
			close();
			System.out.println("result"+result);
		} catch (HibernateException e) {
			System.out.println("Hi"+e);
			rollback();
			throw new CartException("Exception while creating foodItem: " + e.getMessage());
		}
	}
	public void removeCart(String foodID,Long userID)
			throws CartException {
		try {
			begin();
			//System.out.println("inside DAO"+quantity);
			Query q=getSession().createQuery("delete from Cart where foodId=:foodID and userID=:userID");
			q.setString("foodID", foodID);
			q.setLong("userID", userID);
			int result=q.executeUpdate();
			close();
			System.out.println("result"+result);
		} catch (HibernateException e) {
			System.out.println("Hi"+e);
			rollback();
			throw new CartException("Exception while creating foodItem: " + e.getMessage());
		}
	}
	public List<Cart> getCart(Long id)
			throws CartException {
		try {
			begin();
			System.out.println("inside DAO");
			Query q=getSession().createQuery("from Cart where userID=:userid");
			q.setLong("userid", id);
			List<Cart> list=q.list();
			commit();
			return list;

		} catch (HibernateException e) {
			System.out.println("Hi"+e);
			rollback();
			throw new CartException("Exception while creating foodItem: " + e.getMessage());
		}
	}
}
