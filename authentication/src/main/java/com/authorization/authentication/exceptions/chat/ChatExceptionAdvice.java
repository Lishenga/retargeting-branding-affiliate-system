package com.authorization.authentication.exceptions.chat;

import com.authorization.authentication.errors.ChatErrors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ChatExceptionAdvice {
    
    @ExceptionHandler(ChatExceptionHandler.class)
	public ResponseEntity<ChatErrors> mapException(ChatExceptionHandler ex) {
		ChatErrors error = new ChatErrors(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
		return new ResponseEntity<ChatErrors>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}