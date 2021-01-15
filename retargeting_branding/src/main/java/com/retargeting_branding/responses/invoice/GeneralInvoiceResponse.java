package com.retargeting_branding.responses.invoice;

import java.io.Serializable;

import com.retargeting_branding.models.Invoice;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class GeneralInvoiceResponse implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 1624988112525726571L;
    
    private Integer status;

    private String message;

    private Invoice data;
}