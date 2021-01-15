package com.authorization.authentication.requests.roles;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

public class GetParticularRoleRequest implements Serializable {
    
    /**
	 *
	 */
    private static final long serialVersionUID = 4837368888660538031L;
    
	@NotNull(message = "Provide roleId")
	private Long roleId;

    public Long getRoleId() {
        return this.roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
}