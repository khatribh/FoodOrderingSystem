package com.my.spring.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletContext;
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
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import com.my.spring.dao.FoodItemDAO;
import com.my.spring.dao.HotelDAO;
import com.my.spring.exception.HotelException;
import com.my.spring.exception.UserException;
import com.my.spring.pojo.Cart;
import com.my.spring.pojo.FoodItem;
import com.my.spring.pojo.Hotel;
import com.my.spring.pojo.Order;
import com.my.spring.pojo.Photos;
import com.my.spring.pojo.User;
import com.my.spring.validator.HotelValidator;
import com.my.spring.validator.UserValidator;


@Controller
@RequestMapping("/hotel/*")
public class HotelController {

	@Autowired
	@Qualifier("hotelDao")
	HotelDAO hotelDao;
	
	@Autowired
	@Qualifier("foodItemDao")
	FoodItemDAO foodItemDao;
	
	@Autowired
	@Qualifier("hotelValidator")
	HotelValidator validator;

	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(validator);
	}
	
	
	@Autowired
	ServletContext servletContext;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	protected ModelAndView goToHotelHome(HttpServletRequest request) throws Exception {
		HttpSession session=request.getSession();
		Hotel hotel=(Hotel) session.getAttribute("hotel");
		System.out.println("---------**----"+hotel.getHotelName());
		List<Photos> list=hotelDao.getPhotos(hotel.getHotelID());
		return new ModelAndView("hotel-profile","list",list);
	}
//	@RequestMapping(value = "/user/login", method = RequestMethod.POST)
//	protected String loginUser(HttpServletRequest request) throws Exception {
//
//		HttpSession session = (HttpSession) request.getSession();
//		
//		try {
//
//			System.out.print("loginUser");
//
//			User u = userDao.get(request.getParameter("username"), request.getParameter("password"));
//			
//			if(u == null){
//				System.out.println("UserName/Password does not exist");
//				session.setAttribute("errorMessage", "UserName/Password does not exist");
//				return "error";
//			}
//			
//			session.setAttribute("user", u);
//			
//			return "user-home";
//
//		} catch (UserException e) {
//			System.out.println("Exception: " + e.getMessage());
//			session.setAttribute("errorMessage", "error while login");
//			return "error";
//		}
//
//	}
	
//	@RequestMapping(value = "/hotel/register", method = RequestMethod.GET)
//	protected ModelAndView registerUser() throws Exception {
//		System.out.print("registerUser");
//
//		return new ModelAndView("register-user", "user", new User());
//
//	}
	@RequestMapping(value = "/hotel/photoUpload", method = RequestMethod.GET)
	protected ModelAndView moveupload(){
		return new ModelAndView("hotel-photos","hotel",new Hotel());
	}
	@RequestMapping(value = "/hotel/orderHistory", method = RequestMethod.GET)
	protected ModelAndView orderHistory(HttpServletRequest request) throws Exception{
		HttpSession session=request.getSession();
		Hotel h=(Hotel) session.getAttribute("hotel");
		List<FoodItem> food=foodItemDao.getListMenu(h.getHotelID());
		List<Order> list=new ArrayList<Order>();
		for(FoodItem f:food){
			List<Order> orderList=new ArrayList<Order>();
			orderList=foodItemDao.getOrderList(f.getFoodId());
			list.addAll(orderList);
			
		}
		//List<Order> list=hotelDao.getOrderList(h.getHotelID());
		return new ModelAndView("hotel-orders","list",list);
	}
	@RequestMapping(value = "/hotel/register", method = RequestMethod.POST)
	protected ModelAndView registerNewUser(HttpServletRequest request,  @ModelAttribute("hotel") Hotel hotel, BindingResult result) throws Exception {

		try {
			//String id=request.getParameter("hotel");
			System.out.print("registerNewUser");
			boolean a=hotelDao.checkUsername(request.getParameter("username"));
			boolean b=hotelDao.checkEmail(request.getParameter("email.emailAddress"));
			if(a && b){
				
		
			Hotel u = hotelDao.register(hotel);
			
			request.getSession().setAttribute("hotel", u);
			
			return new ModelAndView("register-success");
			}
			else{
				return new ModelAndView("errorRepeat");
			}
		} catch (HotelException e) {
			System.out.println("Exception: " + e.getMessage());
			return new ModelAndView("error", "errorMessage", "error while login");
		}
	}
	
	
	
	@RequestMapping(value = "/hotel/addPhotos", method = RequestMethod.POST)
	protected ModelAndView addHotel(HttpServletRequest request,@ModelAttribute("hotel") Hotel hotel) throws Exception {
		Set<Photos> multiphotos = new HashSet<Photos>();
		HttpSession session=request.getSession();
		
		Hotel h=(Hotel) session.getAttribute("hotel");
		hotel.setHotelName(h.getHotelName());
	    System.out.print("no of photos:"+hotel.getPhoto());
	    	
		Iterator<CommonsMultipartFile> i = hotel.getPhoto().iterator();
		//System.out.println(i.next());
		//System.out.println(i.hasNext());
		while (i.hasNext()) 
				{
			try{

				System.out.print("in try");
				if (hotel.getHotelName().trim() != "" || hotel.getHotelName() != null) {
					File directory;
					String check = File.separator; 
				//	System.out.print("in if");
					String path = null;
					if (check.equalsIgnoreCase("\\")) {
					//	System.out.print("in if if");  C:\\SpringProjects\\FinalProj\\media\\photos
						path = servletContext.getRealPath("").replace("build\\", "C:\\SpringProjects\\FinalProj\\src\\main\\webapp\\resources"); 
					}

					if (check.equalsIgnoreCase("/")) {
						path = servletContext.getRealPath("").replace("build/", "C:\\SpringProjects\\FinalProj\\src\\main\\webapp\\resources");
						path += "/"; // Adding trailing slash for Mac systems.
					}
					directory = new File(path + "\\" + hotel.getHotelName());
					boolean temp = directory.exists();
					if (!temp) {
			//			System.out.println("mkdir");
						temp = directory.mkdir();
					}
					if (temp) {
						System.out.println("photoinmim");

						// We need to transfer to a file
						CommonsMultipartFile photoInMemory = i.next();

						String fileName = photoInMemory.getOriginalFilename();
						// could generate file names as well

						File localFile = new File("C:\\Users\\Bhumika\\Documents\\SpringProjects\\Final\\src\\main\\resources", fileName);

						// move the file from memory to the file

						photoInMemory.transferTo(localFile);
				//		System.out.println("transfer");

						Photos photos = new Photos();
						
						photos.setFilename(localFile.getPath());
						System.out.println("setfilename");

						photos.setHotel(h);
						
						hotelDao.registerPhotos(photos);
						
						multiphotos.add(photos);
						
						System.out.println("File is stored at" + localFile.getPath());
						System.out.print("registerNewUser");
						

					} else {
						System.out.println("Failed to create directory!");
					}
					//hotelDao.add(hotel);
				}
			}catch (IllegalStateException e) {
		        System.out.println("*** IllegalStateException: " + e.getMessage());
		    } catch (IOException e) {
		        // TODO Auto-generated catch block
		        System.out.println("*** IOException: " + e.getMessage());
		    }
			catch(Exception e){
				System.out.println();
			}
	    
	    

}
		return new ModelAndView("photos-success");
}
	
}
