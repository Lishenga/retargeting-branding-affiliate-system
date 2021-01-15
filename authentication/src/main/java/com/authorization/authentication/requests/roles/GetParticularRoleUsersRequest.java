package com.authorization.authentication.requests.roles;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

public class GetParticularRoleUsersRequest implements Serializable {
    
    /**
	 *
	 */
	private static final long serialVersionUID = 4314782068383151917L;

	@NotNull(message = "Provide page")
	private Integer page;

    @NotNull(message = "Provide items")
    private Integer items;

    @NotNull(message = "Provide roleId")
    private Long roleId;

    @NotNull(message = "Provide isDeleted")
    private Boolean isDeleted;

    public Boolean getIsDeleted() {
        return this.isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted= isDeleted;
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

    public Long getRoleId() {
        return this.roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
}