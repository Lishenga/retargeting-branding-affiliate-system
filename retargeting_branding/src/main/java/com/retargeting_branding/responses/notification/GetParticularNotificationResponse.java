package com.retargeting_branding.responses.notification;

import java.io.Serializable;

import com.retargeting_branding.models.Notification;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class GetParticularNotificationResponse implements Serializable {

	/**
	 *
	 */
    private static final long serialVersionUID = 5484715166575089377L;
    
    private Integer status;

    private String message;

    private Notification data;
    
}