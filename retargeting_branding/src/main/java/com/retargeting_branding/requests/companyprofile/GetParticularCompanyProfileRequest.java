package com.retargeting_branding.requests.companyprofile;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class GetParticularCompanyProfileRequest implements Serializable {

	/**
	 *
	 */
    private static final long serialVersionUID = -4903614356698888734L;
    
    @NotNull(message = "Provide companyProfileId")
    private Long companyProfileId;
}