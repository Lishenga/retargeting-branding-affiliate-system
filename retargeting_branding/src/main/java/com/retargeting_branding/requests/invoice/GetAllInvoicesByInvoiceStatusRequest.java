package com.retargeting_branding.requests.invoice;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.retargeting_branding.enums.PaymentStatus;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class GetAllInvoicesByInvoiceStatusRequest implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -4301878031720717033L;
    
    @NotNull(message = "Provide page")
    private Integer page;

    @NotNull(message = "Provide items")
    private Integer items;

    @NotNull(message = "Provide invoiceStatus")
    private PaymentStatus invoiceStatus;
}