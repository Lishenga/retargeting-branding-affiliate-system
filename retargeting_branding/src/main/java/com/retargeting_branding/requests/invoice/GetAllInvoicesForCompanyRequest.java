package com.retargeting_branding.requests.invoice;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class GetAllInvoicesForCompanyRequest implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -8903736336009771485L;
    
    @NotNull(message = "Provide page")
    private Integer page;

    @NotNull(message = "Provide items")
    private Integer items;

    @NotNull(message = "Provide companyId")
    private Long companyId;
}