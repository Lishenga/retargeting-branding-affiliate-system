package com.authorization.authentication.requests;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

public class GeneralPagedRequest implements Serializable {
    
    /**
	 *
	 */
	private static final long serialVersionUID = -6487671289690827077L;

	@NotNull(message = "Provide page")
	private Integer page;

    @NotNull(message = "Provide items")
    private Integer items;

    @NotNull(message = "Provide isDeleted")
    private Boolean isDeleted;

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