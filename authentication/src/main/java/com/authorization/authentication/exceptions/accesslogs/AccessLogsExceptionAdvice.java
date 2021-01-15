package com.authorization.authentication.exceptions.accesslogs;

import com.authorization.authentication.errors.AccessLogsErrors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AccessLogsExceptionAdvice {
    
    @ExceptionHandler(AccessLogsExceptionHandler.class)
	public ResponseEntity<AccessLogsErrors> mapException(AccessLogsExceptionHandler ex) {
		AccessLogsErrors error = new AccessLogsErrors(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
		return new ResponseEntity<AccessLogsErrors>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}