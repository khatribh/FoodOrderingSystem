package com.my.spring.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.my.spring.exception.UserException;
import com.my.spring.pojo.Address;
import com.my.spring.pojo.Admin;
import com.my.spring.pojo.Cart;
import com.my.spring.pojo.Email;
import com.my.spring.pojo.Order;
import com.my.spring.pojo.PhoneNo;
import com.my.spring.pojo.Photos;
import com.my.spring.pojo.User;

public class UserDAO extends DAO {

	public UserDAO() {
	}

	public User get(String username, String password) throws UserException {
		try {
			begin();
			Query q = getSession().createQuery("from User where username = :username and password = :password");
			q.setString("username", username);
			q.setString("password", password);			
			User user = (User) q.uniqueResult();
			commit();
			return user;
		} catch (HibernateException e) {
			rollback();
			throw new UserException("Could not get user " + username, e);
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
	

	public Photos registerPhotos(Photos photos) throws Exception {

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
	public List<Photos> getPhotos(Long userID) throws Exception{
		
		try {
			begin();
			System.out.println("Photos"+userID);
			Query q=getSession().createQuery("from Photos where userID=:userID");
			q.setLong("userID", userID);
			List<Photos> photos=q.list();
			commit();
			return photos;
		} catch (HibernateException e) {
			rollback();
			throw new Exception("Exception while creating user: " + e.getMessage());
		}
	}
	public int getMax() throws UserException {
		try {
			 begin();
		        Criteria crit = getSession().createCriteria(Order.class);
		        crit.setProjection(Projections.max("orderID"));
		        int maxid=0;
		        if(crit.uniqueResult()==null){
		        	System.out.println("In");
		            maxid=0;
		        }else{
		            maxid = (Integer)crit.uniqueResult();
		        }
		        System.out.println("maxid"+maxid);
		        //commit();
		        
		        return maxid;
		} catch (HibernateException e) {
			rollback();
			throw new UserException("Could not get user " + e);
		}
	}
	public List<Order> getOrders(Long userID) throws UserException {
		try {
			 begin();
		        Query q=getSession().createQuery("from Order where userID=:userID");
		        q.setLong("userID", userID);
		        List<Order> list=q.list();
		        
		        //commit();
		        return list;
		        
		} catch (HibernateException e) {
			rollback();
			throw new UserException("Could not get user " + e);
		}
	}
	public void deleteFromCart(Long foodId, Long userID) throws UserException {
		try {
			 begin();
		        Query q=getSession().createQuery("delete from Cart where foodId=:foodId and userID=:userID ");
		        q.setLong("foodId", foodId);
		        q.setLong("userID", userID);
		        int result=q.executeUpdate();
		        commit();
		        close();
		        
		} catch (HibernateException e) {
			rollback();
			throw new UserException("Could not get user " + e);
		}
	}
	public List<Cart> addOrders(Long userID) throws UserException {
		try {
			begin();
			Query q = getSession().createQuery("from Cart where userID=:userID");
			q.setLong("userID", userID);	
			List<Cart> cart=q.list();
			commit();
			return cart;
		} catch (HibernateException e) {
			rollback();
			throw new UserException("Could not get user " +e);
		}
	}
	public void addOrderTable(Order o) throws UserException {
		try {
			begin();
			getSession().save(o);
			commit();
			
		} catch (HibernateException e) {
			rollback();
			throw new UserException("Could not get user " +e);
		}
	}
	
	public User get(int userId) throws UserException {
		try {
			begin();
			Query q = getSession().createQuery("from User where userID= :userID");
			q.setInteger("userID", userId);		
			User user = (User) q.uniqueResult();
			commit();
			return user;
		} catch (HibernateException e) {
			rollback();
			throw new UserException("Could not get user " + userId, e);
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

	public User register(User u)
			throws UserException {
		try {
			begin();
			System.out.println("inside DAO");

			Email email = new Email(u.getEmail().getEmailAddress());
			User user = new User(u.getUsername(), u.getPassword());
			PhoneNo phone = new PhoneNo(u.getPhoneNo().getPhoneNo());
			Address address = new Address(u.getAddress().getStreetName(), u.getAddress().getCity(), u.getAddress().getState(), u.getAddress().getZipcode());
			user.setFirstName(u.getFirstName());
			user.setLastName(u.getLastName());
			user.setEmail(email);
			user.setPhoneNo(phone);
			user.setAddress(address);
			
			phone.setUser(user);
			email.setUser(user);
			address.setUser(user);
			getSession().save(user);
			commit();
			return user;

		} catch (HibernateException e) {
			rollback();
			throw new UserException("Exception while creating user: " + e.getMessage());
		}
	}
	public void update(User user,Long id) throws UserException {
        try {
            begin();
            Query q= getSession().createQuery("update User set firstName= :firstName, lastName =:lastName where userID= :userId");
            q.setString("firstName", user.getFirstName());
            q.setString("lastName", user.getLastName());
            q.setLong("userId", id);
            int result=q.executeUpdate();
            commit();
        } catch (HibernateException e) {
            rollback();
            throw new UserException("Could not save the category", e);
        }
    }
	public Address updateAddress(User user,Long id) throws UserException {
        try {
            begin();
            Query q= getSession().createQuery("update Address set streetName=:streetName, city=:city, state=:state, zipcode=:zipcode where userID=:userId");
            System.out.println("StreetNameDAO"+user.getAddress().getStreetName());
            q.setString("streetName", user.getAddress().getStreetName());
            q.setString("city", user.getAddress().getCity());
            q.setString("state", user.getAddress().getState());
            q.setString("zipcode", user.getAddress().getZipcode());
            q.setLong("userId", id);
            System.out.println("UserIDDAO"+user.getUserID());
            int result=q.executeUpdate();
            System.out.println("RESULT:\t"+result);
            commit();
            close();
            
            begin();
            Query q1= getSession().createQuery("from Address where userID=:userID");
            q1.setLong("userID", id);
            Address address= (Address)q1.uniqueResult();
            System.out.println("???????//"+address.getStreetName());
            commit();
            return address;
        } catch (HibernateException e) {
        	System.out.println("Hi Bhumka" + e);
            rollback();
            throw new UserException("Could not save the category", e);
        }
    }
	public PhoneNo updatePhone(User user,Long id) throws UserException {
        try {
            begin();
            Query q= getSession().createQuery("update PhoneNo set phoneNo= :phoneNo where userID= :userId");
            q.setString("phoneNo", user.getPhoneNo().getPhoneNo());
            q.setLong("userId", id);
            int result=q.executeUpdate();
            commit();
            close();
            
            begin();
            Query q1= getSession().createQuery("from PhoneNo where userID=:userID");
            q1.setLong("userID", id);
            PhoneNo phoneNo= (PhoneNo)q1.uniqueResult();
            commit();
            return phoneNo;
        } catch (HibernateException e) {
            rollback();
            throw new UserException("Could not save the category", e);
        }
    }
	public Email updateEmail(User user,Long id) throws UserException {
        try {
            begin();
            Query q= getSession().createQuery("update Email set emailAddress= :email where userID= :userId");
            q.setString("email", user.getEmail().getEmailAddress());
            q.setLong("userId", id);
            int result=q.executeUpdate();
            commit();
            close();
            
            begin();
            Query q1= getSession().createQuery("from Email where userID=:userID");
            q1.setLong("userID", id);
            Email email= (Email)q1.uniqueResult();
            commit();
            return email;
        } catch (HibernateException e) {
            rollback();
            throw new UserException("Could not save the category", e);
        }
    }
	public void delete(User user) throws UserException {
		try {
			begin();
			getSession().delete(user);
			commit();
		} catch (HibernateException e) {
			rollback();
			throw new UserException("Could not delete user " + user.getUsername(), e);
		}
	}
}