package com.sahadev.springbootin100.exceptions;

import java.util.Date;

public class CustomErrorDetails {
	private Date timestamp;
	private String message;
	private String errordetails;
	
	//Field constructor
	public CustomErrorDetails(Date timestamp, String message, String errordetails) {
		super();
		this.timestamp = timestamp;
		this.message = message;
		this.errordetails = errordetails;
	}
	//Getters
	public Date getTimestamp() {
		return timestamp;
	}
	public String getMessage() {
		return message;
	}
	public String getErrordetails() {
		return errordetails;
	}
	
	
	
	

}
