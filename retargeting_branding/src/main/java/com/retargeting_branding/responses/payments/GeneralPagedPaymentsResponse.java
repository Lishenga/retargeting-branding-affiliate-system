package com.retargeting_branding.responses.payments;

import java.io.Serializable;
import java.util.List;

import com.retargeting_branding.models.Payments;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class GeneralPagedPaymentsResponse implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 7740401202566109148L;
    
    private Integer status;

    private String message;

    private List<Payments> data;
}