package com.retargeting_branding.requests.adverttype;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CreateAdvertTypeRequest implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 5634704632736298159L;
    
    @NotNull(message = "Provide advertTypeCommissionRateId")
    private Long advertTypeCommissionRateId;

    @NotNull(message = "Provide advertTypePricingId")
    private Long advertTypePricingId;

    @NotNull(message = "Provide createdBy")
    private Long createdBy;

    @NotNull(message = "Provide affiliateCommissionRate")
    private Long affiliateCommissionRate;

    @NotNull(message = "Provide priority")
    private Integer priority;

    @NotNull(message = "Provide displayCount")
    private Integer displayCount;

    @NotNull(message = "Provide displayInterval")
    private Integer displayInterval;
}