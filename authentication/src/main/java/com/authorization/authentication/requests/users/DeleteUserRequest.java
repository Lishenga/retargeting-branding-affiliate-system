package com.authorization.authentication.requests.users;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

public class DeleteUserRequest implements Serializable {
    
    /**
	 *
	 */
    private static final long serialVersionUID = -2482466998852437395L;
    
	@NotNull(message = "Provide userId")
	private Long userId;

    public Long getUserId() {
        return this.userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}