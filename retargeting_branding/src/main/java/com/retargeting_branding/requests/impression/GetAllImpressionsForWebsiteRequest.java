package com.retargeting_branding.requests.impression;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class GetAllImpressionsForWebsiteRequest implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 8000176012608222688L;
    
    @NotNull(message = "Provide page")
    private Integer page;

    @NotNull(message = "Provide items")
    private Integer items;

    @NotNull(message = "Provide websiteId")
    private Long websiteId;
}