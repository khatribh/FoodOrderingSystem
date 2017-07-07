package com.my.spring.exception;

public class FoodItemException extends Exception{
	
		public FoodItemException(String message)
		{
			super("FoodException-"+message);
		}
		
		public FoodItemException(String message, Throwable cause)
		{
			super("FoodException-"+message,cause);
		}
		
}
