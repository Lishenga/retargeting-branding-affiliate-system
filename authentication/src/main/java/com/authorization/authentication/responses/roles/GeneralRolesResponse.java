package com.authorization.authentication.responses.roles;

import java.io.Serializable;
import java.util.List;

import com.authorization.authentication.models.Roles;

public class GeneralRolesResponse implements Serializable {

	/**
	 *
	 */
    private static final long serialVersionUID = -5258200394292847691L;
    
    private int status;

    private String message;

    private List<Roles> data;

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

    public List<Roles> getData() {
        return this.data;
    }

    public void setData(List<Roles> data) {
        this.data = data;
    }    
}