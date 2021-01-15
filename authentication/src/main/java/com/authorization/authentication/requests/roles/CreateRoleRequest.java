package com.authorization.authentication.requests.roles;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CreateRoleRequest {
    
    @NotNull(message = "Provide name")
    private String name;

    @NotNull(message = "Provide createdBy")
    private Long createdBy;

}