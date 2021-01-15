package com.authorization.authentication.responses.authentication;

import java.io.Serializable;

import com.authorization.authentication.models.Users;

public class UserAuthenticationResponse implements Serializable  {
    
    /**
	 *
	 */
	private static final long serialVersionUID = 1257528136267882194L;

	private int status;

    private String message;

    private String token;

    private Users data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Users getData() {
        return data;
    }

    public void setData(Users data) {
        this.data = data;
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}