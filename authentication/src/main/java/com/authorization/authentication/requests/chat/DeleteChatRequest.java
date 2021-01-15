package com.authorization.authentication.requests.chat;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

public class DeleteChatRequest implements Serializable {
    
    /**
	 *
	 */
    private static final long serialVersionUID = 6862039834179933487L;
    
	@NotNull(message = "Provide chatId")
	private Long chatId;

    public Long getChatId() {
        return this.chatId;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }
}