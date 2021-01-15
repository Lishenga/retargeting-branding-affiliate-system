package com.authorization.authentication.exceptions.roles;

import com.authorization.authentication.errors.RolesErrors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RolesExceptionAdvice {
    
    @ExceptionHandler(RolesExceptionHandler.class)
	public ResponseEntity<RolesErrors> mapException(RolesExceptionHandler ex) {
		RolesErrors error = new RolesErrors(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
		return new ResponseEntity<RolesErrors>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}