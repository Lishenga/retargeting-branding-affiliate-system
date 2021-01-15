package com.authorization.authentication.requests.chat;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

public class GetParticularUserChatsRequest implements Serializable {
    
    /**
	 *
	 */
	private static final long serialVersionUID = 1644598579446749094L;

	@NotNull(message = "Provide userId")
    private Long userId;
    
    @NotNull(message = "Provide Page")
    private Integer page;
    
    @NotNull(message = "Provide items")
    private Integer items;
    
    @NotNull(message = "Provide isDeleted")
	private Boolean isDeleted;

    public Long getUserId() {
        return this.userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getPage() {
        return this.page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getItems() {
        return this.items;
    }

    public void setItems(Integer items) {
        this.items = items;
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