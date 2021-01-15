package com.authorization.authentication.responses.roles;

import java.io.Serializable;

import com.authorization.authentication.models.Roles;

public class GetParticularRoleResponse implements Serializable {
    
    /**
	 *
	 */
	private static final long serialVersionUID = 743734351592777387L;

	private int status;

    private String message;

    private Roles data;

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

    public Roles getData() {
        return this.data;
    }

    public void setData(Roles data) {
        this.data = data;
    }
}