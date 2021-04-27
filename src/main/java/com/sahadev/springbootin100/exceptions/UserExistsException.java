package com.sahadev.springbootin100.exceptions;

public class UserExistsException extends Exception{

	private static final long serialVersionUID = 1L;

	public UserExistsException(String message) {
		super(message);
	}
	

}
