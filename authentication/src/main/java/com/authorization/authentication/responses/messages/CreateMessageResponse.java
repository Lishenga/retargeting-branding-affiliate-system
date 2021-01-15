package com.authorization.authentication.responses.messages;

import java.io.Serializable;

import com.authorization.authentication.models.Messages;

public class CreateMessageResponse implements Serializable{
    
    /**
	 *
	 */
	private static final long serialVersionUID = 6692845160272139183L;

	private int status;

    private String message;

    private Messages data;

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

    public Messages getData() {
        return this.data;
    }

    public void setData(Messages data) {
        this.data = data;
    }
}