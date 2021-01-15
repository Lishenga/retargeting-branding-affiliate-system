package com.retargeting_branding.requests.website;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.retargeting_branding.enums.Status;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class GetAllWebsitesByStatusRequest implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 937214553713173565L;
    
    @NotNull(message = "Provide page")
    private Integer page;

    @NotNull(message = "Provide items")
    private Integer items;

    @NotNull(message = "Provide websiteStatus")
    private Status websiteStatus;
}