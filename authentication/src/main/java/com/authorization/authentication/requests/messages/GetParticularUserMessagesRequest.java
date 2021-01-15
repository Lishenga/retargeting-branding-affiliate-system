package com.authorization.authentication.requests.messages;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

public class GetParticularUserMessagesRequest implements Serializable {
    
    /**
	 *
	 */
	private static final long serialVersionUID = -3246975411706889065L;

    @NotNull(message = "Provide page")
	private int page;

    @NotNull(message = "Provide items")
    private int items;

    @NotNull(message = "Provide userId")
    private Long userId;

    @NotNull(message = "Provide isDeleted")
    private Boolean isDeleted;

    public Boolean getIsDeleted() {
        return this.isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Long getUserId() {
        return this.userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

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
}