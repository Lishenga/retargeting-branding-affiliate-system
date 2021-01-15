package com.retargeting_branding.responses.invoice;

import java.io.Serializable;
import java.util.List;

import com.retargeting_branding.models.Invoice;

import lombok.Setter;
import lombok.Getter;

@Getter @Setter
public class GeneralPagedInvoicesResponse implements Serializable {
    
    /**
	 *
	 */
	private static final long serialVersionUID = 5219920789963333068L;

	private Integer status;

    private String message;

    private List <Invoice> data;
}