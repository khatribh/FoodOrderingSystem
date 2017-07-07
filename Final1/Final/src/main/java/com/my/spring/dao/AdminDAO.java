package com.my.spring.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.my.spring.exception.HotelException;
import com.my.spring.exception.UserException;
import com.my.spring.pojo.Admin;
import com.my.spring.pojo.Hotel;
import com.my.spring.pojo.User;

public class AdminDAO extends DAO {
	public AdminDAO() {
	}
	public Admin getAdmin(String username, String password) throws UserException {
		try {
			begin();
			Query q = getSession().createQuery("from Admin where username = :username and password = :password");
			q.setString("username", username);
			q.setString("password", password);			
			Admin a = (Admin) q.uniqueResult();
			commit();
			return a;
		} catch (HibernateException e) {
			rollback();
			throw new UserException("Could not get user " + username, e);
		}
	}
	public List<User> getAll() throws UserException {
		try {
			begin();
			Query q = getSession().createQuery("from User");
			List<User> user = q.list();	
			
			commit();
			return user;
		} catch (HibernateException e) {
			rollback();
			throw new UserException("Could not get user " + e);
		}
	}
	public List<Hotel> getAllHotel() throws HotelException {
		try {
			begin();
			Query q = getSession().createQuery("from Hotel where status =:status");
			q.setString("status", "Approved");
			List<Hotel> hotel = q.list();	
			
			commit();
			return hotel;
		} catch (HibernateException e) {
			rollback();
			throw new HotelException("Could not get user " + e);
		}
	}
	public List<Hotel> getAllPending() throws HotelException {
		try {
			begin();
			Query q = getSession().createQuery("from Hotel where status =:status");
			q.setString("status", "Pending");
			List<Hotel> hotel = q.list();	
			
			commit();
			return hotel;
		} catch (HibernateException e) {
			rollback();
			throw new HotelException("Could not get user " + e);
		}
	}
	public void setStatus(String hotelID) throws HotelException {
		try {
			begin();
			Query q = getSession().createQuery("Update Hotel set status =:status where hotelID=:hotelID");
			q.setString("status", "Approved");
			q.setString("hotelID", hotelID);
			int result=q.executeUpdate();
			
			commit();
			
		} catch (HibernateException e) {
			rollback();
			throw new HotelException("Could not get user " + e);
		}
	}
	public void setDeactive(String hotelID) throws HotelException {
		try {
			begin();
			Query q = getSession().createQuery("Update Hotel set active =:active where hotelID=:hotelID");
			q.setString("active", "Deactive");
			q.setString("hotelID", hotelID);
			int result=q.executeUpdate();
			
			commit();
			
		} catch (HibernateException e) {
			rollback();
			throw new HotelException("Could not get user " + e);
		}
	}
	public void setActive(String hotelID) throws HotelException {
		try {
			begin();
			Query q = getSession().createQuery("Update Hotel set active =:active where hotelID=:hotelID");
			q.setString("active", "Active");
			q.setString("hotelID", hotelID);
			int result=q.executeUpdate();
			
			commit();
			
		} catch (HibernateException e) {
			rollback();
			throw new HotelException("Could not get user " + e);
		}
	}
	
}
