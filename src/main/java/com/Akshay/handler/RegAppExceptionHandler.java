package com.Akshay.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.Akshay.exception.RegAppException;

@RestControllerAdvice
public class RegAppExceptionHandler {

	@ExceptionHandler(value=RegAppException.class)
	public ResponseEntity<AppError> handelAppException(RegAppException regAppException){
		 
		AppError error = new AppError();
		
		error.setErrorCode("REGAPP101");
		error.setErrorMsg(regAppException.getMessage());
		
		ResponseEntity< AppError> entity = new ResponseEntity<AppError>(error, HttpStatus.INTERNAL_SERVER_ERROR);
		
		return entity;
		
	}
}
