package com.retargeting_branding.responses.adevents;

import java.io.Serializable;

import com.retargeting_branding.models.AdEvents;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class GeneralAdEventsResponse implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 2456080278420632790L;
    
    private Integer status;

    private String message;

    private AdEvents data;
}