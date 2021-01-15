package com.retargeting_branding.requests.notification;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class GetParticularNotificationRequest implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 5676162854227774605L;
    
    @NotNull(message = "Provide notificationId")
    private Long notificationId;
}