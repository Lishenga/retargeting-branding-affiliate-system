package com.retargeting_branding.requests.adevents;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class GetAllAdEventsForAdvertiserRequest implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 3098494523978460615L;
    
    @NotNull(message = "Provide page")
    private Integer page;

    @NotNull(message = "Provide items")
    private Integer items;

    @NotNull(message = "Provide advertiserId")
    private Long advertiserId;
}