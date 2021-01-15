package com.retargeting_branding.requests.payments;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class GetAllPaymentsForCompanyRequest implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 8576593987883343686L;
    
    @NotNull(message = "Provide page")
    private Integer page;

    @NotNull(message = "Provide items")
    private Integer items;

    @NotNull(message = "Provide companyId")
    private Long companyId;
}