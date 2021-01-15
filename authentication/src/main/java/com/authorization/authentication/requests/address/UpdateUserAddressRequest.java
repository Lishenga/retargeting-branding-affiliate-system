package com.authorization.authentication.requests.address;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UpdateUserAddressRequest implements Serializable {

	/**
	 *
	 */
    private static final long serialVersionUID = 8375080789504396511L;
    
    @NotNull(message = "Provide userId")
    private Long userId;
    
    private String city;

    private String country;

    private String state;

    private String postalCode;

    private String street;

}