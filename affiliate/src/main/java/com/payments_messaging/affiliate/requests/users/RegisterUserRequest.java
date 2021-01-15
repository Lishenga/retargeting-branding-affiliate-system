package com.payments_messaging.affiliate.requests.users;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.payments_messaging.affiliate.enums.UsersType;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class RegisterUserRequest implements Serializable{
    
    /**
	 *
	 */
	private static final long serialVersionUID = 4345219732170746181L;

	@NotNull(message = "Email address must be provided")
    private String email;

    @NotNull(message = "First Name must be provided")
    private String firstName;

    @NotNull(message = "Last Name must be provided")
    private String lastName;

    @NotNull(message = "Phone Number must be provided")
    private String phoneNumber;

    @NotNull(message = "Role ID must be provided")
    private Long roleId;

    @NotNull(message = "User Type must be provided")
    private UsersType userType;

    @NotNull(message = "Password must be provided")
    private String password;

    private String country;

    private String city;

    private String street;

    private String state;

    private String postalCode;
}