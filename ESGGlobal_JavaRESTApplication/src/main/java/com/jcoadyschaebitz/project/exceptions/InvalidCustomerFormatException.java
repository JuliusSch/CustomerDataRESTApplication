package com.jcoadyschaebitz.project.exceptions;

@SuppressWarnings("serial")
public class InvalidCustomerFormatException extends Exception {
	
	public InvalidCustomerFormatException(String exception) {
		super(exception);
	}
	
	public InvalidCustomerFormatException() {
	}
}
