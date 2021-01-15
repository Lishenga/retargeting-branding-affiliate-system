package com.authorization.authentication.requests.permissions;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.authorization.authentication.models.Roles;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CreatePermissionRequest implements Serializable {
    
    /**
	 *
	 */
	private static final long serialVersionUID = 6955247173640403218L;

	@NotNull(message = "Name must be provided")
    private String name;

    @NotNull(message = "Role must be provided")
    private Roles role;
}