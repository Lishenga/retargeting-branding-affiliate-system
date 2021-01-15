package com.retargeting_branding.requests.website;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.retargeting_branding.enums.Status;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CreateWebsiteRequest implements Serializable {
    
    /**
	 *
	 */
	private static final long serialVersionUID = -8657843221214034272L;

	@NotNull(message = "Provide name")
    private String name;

    @NotNull(message = "Provide url")
    private String url;

    @NotNull(message = "Provide userId")
    private Long userId;

    @NotNull(message = "Provide websiteStatus")
    private Status websiteStatus;

    @NotNull(message = "Provide advertiser")
    private Long advertiser;
}