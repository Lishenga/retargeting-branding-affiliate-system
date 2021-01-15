package com.authorization.authentication.exceptions.permissions;

import com.authorization.authentication.errors.PermissionsErrors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class PermissionsExceptionAdvice {
    
    @ExceptionHandler(PermissionsExceptionHandler.class)
	public ResponseEntity<PermissionsErrors> mapException(PermissionsExceptionHandler ex) {
		PermissionsErrors error = new PermissionsErrors(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
		return new ResponseEntity<PermissionsErrors>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}