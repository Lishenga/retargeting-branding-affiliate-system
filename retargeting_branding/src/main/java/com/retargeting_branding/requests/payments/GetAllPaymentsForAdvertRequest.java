package com.retargeting_branding.requests.payments;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class GetAllPaymentsForAdvertRequest implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 7748783638858757831L;
    
    @NotNull(message = "Provide page")
    private Integer page;

    @NotNull(message = "Provide items")
    private Integer items;

    @NotNull(message = "Provide advertId")
    private Long advertId;
}