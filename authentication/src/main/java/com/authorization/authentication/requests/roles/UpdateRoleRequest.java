package com.authorization.authentication.requests.roles;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UpdateRoleRequest implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -6444416261880962552L;
    
    @NotNull(message = "Provide roleId")
	private Long roleId;
	
	@NotNull(message = "Provide updatedBy")
    private Long updatedBy;

    private String name;
}