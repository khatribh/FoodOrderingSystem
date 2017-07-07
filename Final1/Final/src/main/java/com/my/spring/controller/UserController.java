package com.my.spring.controller;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.SimpleEmail;
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

import com.my.spring.dao.HotelDAO;
import com.my.spring.dao.UserDAO;
import com.my.spring.exception.HotelException;
import com.my.spring.exception.UserException;
import com.my.spring.pojo.Address;
import com.my.spring.pojo.Cart;
import com.my.spring.pojo.Email;
import com.my.spring.pojo.FoodItem;
import com.my.spring.pojo.Hotel;
import com.my.spring.pojo.Order;
import com.my.spring.pojo.PhoneNo;
import com.my.spring.pojo.Photos;
import com.my.spring.pojo.User;
import com.my.spring.validator.UserValidator;

@Controller
 @RequestMapping("/user/*")
public class UserController {

	@Autowired
	@Qualifier("userDao")
	UserDAO userDao;

	@Autowired
	@Qualifier("userValidator")
	UserValidator validator;

	@Autowired
	@Qualifier("hotelDao")
	HotelDAO hotelDao;

	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(validator);
	}

	@Autowired
	ServletContext servletContext;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	protected String goToUserHome(HttpServletRequest request) throws Exception {
		return "user-profile";
	}

	@RequestMapping(value = "/user/login", method = RequestMethod.POST)
	protected ModelAndView loginUser(HttpServletRequest request) throws Exception {

		HttpSession session = (HttpSession) request.getSession();

		try {

			System.out.print("loginUser");

			User u = userDao.get(request.getParameter("username"), request.getParameter("password"));

			if (u == null) {
				System.out.println("UserName/Password does not exist");
				session.setAttribute("errorMessage", "UserName/Password does not exist");
				return new ModelAndView("error");
			}

			// session.setAttribute("user", u);

			return new ModelAndView("user-profile", "user", new User());

		} catch (UserException e) {
			System.out.println("Exception: " + e.getMessage());
			session.setAttribute("errorMessage", "error while login");
			return new ModelAndView("error");
		}

	}
	
	@RequestMapping(value = "/user/searchresult", method = RequestMethod.POST)
	protected ModelAndView searchResult(HttpServletRequest request) throws Exception {

		String hotelName = null, foodtype = null, rate = null, area = null;
		try {
			String searchFields[] = request.getParameterValues("search");
			for (String s : searchFields) {
				System.out.println("Search" + s);
				if (s.equals("hotelName")) {
					hotelName = request.getParameter("hotelName");
					System.out.println("name" + hotelName);
				}
				if (s.equals("cuisines")) {
					foodtype = request.getParameter("foodtype");
					System.out.println("food" + foodtype);
				}
				if (s.equals("rate")) {
					rate = request.getParameter("rate");
				}
				if (s.equals("location")) {
					area = request.getParameter("area");
				}
			}

			List<Hotel> hotelList = hotelDao.getSearch(hotelName, foodtype, rate, area);

			for (Hotel l : hotelList) {
				System.out.println("Values" + l.getHotelName());
			}

			// request.getSession().setAttribute("hotel", hotelList);

			return new ModelAndView("success-results", "hotelList", hotelList);

		} catch (HotelException e) {
			System.out.println("Exception: " + e.getMessage());
			return new ModelAndView("error", "errorMessage", "error");
		}
	}

	@RequestMapping(value = "/user/register", method = RequestMethod.GET)
	protected ModelAndView registerUser() throws Exception {
		System.out.print("registerUser");

		return new ModelAndView("register-user", "user", new User());

	}

	@RequestMapping(value = "/user/search", method = RequestMethod.GET)
	protected ModelAndView searchHotels() throws Exception {
		// System.out.print("registerUser");

		return new ModelAndView("search-hotels");

	}

	@RequestMapping(value = "/user/orders", method = RequestMethod.GET)
	protected ModelAndView userOrdersList(HttpServletRequest request) throws Exception {
		// System.out.print("registerUser");
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		List<Order> order = userDao.getOrders(user.getUserID());
		HashMap<Integer, ArrayList<Order>> hashmap = new HashMap<Integer, ArrayList<Order>>();
		for (Order o : order) {
			ArrayList<Order> orderList = hashmap.get(o.getOrderID());

			if (orderList == null) {
				orderList = new ArrayList<Order>();
				orderList.add(o);
				hashmap.put(o.getOrderID(), orderList);
			} else {
				orderList.add(o);
			}
		}

		return new ModelAndView("user-orders", "hashmap", hashmap);

	}

	@RequestMapping(value = "/user/photos", method = RequestMethod.GET)
	protected ModelAndView userphotos(HttpServletRequest request) throws Exception {
		// System.out.print("registerUser");
		HttpSession session = request.getSession();
		User u = (User) session.getAttribute("user");
		List<Photos> list = userDao.getPhotos(u.getUserID());

		return new ModelAndView("user-photos", "user", new User());

	}

	@RequestMapping(value = "/user/album", method = RequestMethod.GET)
	protected ModelAndView userAlbum(HttpServletRequest request) throws Exception {
		// System.out.print("registerUser");
		HttpSession session = request.getSession();
		User u = (User) session.getAttribute("user");
		List<Photos> list = userDao.getPhotos(u.getUserID());

		return new ModelAndView("user-album", "list", list);

	}

	@RequestMapping(value = "/user/updateInfo", method = RequestMethod.GET)
	protected ModelAndView updateInfo(HttpServletRequest request) throws Exception {
		// System.out.print("registerUser");
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		return new ModelAndView("user-updateinfo", "user", user);

	}

	@RequestMapping(value = "/user/addPhotos", method = RequestMethod.POST)
	protected ModelAndView addPhotos(HttpServletRequest request, @ModelAttribute("user") User user,
			BindingResult result) throws Exception {
		// System.out.print("registerUser");
		Set<Photos> multiphotos = new HashSet<Photos>();
		HttpSession session = request.getSession();

		User u = (User) session.getAttribute("user");
		user.setFirstName(u.getFirstName());

		System.out.print("no of photos:" + user.getPhotos());

		Iterator<CommonsMultipartFile> i = user.getPhoto().iterator();
		// System.out.println(i.next());
		// System.out.println(i.hasNext());
		while (i.hasNext()) {
			try {

				System.out.print("in try");
				if (user.getFirstName().trim() != "" || user.getFirstName() != null) {
					File directory;
					String check = File.separator;
					// System.out.print("in if");
					String path = null;
					if (check.equalsIgnoreCase("\\")) {
						// System.out.print("in if if");
						// C:\\SpringProjects\\FinalProj\\media\\photos
						path = servletContext.getRealPath("").replace("build\\",
								"C:\\SpringProjects\\FinalProj\\src\\main\\webapp\\resources");
					}

					if (check.equalsIgnoreCase("/")) {
						path = servletContext.getRealPath("").replace("build/",
								"C:\\SpringProjects\\FinalProj\\src\\main\\webapp\\resources");
						path += "/"; // Adding trailing slash for Mac systems.
					}
					directory = new File(path + "\\" + user.getFirstName());
					boolean temp = directory.exists();
					if (!temp) {
						// System.out.println("mkdir");
						temp = directory.mkdir();
					}
					if (temp) {
						System.out.println("photoinmim");

						// We need to transfer to a file
						CommonsMultipartFile photoInMemory = i.next();

						String fileName = photoInMemory.getOriginalFilename();
						// could generate file names as well

						File localFile = new File(
								"C:\\Users\\Bhumika\\Documents\\SpringProjects\\Final\\src\\main\\resources", fileName);

						// move the file from memory to the file

						photoInMemory.transferTo(localFile);
						// System.out.println("transfer");

						Photos photos = new Photos();

						photos.setFilename(localFile.getPath());
						System.out.println("setfilename");

						photos.setUser(u);

						userDao.registerPhotos(photos);

						multiphotos.add(photos);

						System.out.println("File is stored at" + localFile.getPath());
						System.out.print("registerNewUser");

					} else {
						System.out.println("Failed to create directory!");
					}
					// hotelDao.add(hotel);
				}
			} catch (IllegalStateException e) {
				System.out.println("*** IllegalStateException: " + e.getMessage());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("*** IOException: " + e.getMessage());
			} catch (Exception e) {
				System.out.println();
			}

		}
		return new ModelAndView("photos-user");
	}

	@RequestMapping(value = "/user/update", method = RequestMethod.GET)
	protected ModelAndView updateUser(HttpServletRequest request, @ModelAttribute("user") User user,
			BindingResult result) throws Exception {
		// System.out.print("registerUser");
		validator.validate(user, result);
		try {
			HttpSession session=request.getSession();
			System.out.println("Street" + user.getAddress().getStreetName());
			
			User u=(User) session.getAttribute("user");
			System.out.println("U"+u);
			System.out.println("UID"+u.getUserID());
			System.out.println("User ID"+user.getUserID());
			Address address = userDao.updateAddress(user, u.getUserID());

			PhoneNo phoneNo = userDao.updatePhone(user, u.getUserID());

			Email email = (Email) userDao.updateEmail(user, u.getUserID());

			System.out.println("**********" + address.getStreetName());
			user.setEmail(email);
			user.setAddress(address);
			user.setPhoneNo(phoneNo);

			userDao.update(user, u.getUserID());

			// request.getSession().setAttribute("user", u);

			return new ModelAndView("update-success");

		} catch (UserException e) {
			System.out.println("Exception: " + e.getMessage());
			return new ModelAndView("error", "errorMessage", "error while login");
		}

	}

	@RequestMapping(value = "/user/register", method = RequestMethod.POST)
	protected ModelAndView registerNewUser(HttpServletRequest request, @ModelAttribute("user") User user,
			BindingResult result) throws Exception {

		validator.validate(user, result);

		// if (result.hasErrors()) {
		// return new ModelAndView("user-register", "user", user);
		// }

		try {

			System.out.print("registerNewUser");

			boolean a=userDao.checkUsername(request.getParameter("username"));
			boolean b=userDao.checkEmail(request.getParameter("email.emailAddress"));
			if(a && b){
				User u = userDao.register(user);
			SimpleEmail email = new SimpleEmail();
			email.setHostName("smtp.googlemail.com");
			email.setSmtpPort(465);
			email.setAuthenticator(new DefaultAuthenticator("temporarywebtools2017.com", "temporary"));
			email.setSSL(true);
			email.setFrom("temporarywebtools2017@gmail.com");
			email.setSubject("Successful Registration");
			email.setMsg("Hi " + user.getFirstName() + " " + user.getLastName()
					+ "\nYou have successfully registered for our online food ordering system \n Thank You!!\n\n Team, \nFoodOrder ");
			email.addTo(user.getEmail().getEmailAddress());
			email.send();

			return new ModelAndView("register-success");
			}
			else{
				return new ModelAndView("errorRepeat");
			}
		} catch (UserException e) {
			System.out.println("Exception: " + e.getMessage());
			return new ModelAndView("error", "errorMessage", "error while login");
		}
	}

	@RequestMapping(value = "/user/orderPlaced", method = RequestMethod.GET)

	protected ModelAndView userOrders(HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession();

		// System.out.print("registerNewUser");
		User user = (User) session.getAttribute("user");
		List<Cart> cartList = userDao.addOrders(user.getUserID());
		int max = userDao.getMax();
		System.out.println("Max after function" + max);
		if (max == 0) {
			max = 1;
		} else {
			max = max + 1;
		}
		System.out.println("Order id" + max);
		for (Cart c : cartList) {
			Order o = new Order();
			o.setOrderID(max);
			o.setUser(c.getUser());
			o.setFoodItem(c.getFoodItem());
			o.setQuantity(c.getQuantity());
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = new Date();
			o.setDate(date);
			userDao.addOrderTable(o);
			userDao.deleteFromCart(c.getFoodItem().getFoodId(), c.getUser().getUserID());

		}
		SimpleEmail email = new SimpleEmail();
		email.setHostName("smtp.googlemail.com");
		email.setSmtpPort(465);
		email.setAuthenticator(new DefaultAuthenticator("temporarywebtools2017.com", "temporary"));
		email.setSSL(true);
		email.setFrom("temporarywebtools2017@gmail.com");
		email.setSubject("Order Placed");
		email.setMsg("Hi " + user.getFirstName() + " " + user.getLastName()
				+ "\nYou have successfully placed an order through our online food ordering system \n Your Order will be delivered to the following location \n"
				+ user.getAddress().getStreetName() + " " + user.getAddress().getCity() + " "
				+ user.getAddress().getZipcode() + " " + "\n\n Team, \nFoodOrder ");
		email.addTo(user.getEmail().getEmailAddress());
		email.send();
		// request.getSession().setAttribute("user", u);
		return new ModelAndView("register-success");

	}

}
