package com.retargeting_branding.requests.adverttype;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UpdateAdvertTypeRequest implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -1036398967479497115L;
    
    @NotNull(message = "Provide advertTypeId")
    private Long advertTypeId;
    
    private Long advertTypeCommissionRateId;

    private Long advertTypePricingId;

    @NotNull(message = "Provide updatedBy")
    private Long updatedBy;

    private Long affiliateCommissionRate;

    private Integer priority;

    private Integer displayCount;

    private Integer displayInterval;
}