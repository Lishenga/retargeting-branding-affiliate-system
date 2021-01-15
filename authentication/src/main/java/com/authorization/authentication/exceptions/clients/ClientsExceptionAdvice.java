package com.authorization.authentication.exceptions.clients;

import com.authorization.authentication.errors.ClientsErrors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ClientsExceptionAdvice {
    
    @ExceptionHandler(ClientsExceptionHandler.class)
	public ResponseEntity<ClientsErrors> mapException(ClientsExceptionHandler ex) {
		ClientsErrors error = new ClientsErrors(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
		return new ResponseEntity<ClientsErrors>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}