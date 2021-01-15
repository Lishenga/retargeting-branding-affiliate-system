package com.authorization.authentication.requests.chat;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

public class GetParticularChatRequest implements Serializable {
    
    /**
	 *
	 */
    private static final long serialVersionUID = 2555828615297550973L;
    
	@NotNull(message = "Provide chatId")
	private Long chatId;

    public Long getChatId() {
        return this.chatId;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }
}