package com.authorization.authentication.exceptions.token;

import com.authorization.authentication.errors.TokenErrors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class TokenExceptionAdvice {
    
    @ExceptionHandler(TokenExceptionHandler.class)
	public ResponseEntity<TokenErrors> mapException(TokenExceptionHandler ex) {
		TokenErrors error = new TokenErrors(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
		return new ResponseEntity<TokenErrors>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}