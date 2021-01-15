package com.authorization.authentication.responses.chat;

import java.io.Serializable;
import java.util.List;

import com.authorization.authentication.models.Chat;

public class GeneralPagedChatsResponse implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 4897143396265990352L;
    
    private int status;

    private String message;

    private List<Chat> data;

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

    public List<Chat> getData() {
        return this.data;
    }

    public void setData(List<Chat> data) {
        this.data = data;
    }
}