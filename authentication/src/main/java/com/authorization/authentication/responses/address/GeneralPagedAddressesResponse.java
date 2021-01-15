package com.authorization.authentication.responses.address;

import java.io.Serializable;
import java.util.List;

import com.authorization.authentication.models.Address;

public class GeneralPagedAddressesResponse implements Serializable {

	/**
	 *
	 */
    private static final long serialVersionUID = 8476982077423863671L;
    
    private Integer status;

    private String message;

    private List<Address> data;

    public Integer getStatus() {
        return this.status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Address> getData() {
        return this.data;
    }

    public void setData(List<Address> data) {
        this.data = data;
    }    
}