package com.my.spring.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.my.spring.pojo.FoodItem;



public class FoodItemValidator implements Validator {
	public boolean supports(Class aClass) {
		return aClass.equals(FoodItem.class);
	}

	public void validate(Object obj, Errors errors) {
		FoodItem food = (FoodItem) obj;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "foodName", "error.invalid.foodName", "Food Name Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "error.invalid.description", "Description Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "price", "error.invalid.price", "Price Required");
		
		
		// check if user exists
		
	}
}
