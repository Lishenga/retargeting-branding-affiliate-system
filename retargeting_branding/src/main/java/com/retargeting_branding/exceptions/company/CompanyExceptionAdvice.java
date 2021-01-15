package com.retargeting_branding.exceptions.company;

import com.retargeting_branding.errors.Errors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CompanyExceptionAdvice {
    
    @ExceptionHandler(CompanyExceptionHandler.class)
	public ResponseEntity<Errors> mapException(CompanyExceptionHandler ex) {
		Errors error = new Errors(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
		return new ResponseEntity<Errors>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}