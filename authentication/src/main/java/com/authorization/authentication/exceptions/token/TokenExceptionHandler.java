package com.authorization.authentication.exceptions.token;

public class TokenExceptionHandler extends Exception {
    
    /**
	 *
	 */
	private static final long serialVersionUID = 8714675717361741849L;

	public TokenExceptionHandler (String message) {
		super(message);
	}
}