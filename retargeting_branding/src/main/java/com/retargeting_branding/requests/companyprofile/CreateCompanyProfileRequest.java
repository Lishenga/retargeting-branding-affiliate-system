package com.retargeting_branding.requests.companyprofile;

import java.io.Serializable;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class CreateCompanyProfileRequest implements Serializable {
    
    /**
	 *
	 */
	private static final long serialVersionUID = -2626060177196736187L;

	@NotNull(message = "Provide street")
    private String street;

    @NotNull(message = "Provide city")
    private String city;

    @NotNull(message = "Provide state")
    private String state;

    @NotNull(message = "Provide postalCode")
    private String postalCode;

    @NotNull(message = "Provide companyId")
    private Long companyId;

    @NotNull(message = "Provide country")
    private String country;

    @NotNull(message = "Provide userId")
    private Long userId;
}