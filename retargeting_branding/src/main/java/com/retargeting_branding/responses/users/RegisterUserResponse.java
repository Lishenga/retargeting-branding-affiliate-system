package com.retargeting_branding.responses.users;

import java.io.Serializable;

public class RegisterUserResponse implements Serializable{
    
    /**
	 *
	 */
	private static final long serialVersionUID = -8151757319916890159L;

	private int status;

    private String message;

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}