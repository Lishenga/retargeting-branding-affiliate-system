package com.authorization.authentication.requests.address;

import java.io.Serializable;

public class UpdateUserAddress implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 8273566771177086100L;
    
    private String country;

    private String city;

    private String street;

    private String state;

    private String postalCode;

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