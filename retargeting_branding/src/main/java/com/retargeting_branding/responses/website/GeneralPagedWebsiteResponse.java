package com.retargeting_branding.responses.website;

import java.io.Serializable;
import java.util.List;

import com.retargeting_branding.models.Website;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class GeneralPagedWebsiteResponse implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1413601772623665598L;
    
    private Integer status;

    private String message;

    private List<Website> data;
}