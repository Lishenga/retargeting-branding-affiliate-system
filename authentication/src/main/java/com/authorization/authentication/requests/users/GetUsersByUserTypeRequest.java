package com.authorization.authentication.requests.users;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.authorization.authentication.enums.UsersType;

public class GetUsersByUserTypeRequest implements Serializable {
    
    /**
	 *
	 */
	private static final long serialVersionUID = -7520698400249011813L;

	@NotNull(message = "Provide page")
	private Integer page;

    @NotNull(message = "Provide items")
    private Integer items;

    @NotNull(message = "Provide isDeleted")
    private Boolean isDeleted;

    @NotNull(message = "Provide userType")
    private UsersType userType;

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

    public UsersType getUserType() {
        return this.userType;
    }

    public void setUserType(UsersType userType) {
        this.userType = userType;
    }
}