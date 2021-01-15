package com.retargeting_branding.requests.notification;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UpdateNotificationRequest implements Serializable {
    
    /**
	 *
	 */
	private static final long serialVersionUID = 3795630575743889100L;

    @NotNull(message = "Provide notificationId")
    private Long notificationId;

    private String message;

    private Long userId;

    @NotNull(message = "Provide updatedBy")
    private Long updatedBy;

    private String url;
}