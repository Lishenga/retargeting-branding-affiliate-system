package com.authorization.authentication.requests.authentication;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserAuthenticationRequest{
    
    /**
     *
     */

    @NotNull(message = "Email address must be provided")
    private String email;

    @NotNull(message = "Password must be provided")
    private String password;
}