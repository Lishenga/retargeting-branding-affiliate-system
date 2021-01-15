package com.authorization.authentication.responses.address;

import java.io.Serializable;

import com.authorization.authentication.models.Address;

public class GeneralAddressResponse implements Serializable {
    
    /**
	 *
	 */
	private static final long serialVersionUID = -8828335959612808685L;

	private int status;

    private String message;

    private Address data;

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Address getData() {
        return this.data;
    }

    public void setData(Address data) {
        this.data = data;
    }
}