package com.authorization.authentication.requests.messages;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

public class DeleteMessageRequest implements Serializable {
    
    /**
	 *
	 */
    private static final long serialVersionUID = 5739661363336533116L;
    
    @NotNull(message = "Provide messageId")
	private Long messageId;

    public Long getMessageId() {
        return this.messageId;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }
}