package com.retargeting_branding.requests.users;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.retargeting_branding.enums.UsersType;

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

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Long getRoleId() {
        return this.roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public UsersType getUserType() {
        return this.userType;
    }

    public void setUserType(UsersType userType) {
        this.userType = userType;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return this.street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getState() {
        return this.state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPostalCode() {
        return this.postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
}