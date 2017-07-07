package com.my.spring.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import com.my.spring.dao.CartDAO;
import com.my.spring.dao.FoodItemDAO;
import com.my.spring.exception.FoodItemException;
import com.my.spring.exception.HotelException;
import com.my.spring.exception.UserException;
import com.my.spring.pojo.Cart;
import com.my.spring.pojo.FoodItem;
import com.my.spring.pojo.Hotel;
import com.my.spring.pojo.User;
import com.my.spring.validator.FoodItemValidator;



@Controller

public class FoodItemController {
	@Autowired
	@Qualifier("foodItemDao")
	FoodItemDAO foodItemDao;
	
	@Autowired
	@Qualifier("cartDao")
	CartDAO cartDao;

	@Autowired
	@Qualifier("foodItemValidator")
	FoodItemValidator validator;

	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(validator);
	}
	@RequestMapping(value = "/hotel/addItems", method = RequestMethod.GET)
	protected ModelAndView gotToAddItems(HttpServletRequest request) throws Exception {
		
		HttpSession session = (HttpSession) request.getSession();
		Object id= session.getAttribute("id");
		session.setAttribute("id", id);
		
		return new ModelAndView("AddMenu", "foodItem", new FoodItem());
	}
	@RequestMapping(value = "/hotel/addFood", method = RequestMethod.POST)
	protected ModelAndView addFood(HttpServletRequest request,  @ModelAttribute("foodItem") FoodItem foodItem, BindingResult result) throws Exception  {
		validator.validate(foodItem, result);
		HttpSession session = (HttpSession) request.getSession();
//		if (result.hasErrors()) {
//			return new ModelAndView("user-register", "user", user);
//		}
		Hotel hotel=(Hotel) session.getAttribute("hotel");
		
		//session.setAttribute("hotel", id);
		FoodItem f = foodItemDao.add(hotel.getHotelID(),foodItem);
		
		//request.getSession().setAttribute();
		
		return new ModelAndView("add-success");
	}
	@RequestMapping(value = "/hotel/menu", method = RequestMethod.GET)
	protected ModelAndView hotelMenu(HttpServletRequest request) throws Exception {
		HttpSession session = (HttpSession) request.getSession();
		Hotel hotel=(Hotel) session.getAttribute("hotel");
		
		List<FoodItem> list= foodItemDao.getListMenu(hotel.getHotelID());
		return new ModelAndView("hotel-menu","list",list);
	}
	@RequestMapping(value = "/hotel/menu.pdf", method = RequestMethod.GET)
	protected ModelAndView viewHotelMenu(HttpServletRequest request,  @ModelAttribute("foodItem") FoodItem foodItem, BindingResult result) throws Exception  {
//		validator.validate(foodItem, result);
	HttpSession session = (HttpSession) request.getSession();
//		if (result.hasErrors()) {
//			return new ModelAndView("user-register", "user", user);
//		}
		View view = new MyView();
		//HttpSession session = (HttpSession) request.getSession();
		Hotel hotel=(Hotel) session.getAttribute("hotel");
		List<FoodItem> list= foodItemDao.getListMenu(hotel.getHotelID());
		
		//request.getSession().setAttribute("user", u);
		
		return new ModelAndView(view, "list",list);
	}
	@RequestMapping(value = "/user/menu.pdf", method = RequestMethod.GET)
	protected ModelAndView viewHotelMenu(HttpServletRequest request) throws Exception  {
//		validator.validate(foodItem, result);
//	HttpSession session = (HttpSession) request.getSession();
//		if (result.hasErrors()) {
//			return new ModelAndView("user-register", "user", user);
//		}
		View view = new MyView();
		//HttpSession session = (HttpSession) request.getSession();
		//Hotel hotel=(Hotel) session.getAttribute("hotel");
		String hotelid=request.getParameter("hotelid");
		System.out.println("hotelid"+hotelid);
		List<FoodItem> list= foodItemDao.getListMenu1(hotelid);
		//System.out.println("list"+list.get(1));
		//request.getSession().setAttribute("user", u);
		
		return new ModelAndView(view, "list",list);
	}
	@RequestMapping(value = "/user/orderItems", method = RequestMethod.POST)
	protected ModelAndView searchResult(HttpServletRequest request,  @ModelAttribute("foodItem") FoodItem foodItem, BindingResult result) throws Exception {

		HttpSession session=request.getSession();
		String hotelid=request.getParameter("hotelId");
		System.out.println("hotelId"+hotelid);
		
			List<FoodItem> foodList=foodItemDao.getListMenu1(hotelid);
			
			session.setAttribute("hotelid", hotelid);
			
			
			//request.getSession().setAttribute("hotel", hotelList);
			
			return new ModelAndView("order-foodItems","foodList", foodList);

		
	}
	@RequestMapping(value = "/user/addtoCart", method = RequestMethod.GET)
	public @ResponseBody
	String addtoCart(HttpServletRequest request) throws Exception {
		HttpSession session=request.getSession();
	
		int quantity=Integer.parseInt(request.getParameter("qty"));
		String foodId=request.getParameter("fid");
		System.out.println("Food Item"+foodId);
		System.out.println("Qty"+quantity);
        FoodItem f=foodItemDao.getFoodItem(foodId);
        User user = (User) session.getAttribute("user");
        System.out.println("User"+user.getUserID());
        boolean check=cartDao.check(foodId,user.getUserID());
        if(check){
		Cart cart=cartDao.add(f, user, quantity);
        }
        else{
        	Cart c=cartDao.getQuantity(foodId,user.getUserID());
        	int qty=c.getQuantity();
        	System.out.println("Qty"+qty);
        	qty=qty+quantity;
        	System.out.println("QtyAFterUpdate"+qty);
        	cartDao.updateQty(foodId,user.getUserID(),qty);
        }
        
			
			
			//request.getSession().setAttribute("hotel", hotelList);
			
			return "Added to Cart";

		
	}
	@RequestMapping(value = "/user/removeCart", method = RequestMethod.GET)
	public @ResponseBody
	String removeCart(HttpServletRequest request) throws Exception {
		HttpSession session=request.getSession();
	
		
		String foodId=request.getParameter("fid");
		System.out.println("Food Item"+foodId);
		
        FoodItem f=foodItemDao.getFoodItem(foodId);
        User user = (User) session.getAttribute("user");
        System.out.println("User"+user.getUserID());
        cartDao.removeCart(foodId,user.getUserID());
        
			
			
			//request.getSession().setAttribute("hotel", hotelList);
			
			return "Removed from Cart";

		
	}
	@RequestMapping(value = "/hotel/editFood", method = RequestMethod.GET)
	public @ResponseBody
	String updateFood(HttpServletRequest request) throws Exception {
		HttpSession session=request.getSession();
	
		
		String foodId=request.getParameter("foodID");
		String fname=request.getParameter("fname");
		String des=request.getParameter("des");
		String price=request.getParameter("price");
		//System.out.println("Food Item"+foodId);
		foodItemDao.updateFoodItem(foodId, fname, des, price);
        
        
			
			
			//request.getSession().setAttribute("hotel", hotelList);
			
			return "FoodItem Updated Successfully";

		
	}
	@RequestMapping(value = "/user/cart", method = RequestMethod.GET)
	protected ModelAndView getCart(HttpServletRequest request) throws Exception {
		HttpSession session = (HttpSession) request.getSession();
		User user=(User) session.getAttribute("user");
		Long id=user.getUserID();
		List<Cart> list=cartDao.getCart(id);
		return new ModelAndView("user-cart","list",list);
	}
}
