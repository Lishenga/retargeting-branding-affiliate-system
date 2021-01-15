package com.authorization.authentication.requests.permissions;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UpdatePermissionsRequest implements Serializable {
    
    /**
	 *
	 */
	private static final long serialVersionUID = 230752542873489798L;

	@NotNull(message = "Provide permissionId")
    private Long permissionId;

    private String name;

    private Long roleId;

    @NotNull(message = "Provide userId")
    private Long userId;
}