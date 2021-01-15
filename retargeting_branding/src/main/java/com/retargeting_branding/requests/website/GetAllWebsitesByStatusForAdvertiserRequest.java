package com.retargeting_branding.requests.website;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.retargeting_branding.enums.Status;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class GetAllWebsitesByStatusForAdvertiserRequest implements Serializable {

	/**
	 *
	 */
    private static final long serialVersionUID = -8670562365751001672L;
    
    @NotNull(message = "Provide page")
    private Integer page;

    @NotNull(message = "Provide items")
    private Integer items;

    @NotNull(message = "Provide advertStatus")
    private Status advertStatus;

    @NotNull(message = "Provide advertiserId")
    private Long advertiserId;
    
}