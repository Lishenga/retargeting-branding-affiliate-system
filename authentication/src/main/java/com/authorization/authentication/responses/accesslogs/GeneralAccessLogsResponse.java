package com.authorization.authentication.responses.accesslogs;

import java.io.Serializable;

import com.authorization.authentication.models.AccessLogs;

public class GeneralAccessLogsResponse implements Serializable {
    
    /**
	 *
	 */
	private static final long serialVersionUID = -1323670771446906274L;

	private int status;

    private String message;

    private AccessLogs data;

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

    public AccessLogs getData() {
        return this.data;
    }

    public void setData(AccessLogs data) {
        this.data = data;
    }
}