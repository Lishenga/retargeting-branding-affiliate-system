package com.authorization.authentication.exceptions.address;

import com.authorization.authentication.errors.AddressErrors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AddressExceptionAdvice {
    
    @ExceptionHandler(AddressExceptionHandler.class)
	public ResponseEntity<AddressErrors> mapException(AddressExceptionHandler ex) {
		AddressErrors error = new AddressErrors(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
		return new ResponseEntity<AddressErrors>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}