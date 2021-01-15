package com.authorization.authentication.responses.authentication;

import java.io.Serializable;

public class AuthenticationResponse implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -3866875349074342632L;
    
    private final String jwt;

    public AuthenticationResponse(String jwt) {
        this.jwt = jwt;
    }

    public String getJwt() {
        return jwt;
    }
}