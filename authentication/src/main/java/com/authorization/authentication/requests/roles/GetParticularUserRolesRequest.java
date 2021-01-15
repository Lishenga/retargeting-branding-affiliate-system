package com.authorization.authentication.requests.roles;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

public class GetParticularUserRolesRequest implements Serializable {
    
    /**
	 *
	 */
    private static final long serialVersionUID = -6225053198612432306L;
    
	@NotNull(message = "Provide userId")
	private Long userId;

    public Long getUserId() {
        return this.userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}