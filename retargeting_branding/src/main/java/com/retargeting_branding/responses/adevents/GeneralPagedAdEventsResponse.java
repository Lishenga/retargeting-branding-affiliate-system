package com.retargeting_branding.responses.adevents;

import java.io.Serializable;
import java.util.List;

import com.retargeting_branding.models.AdEvents;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class GeneralPagedAdEventsResponse implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1806467984694465932L;
    
    private Integer status;

    private String message;

    private List<AdEvents> data;
}