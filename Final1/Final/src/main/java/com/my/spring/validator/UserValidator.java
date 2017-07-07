package com.my.spring.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.my.spring.pojo.User;

public class UserValidator implements Validator {
	private Pattern pattern;  
	 private Matcher matcher;  
	  
	   
	 String ID_PATTERN = "[0-9]+";  
	 String STRING_PATTERN = "[a-zA-Z]+";  
	 String MOBILE_PATTERN = "[0-9]{10}"; 
	public boolean supports(Class aClass) {
		return aClass.equals(User.class);
	}

	public void validate(Object obj, Errors errors) {
		User user = (User) obj;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "error.invalid.user", "First Name Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "error.invalid.user", "Last Name Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "error.invalid.user", "User Name Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "error.invalid.password", "Password Required");
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address.streetName", "error.invalid.address.streetName",
				"Street Name Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email.emailAddress", "error.invalid.email.emailAddress",
				"Email Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address.city", "error.invalid.address.city",
				"City Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address.state", "error.invalid.address.state",
				"State Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address.zipcode", "error.invalid.address.zipcode",
				"Zipcode Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phoneNo.phoneNo", "error.invalid.phoneNo.phoneNo",
				"Phone No Required");
		
		 
		// check if user exists
		
	}
}
