package com.retargeting_branding.requests.notification;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CreateNotificationRequest implements Serializable{
    
    /**
	 *
	 */
	private static final long serialVersionUID = 3615809249177676524L;

	@NotNull(message = "Provide message")
    private String message;

    @NotNull(message = "Provide userId")
    private Long userId;

    @NotNull(message = "Provide url")
    private String url;
}