package com.retargeting_branding.requests.adverttypepricing;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.retargeting_branding.enums.PricingMode;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UpdateAdvertTypePricingRequest implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -4236437292479858404L;

    private Long advertTypeId;

    @NotNull(message = "Provide advertTypePricingId")
    private Long advertTypePricingId;

    private PricingMode pricingMode;

    private Double amount;

    private Integer period;

    @NotNull(message = "Provide updatedBy")
    private Long updatedBy;
}