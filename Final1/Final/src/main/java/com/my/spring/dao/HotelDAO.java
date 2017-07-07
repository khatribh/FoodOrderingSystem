package com.my.spring.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import com.my.spring.exception.HotelException;
import com.my.spring.exception.UserException;
import com.my.spring.pojo.Address;
import com.my.spring.pojo.Email;
import com.my.spring.pojo.Hotel;
import com.my.spring.pojo.Order;
import com.my.spring.pojo.PhoneNo;
import com.my.spring.pojo.Photos;
import com.my.spring.pojo.User;



public class HotelDAO extends DAO{
	public HotelDAO() {
	}

	public Hotel get(String username, String password) throws HotelException {
		try {
			begin();
			Query q = getSession().createQuery("from Hotel where username = :username and password = :password");
			q.setString("username", username);
			q.setString("password", password);			
			Hotel hotel = (Hotel) q.uniqueResult();
			commit();
			return hotel;
		} catch (HibernateException e) {
			rollback();
			throw new HotelException("Could not get hotel " + username, e);
		}
	}
public Photos registerPhotos(Photos photos) throws Exception{
		
		try {
			begin();
			System.out.println("inside Hotel_PhotosDAO");
			getSession().save(photos);
			commit();
			return photos;
		} catch (HibernateException e) {
			rollback();
			throw new Exception("Exception while creating user: " + e.getMessage());
		}
	}
public boolean checkUsername(String username) throws UserException {
	try {
		 begin();
            Query q = getSession().createQuery("from User where username = :username");
            q.setString("username", username);
            System.out.print(q);
            User user = (User) q.uniqueResult();
            System.out.print(user);
            if(user == null) {
                return true;
            } else {
                return false;
            }
        }catch (HibernateException e) {
		rollback();
		throw new UserException("Could not get user " + e);
	}
}
public boolean checkEmail(String email) throws UserException {
	try {
		 begin();
            Query q = getSession().createQuery("from Email where emailAddress = :email");
            q.setString("email", email);
            System.out.print("em"+email);
            Email user = (Email) q.uniqueResult();
            System.out.print(user);
            if(user == null) {
                return true;
            } else {
                return false;
            }
        } catch (HibernateException e) {
		rollback();
		throw new UserException("Could not get user " + e);
	}
}
	public void add(Hotel hotel) throws Exception{
		
		try {
			begin();
			System.out.println("inside Hotel_PhotosDAO");
			getSession().save(hotel);
			commit();
			
		} catch (HibernateException e) {
			rollback();
			throw new Exception("Exception while creating user: " + e.getMessage());
		}
	}
	public List<Photos> getPhotos(Long hotelID) throws Exception{
			
			try {
				begin();
				System.out.println("Photos"+hotelID);
				Query q=getSession().createQuery("from Photos where hotelID=:hotelID");
				q.setLong("hotelID", hotelID);
				List<Photos> photos=q.list();
				commit();
				return photos;
			} catch (HibernateException e) {
				rollback();
				throw new Exception("Exception while creating user: " + e.getMessage());
			}
		}
	
	public Hotel add(String username, String password) throws HotelException {
		try {
			begin();
			Query q = getSession().createQuery("from Hotel where username = :username and password = :password");
			q.setString("username", username);
			q.setString("password", password);			
			Hotel hotel = (Hotel) q.uniqueResult();
			commit();
			return hotel;
		} catch (HibernateException e) {
			rollback();
			throw new HotelException("Could not get hotel " + username, e);
		}
	}
	
	public Hotel get(int userId) throws HotelException {
		try {
			begin();
			Query q = getSession().createQuery("from Hotel where hotelID= :hotelID");
			q.setInteger("hotelID", userId);		
			Hotel hotel = (Hotel) q.uniqueResult();
			commit();
			return hotel;
		} catch (HibernateException e) {
			rollback();
			throw new HotelException("Could not get hotel " + userId, e);
		}
	}
	public List<Hotel> getSearch(String hotelName, String cuisines, String rate, String area) throws HotelException {
		try {
			begin();
			Criteria crit = getSession().createCriteria(Hotel.class);
			Criteria addcrit=crit.createCriteria("address");
			if(hotelName!=null){
				System.out.println("insidehotelname"+hotelName);
				crit.add(Restrictions.ilike("hotelName",hotelName, MatchMode.ANYWHERE));
			}
			if(cuisines!=null){
				crit.add(Restrictions.ilike("cuisines",cuisines, MatchMode.EXACT));
			}
			if(rate!=null){
				crit.add(Restrictions.le("rate", Double.parseDouble(rate)));
			}
			if(area!=null){
				addcrit.add(Restrictions.ilike("streetName",area, MatchMode.ANYWHERE));
			}
					
			List<Hotel> list=crit.list();
			commit();
			return list;
		} catch (HibernateException e) {
			rollback();
			throw new HotelException("Could not get hotel "+ e);
		}
	}

	public Hotel register(Hotel h)
			throws HotelException {
		try {
			begin();
			System.out.println("inside DAO");

			Email email = new Email(h.getEmail().getEmailAddress());
			Hotel hotel = new Hotel(h.getUsername(), h.getPassword());
			PhoneNo phone = new PhoneNo(h.getPhoneNo().getPhoneNo());
			Address address = new Address(h.getAddress().getStreetName(), h.getAddress().getCity(), h.getAddress().getState(), h.getAddress().getZipcode());
			hotel.setCuisines(h.getCuisines());
			hotel.setHotelName(h.getHotelName());
			hotel.setRate(h.getRate());
			hotel.setEmail(email);
			hotel.setPhoneNo(phone);
			hotel.setAddress(address);
			
			hotel.setStatus("Pending");
			phone.setHotel(hotel);
			email.setHotel(hotel);
			address.setHotel(hotel);
			getSession().save(hotel);
			commit();
			return hotel;

		} catch (HibernateException e) {
			rollback();
			throw new HotelException("Exception while creating hotel: " + e.getMessage());
		}
	}
	
	
	
	public void delete(Hotel hotel) throws HotelException {
		try {
			begin();
			getSession().delete(hotel);
			commit();
		} catch (HibernateException e) {
			rollback();
			throw new HotelException("Could not delete hotel " + hotel.getUsername(), e);
		}
	}
}
