package com.retargeting_branding.requests.company;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class CreateCompanyRequest implements Serializable {

	/**
	 *
	 */
    private static final long serialVersionUID = 1168869692869091225L;
    
    @NotNull(message = "Provide name")
    private String name;

    @NotNull(message = "Provide accountNo")
    private String accountNo;

    @NotNull(message = "Provide admin")
    private Long admin;

    @NotNull(message = "Provide createdBy")
    private Long createdBy;

    @NotNull(message = "Provide street")
    private String street;

    @NotNull(message = "Provide state")
    private String state;

    @NotNull(message = "Provide city")
    private String city;

    @NotNull(message = "Provide postalCode")
    private String postalCode;

    @NotNull(message = "Provide country")
    private String country;
    
}