package com.my.spring.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.my.spring.pojo.Hotel;
import com.my.spring.pojo.User;

public class HotelValidator implements Validator {

	public boolean supports(Class aClass) {
		return aClass.equals(Hotel.class);
	}

	public void validate(Object obj, Errors errors) {
		Hotel hotel = (Hotel) obj;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "hotelName", "error.invalid.hotel", "First Name Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cuisines", "error.invalid.hotel", "Last Name Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "error.invalid.user", "User Name Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "error.invalid.password", "Password Required");
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address.streetNo", "error.invalid.address.streetName",
				"Street No Required");
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
