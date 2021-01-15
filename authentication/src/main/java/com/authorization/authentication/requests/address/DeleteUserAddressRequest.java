package com.authorization.authentication.requests.address;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

public class DeleteUserAddressRequest implements Serializable {
    
    /**
	 *
	 */
    private static final long serialVersionUID = 7296691832277781626L;
    
	@NotNull(message = "userId must be provided")
    private Long userId;

    public Long getUserId() {
        return this.userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}