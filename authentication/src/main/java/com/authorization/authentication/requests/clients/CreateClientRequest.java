package com.authorization.authentication.requests.clients;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor
public class CreateClientRequest implements Serializable {
    
    /**
	 *
	 */
	private static final long serialVersionUID = -7972696825903762617L;

	@NotNull(message = "Client ID must be provided")
    private String clientId;

    @NotNull(message = "Client Secret must be provided")
    private String clientSecret;

    private String nickName;
}