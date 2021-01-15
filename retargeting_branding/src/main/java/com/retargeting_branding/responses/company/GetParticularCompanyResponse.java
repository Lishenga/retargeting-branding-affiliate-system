package com.retargeting_branding.responses.company;

import java.io.Serializable;

import com.retargeting_branding.models.Company;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class GetParticularCompanyResponse implements Serializable {
    
    /**
	 *
	 */
	private static final long serialVersionUID = -295467760039770314L;

	private Integer status;

    private String message;

    private Company data;
}