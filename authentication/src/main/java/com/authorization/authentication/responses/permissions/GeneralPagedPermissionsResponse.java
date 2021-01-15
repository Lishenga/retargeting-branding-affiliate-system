package com.authorization.authentication.responses.permissions;

import java.io.Serializable;
import java.util.List;

import com.authorization.authentication.models.Permissions;

public class GeneralPagedPermissionsResponse implements Serializable {
    
    /**
	 *
	 */
	private static final long serialVersionUID = 7820850561752254766L;

	private int status;

    private String message;

    private List<Permissions> data;

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

    public List<Permissions> getData() {
        return this.data;
    }

    public void setData(List<Permissions> data) {
        this.data = data;
    }
}