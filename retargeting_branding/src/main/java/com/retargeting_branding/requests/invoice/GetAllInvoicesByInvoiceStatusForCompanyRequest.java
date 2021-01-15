package com.retargeting_branding.requests.invoice;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.retargeting_branding.enums.PaymentStatus;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class GetAllInvoicesByInvoiceStatusForCompanyRequest implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 2545657938861461739L;
    
    @NotNull(message = "Provide page")
    private Integer page;

    @NotNull(message = "Provide items")
    private Integer items;

    @NotNull(message = "Provide invoiceStatus")
    private PaymentStatus invoiceStatus;

    @NotNull(message = "Provide companyId")
    private Long companyId;
}