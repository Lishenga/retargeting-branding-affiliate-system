package com.retargeting_branding.responses.companyprofile;

import java.io.Serializable;

import com.retargeting_branding.models.CompanyProfile;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class GetParticularCompanyProfileResponse implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 5339903865447906802L;
    
    private Integer status;

    private String message;

    private CompanyProfile data;
}