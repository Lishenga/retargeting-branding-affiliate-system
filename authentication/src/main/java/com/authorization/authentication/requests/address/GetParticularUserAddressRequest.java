package com.authorization.authentication.requests.address;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.authorization.authentication.models.Users;

public class GetParticularUserAddressRequest implements Serializable {
    
    /**
	 *
	 */
    private static final long serialVersionUID = -340289721383355339L;
    
	@NotNull(message = "Provide user")
    private Users user;
    
    @NotNull(message = "Provide isDeleted")
	private Boolean isDeleted;

    public Users getUser() {
        return this.user;
    }

    public void setUser(Users user) {
        this.user = user;
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