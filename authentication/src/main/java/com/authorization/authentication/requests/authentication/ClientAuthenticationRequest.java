package com.authorization.authentication.requests.authentication;

import java.io.Serializable;
import javax.validation.constraints.NotNull;

public class ClientAuthenticationRequest implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 3751753374667219602L;

    @NotNull(message = "Client ID must be provided")
    private String clientId;

    @NotNull(message = "Client Secret must be provided")
    private String clientSecret;

    @NotNull(message = "Nickname must be provided")
    private String nickname;

    public String getClientId() {
        return this.clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return this.clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public String getNickname() {
        return this.nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public ClientAuthenticationRequest(String clientId, String clientSecret, String nickname) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.nickname = nickname;
    }
    
    //need default constructor for JSON Parsing
    public ClientAuthenticationRequest()
    {
        // Constructor
    }
}