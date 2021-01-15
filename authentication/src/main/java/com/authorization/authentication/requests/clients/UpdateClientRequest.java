package com.authorization.authentication.requests.clients;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UpdateClientRequest implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 2112290291567527018L;
  
    @NotNull(message = "Provide id")
    private Long id;

    private String clientId;

    private String clientSecret;

    private String nickName;
}