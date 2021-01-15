package com.authorization.authentication.exceptions.security;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.authorization.authentication.errors.SecurityErrors;

@ControllerAdvice
public class SecurityExceptionAdvice {
    
    @ExceptionHandler(SecurityExceptionHandler.class)
	public ResponseEntity<SecurityErrors> mapException(SecurityExceptionHandler ex) {
		SecurityErrors error = new SecurityErrors(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
		return new ResponseEntity<SecurityErrors>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}