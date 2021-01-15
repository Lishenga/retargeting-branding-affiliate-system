package com.authorization.authentication.responses.chat;

import java.io.Serializable;

import com.authorization.authentication.models.Chat;

public class GeneralChatResponse implements Serializable {
    
    /**
	 *
	 */
	private static final long serialVersionUID = 4997382993113599355L;

	private int status;

    private String message;

    private Chat data;

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

    public Chat getData() {
        return this.data;
    }

    public void setData(Chat data) {
        this.data = data;
    }
}