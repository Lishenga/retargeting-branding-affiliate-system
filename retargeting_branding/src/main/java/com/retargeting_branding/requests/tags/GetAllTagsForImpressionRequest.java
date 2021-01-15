package com.retargeting_branding.requests.tags;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class GetAllTagsForImpressionRequest implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 4064935274130496236L;
    
    @NotNull(message = "Provide page")
    private Integer page;

    @NotNull(message = "Provide items")
    private Integer items;

    @NotNull(message = "Provide impressionId")
    private Long impressionId;
}