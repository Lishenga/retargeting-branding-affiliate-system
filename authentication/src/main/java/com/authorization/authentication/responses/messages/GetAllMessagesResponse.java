package com.authorization.authentication.responses.messages;

import java.io.Serializable;
import java.util.List;

import com.authorization.authentication.models.Messages;

public class GetAllMessagesResponse implements Serializable {
    
    /**
	 *
	 */
	private static final long serialVersionUID = 8053913006809917057L;

	private int status;

    private String message;

    private List<Messages> data;

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

    public List<Messages> getData() {
        return this.data;
    }

    public void setData(List<Messages> data) {
        this.data = data;
    }
}