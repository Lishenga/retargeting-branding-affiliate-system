package com.retargeting_branding.requests.company;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Getter @Setter @NoArgsConstructor
public class UpdateCompanyRequest implements Serializable {
    
    /**
	 *
	 */
	private static final long serialVersionUID = 8351916521597683951L;

	@NotNull(message = "Provide companyId")
    private Long companyId;

    private String name;

    private String accountNo;

    private Long admin;

    @NotNull(message = "Provide updatedBy")
    private Long updatedBy;

    private String street;

    private String state;

    private String city;

    private String postalCode;

    private String country;
}