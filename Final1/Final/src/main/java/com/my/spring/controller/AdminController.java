package com.my.spring.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.my.spring.dao.AdminDAO;
import com.my.spring.exception.HotelException;
import com.my.spring.exception.UserException;
import com.my.spring.pojo.Hotel;

import com.my.spring.pojo.User;



@Controller
@RequestMapping("/admin/*")
public class AdminController {
	@Autowired
	@Qualifier("adminDao")
	AdminDAO adminDao;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	protected String goToAdminHome(HttpServletRequest request) throws Exception {
		return "admin-profile";
	}
	@RequestMapping(value = "/admin/list", method = RequestMethod.GET)
	protected ModelAndView listUser(HttpServletRequest request) throws Exception {


		try {

		

			List<User> list = adminDao.getAll();
			
			//request.getSession().setAttribute("user", u);
			
			return new ModelAndView("list-user", "list", list);

		} catch (UserException e) {
			System.out.println("Exception: " + e.getMessage());
			return new ModelAndView("error", "errorMessage", "error while login");
		}
	}
	@RequestMapping(value = "/admin/hotelList", method = RequestMethod.GET)
	protected ModelAndView listHotel(HttpServletRequest request) throws Exception {
		

		try {

			

			List<Hotel> list =adminDao.getAllHotel();
			
			//request.getSession().setAttribute("user", u);
			
			return new ModelAndView("list-hotel", "list", list);

		} catch (HotelException e) {
			System.out.println("Exception: "+e.getMessage());
			return new ModelAndView("error", "errorMessage", "error while login");
		}
	}
	@RequestMapping(value = "/admin/pendingList", method = RequestMethod.GET)
	protected ModelAndView listUserPending(HttpServletRequest request) throws Exception {
		//ModelAndView mv = new ModelAndView();
		//validator.validate(user, result);

//		if (result.hasErrors()) {
//			return new ModelAndView("user-register", "user", user);
//		}

		try {

			//System.out.print("registerNewUser");

			List<Hotel> list =adminDao.getAllPending();
			
			//request.getSession().setAttribute("user", u);
			
			return new ModelAndView("list-hotelpending", "list", list);

		} catch (HotelException e) {
			System.out.println("Exception: " + e.getMessage());
			return new ModelAndView("error", "errorMessage", "error while login");
		}
	}
	@RequestMapping(value = "/admin/approveHotel", method = RequestMethod.GET)
	public @ResponseBody
	String addtoCart(HttpServletRequest request) throws Exception {
		HttpSession session=request.getSession();
	
		
		String hotelID=request.getParameter("hotelID");
		
       adminDao.setStatus(hotelID);
        
        
			
			
			//request.getSession().setAttribute("hotel", hotelList);
			
			return "Hotel Approved";

		
	}
	@RequestMapping(value = "/admin/deactivateHotel", method = RequestMethod.GET)
	public @ResponseBody
	String deactivateHotel(HttpServletRequest request) throws Exception {
		HttpSession session=request.getSession();
	
		
		String hotelID=request.getParameter("hotelID");
		
       adminDao.setDeactive(hotelID);
        
        
			
			
			//request.getSession().setAttribute("hotel", hotelList);
			
			return "Hotel Status Set To Deactive";

		
	}
	@RequestMapping(value = "/admin/activateHotel", method = RequestMethod.GET)
	public @ResponseBody
	String activateHotel(HttpServletRequest request) throws Exception {
		HttpSession session=request.getSession();
	
		
		String hotelID=request.getParameter("hotelID");
		
       adminDao.setActive(hotelID);
        
        
			
			
			//request.getSession().setAttribute("hotel", hotelList);
			
			return "Hotel Status Set To Active";

		
	}
}
