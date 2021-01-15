package com.retargeting_branding.responses.companyprofile;

import java.io.Serializable;
import java.util.List;

import com.retargeting_branding.models.CompanyProfile;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class GeneralPagedCompanyProfileResponse implements Serializable {

	/**
	 *
	 */
    private static final long serialVersionUID = 4945475956449398707L;
    
    private Integer status;

    private String message;

    private List<CompanyProfile> data;
}