package com.retargeting_branding.responses.notification;

import java.io.Serializable;
import java.util.List;

import com.retargeting_branding.models.Notification;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class GeneralNotificationResponse implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -8288151499346606566L;
    
    private Integer status;

    private String message;

    private List<Notification> data;
}