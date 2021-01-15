package com.retargeting_branding.requests.companyprofile;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UpdateCompanyProfileRequest implements Serializable {

	/**
	 *
	 */
    private static final long serialVersionUID = 3687222963700753459L;

    private String street;

    private String city;

    private String state;

    private String postalCode;

    @NotNull(message = "Provide companyId")
    private Long companyId;

    private String country;

    @NotNull(message = "Provide updatedBy")
    private Long updatedBy;
    
}