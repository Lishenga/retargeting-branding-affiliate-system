package com.retargeting_branding.requests.adverttypecommissionrate;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UpdateAdvertTypeCommissionRateRequest implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -1046250821921223837L;

    private Long advertTypeId;

    @NotNull(message = "Provide advertTypeCommissionRateId")
    private Long advertTypeCommissionRateId;

    private Double pricePerClick;

    private Double priceForUserOnSiteTime;

    @NotNull(message = "Provide updatedBy")
    private Long updatedBy;
}