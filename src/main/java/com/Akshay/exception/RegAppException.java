package com.Akshay.exception;

public class RegAppException extends RuntimeException {

	public RegAppException() {
		
	}
	
	public RegAppException(String msg) {
		super(msg);
	}
}