package com.my.spring.exception;

public class HotelException extends Exception {
	public HotelException(String message)
	{
		super("HotelException-"+message);
	}
	
	public HotelException(String message, Throwable cause)
	{
		super("HotelException-"+message,cause);
	}
	
}
