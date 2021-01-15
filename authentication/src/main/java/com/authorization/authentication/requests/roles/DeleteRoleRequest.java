package com.authorization.authentication.requests.roles;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

public class DeleteRoleRequest implements Serializable {
    
    /**
	 *
	 */
    private static final long serialVersionUID = -1324646091475970468L;
    
	@NotNull(message = "Provide roleId")
	private Long roleId;

    public Long getRoleId() {
        return this.roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

}