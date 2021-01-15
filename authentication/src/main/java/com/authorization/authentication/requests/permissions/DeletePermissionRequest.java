package com.authorization.authentication.requests.permissions;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

public class DeletePermissionRequest implements Serializable {
    
    /**
	 *
	 */
    private static final long serialVersionUID = 3182758677139357812L;
    
	@NotNull(message = "Provide permissionId")
	private Long permissionId;

    public Long getPermissionId() {
        return this.permissionId;
    }

    public void setPermissionId(Long permissionId) {
        this.permissionId = permissionId;
    }
}