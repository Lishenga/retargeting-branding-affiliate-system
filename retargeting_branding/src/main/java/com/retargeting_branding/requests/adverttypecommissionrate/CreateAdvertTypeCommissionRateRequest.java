package com.retargeting_branding.requests.adverttypecommissionrate;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CreateAdvertTypeCommissionRateRequest implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -3216218434307232468L;

    @NotNull(message = "Provide pricePerClick")
    private Double pricePerClick;

    @NotNull(message = "Provide priceForUserOnSiteTime")
    private Double priceForUserOnSiteTime;

    @NotNull(message = "Provide createdBy")
    private Long createdBy;
}