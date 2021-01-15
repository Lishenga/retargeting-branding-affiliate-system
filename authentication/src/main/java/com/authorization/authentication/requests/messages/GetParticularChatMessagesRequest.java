package com.authorization.authentication.requests.messages;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.authorization.authentication.models.Chat;

public class GetParticularChatMessagesRequest implements Serializable {
    
    /**
	 *
	 */
	private static final long serialVersionUID = 7320527942036494491L;

	@NotNull(message = "Provide page")
	private int page;

    @NotNull(message = "Provide items")
    private int items;

    @NotNull(message = "Provide Chat")
    private Chat chat;

    @NotNull(message = "Provide isDeleted")
    private Boolean isDeleted;

    public int getPage() {
        return this.page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getItems() {
        return this.items;
    }

    public void setItems(int items) {
        this.items = items;
    }

    public Chat getChat() {
        return this.chat;
    }

    public void setChat(Chat chat) {
        this.chat = chat;
    }

    public Boolean isIsDeleted() {
        return this.isDeleted;
    }

    public Boolean getIsDeleted() {
        return this.isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }
}