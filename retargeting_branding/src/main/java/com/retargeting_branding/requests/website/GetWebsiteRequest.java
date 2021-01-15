package com.retargeting_branding.requests.website;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class GetWebsiteRequest implements Serializable {

	/**
	 *
	 */
    private static final long serialVersionUID = -1502427484245375066L;
    
    @NotNull(message = "Provide websiteId")
    private Long websiteId;
}