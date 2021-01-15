package com.authorization.authentication.requests.chat;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CreateChatRequest implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -5912616005830255916L;
    
    @NotNull( message = "Provide userOne")
    private Long userOne;

    @NotNull( message = "Provide userTwo")
    private Long userTwo;

    @NotNull( message = "Provide initiator")
    private Long initiator;
}