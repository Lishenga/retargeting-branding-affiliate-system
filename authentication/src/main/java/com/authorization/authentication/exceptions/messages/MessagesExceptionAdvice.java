package com.authorization.authentication.exceptions.messages;

import com.authorization.authentication.errors.MessagesErrors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MessagesExceptionAdvice {
    
    @ExceptionHandler(MessagesExceptionHandler.class)
	public ResponseEntity<MessagesErrors> mapException(MessagesExceptionHandler ex) {
		MessagesErrors error = new MessagesErrors(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
		return new ResponseEntity<MessagesErrors>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}