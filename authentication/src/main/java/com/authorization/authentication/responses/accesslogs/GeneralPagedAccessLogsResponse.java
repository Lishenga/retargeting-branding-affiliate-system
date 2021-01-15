package com.authorization.authentication.responses.accesslogs;

import java.io.Serializable;
import java.util.List;

import com.authorization.authentication.models.AccessLogs;

public class GeneralPagedAccessLogsResponse implements Serializable {
    
    /**
	 *
	 */
	private static final long serialVersionUID = -4794053428493405504L;

	private int status;

    private String message;

    private List<AccessLogs> data;

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

    public List<AccessLogs> getData() {
        return this.data;
    }

    public void setData(List<AccessLogs> data) {
        this.data = data;
    }
}