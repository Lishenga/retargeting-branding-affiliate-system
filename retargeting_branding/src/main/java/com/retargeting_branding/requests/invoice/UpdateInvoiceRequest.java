package com.retargeting_branding.requests.invoice;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.retargeting_branding.enums.PaymentMode;
import com.retargeting_branding.enums.PaymentStatus;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UpdateInvoiceRequest implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 3197126635259184427L;
    
    @NotNull(message = "Provide invoiceId")
    private Long invoiceId;

    private String invoiceNo;

    private Double amount;

    private Long advertId;

    private PaymentStatus invoiceStatus;

    private Long companyId;

    private PaymentMode paymentMode;

    private Long advertTypeId;

    @NotNull(message = "Provide updatedBy")
    private Long updatedBy;
}