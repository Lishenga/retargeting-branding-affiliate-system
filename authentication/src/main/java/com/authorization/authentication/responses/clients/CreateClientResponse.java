package com.authorization.authentication.responses.clients;

import java.io.Serializable;

import com.authorization.authentication.models.Clients;

public class CreateClientResponse implements Serializable {
    
    /**
	 *
	 */
	private static final long serialVersionUID = -6284835158201277678L;

	private int status;

    private String message;

    private Clients data;

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

    public Clients getData() {
        return this.data;
    }

    public void setData(Clients data) {
        this.data = data;
    }
}