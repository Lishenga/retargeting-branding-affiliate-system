package com.authorization.authentication.responses.permissions;

import java.io.Serializable;

import com.authorization.authentication.models.Permissions;

public class GeneralPermissionsResponse implements Serializable {

	/**
	 *
	 */
    private static final long serialVersionUID = 6488677711259405128L;
    
    private int status;

    private String message;

    private Permissions data;

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

    public Permissions getData() {
        return this.data;
    }

    public void setData(Permissions data) {
        this.data = data;
    }    
}