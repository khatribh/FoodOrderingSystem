package com.my.spring.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.my.spring.dao.AdminDAO;
import com.my.spring.dao.HotelDAO;
import com.my.spring.dao.UserDAO;
import com.my.spring.pojo.Admin;
import com.my.spring.pojo.Hotel;
import com.my.spring.pojo.Photos;
import com.my.spring.pojo.User;
import com.my.spring.validator.HotelValidator;
import com.my.spring.validator.UserValidator;



@Controller
public class NavigateController {
	@Autowired
	@Qualifier("userDao")
	UserDAO userDao;
	
	@Autowired
	@Qualifier("hotelDao")
	HotelDAO hotelDao;
	
	@Autowired
	@Qualifier("adminDao")
	AdminDAO adminDao;
	
	@RequestMapping(value = "/logout.htm", method = RequestMethod.GET)
    public ModelAndView redirectLogout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();
        return new ModelAndView("index");
    }
	@RequestMapping(value = "/register.htm", method = RequestMethod.GET)
	protected ModelAndView navigateRegister() throws Exception {
		

		return new ModelAndView("register-navigate");

	}
	@RequestMapping(value = "/loginPage.htm", method = RequestMethod.GET)
	protected ModelAndView navigateLogin() throws Exception {
		

		return new ModelAndView("login-navigate");

	}
	@RequestMapping(value = "/register-nav.htm", method = RequestMethod.POST)
	protected ModelAndView register(HttpServletRequest request) throws Exception {
		
		String type= request.getParameter("type");
		if(type.equalsIgnoreCase("User")){
			return new ModelAndView("user-register", "user", new User());
		}
		else
			return new ModelAndView("hotel-register","hotel", new Hotel());
		
		
		

	}
	@RequestMapping(value = "/login-nav.htm", method = RequestMethod.GET)
	protected ModelAndView loginNav(HttpServletRequest request) throws Exception {
		return new ModelAndView("index");
	}
	@RequestMapping(value = "/login-nav.htm", method = RequestMethod.POST)
	protected ModelAndView login(HttpServletRequest request) throws Exception {
		HttpSession session=request.getSession();
		
		String type= request.getParameter("type");
		if(type.equalsIgnoreCase("Admin")){
			Admin admin=adminDao.getAdmin(request.getParameter("username"), request.getParameter("password"));
			if(admin==null){
				return new ModelAndView("errorPage");
			}
			session.setAttribute("admin", admin);
			session.setAttribute("role", "admin");
			System.out.println("Admin"+admin.getUsername());
				return new ModelAndView("admin-profile","admin",admin);
		}
		
		if(type.equalsIgnoreCase("Hotel")){
			Hotel hotel =hotelDao.get(request.getParameter("username"), request.getParameter("password"));
			if(hotel==null){
				return new ModelAndView("errorPage");
			}
			if(hotel.getStatus().equalsIgnoreCase("Pending")){
				return new ModelAndView("pending-hotel");
			}
			session.setAttribute("hotel", hotel);
			session.setAttribute("role", "hotel");
			List<Photos> list=hotelDao.getPhotos(hotel.getHotelID());
			return new ModelAndView("hotel-profile","list", list);
		}
		
		else{
			User user = userDao.get(request.getParameter("username"), request.getParameter("password"));
			if(user==null){
				return new ModelAndView("errorPage");
			}
			session.setAttribute("user", user);
			session.setAttribute("role", "user");
		    return new ModelAndView("user-profile","user", user);
		
		}
		
		

	}
	
	@RequestMapping(value = "/index.htm", method = RequestMethod.GET)
	protected ModelAndView homePage() throws Exception {
		return new ModelAndView("index");

	}
}
