package com.retargeting_branding.requests.invoice;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.retargeting_branding.enums.PaymentMode;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class GetAllInvoicesByPaymentModeForCompanyRequest implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 5117887581151899630L;
    
    @NotNull(message = "Provide page")
    private Integer page;

    @NotNull(message = "Provide items")
    private Integer items;

    @NotNull(message = "Provide paymentMode")
    private PaymentMode paymentMode;

    @NotNull(message = "Provide companyId")
    private Long companyId;
}