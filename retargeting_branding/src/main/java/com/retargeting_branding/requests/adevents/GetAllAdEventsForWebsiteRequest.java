package com.retargeting_branding.requests.adevents;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class GetAllAdEventsForWebsiteRequest implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -1378031510853585403L;
    
    @NotNull(message = "Provide page")
    private Integer page;

    @NotNull(message = "Provide items")
    private Integer items;

    @NotNull(message = "Provide websiteId")
    private Long websiteId;
}