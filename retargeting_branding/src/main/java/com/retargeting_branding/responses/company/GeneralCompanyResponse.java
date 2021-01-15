package com.retargeting_branding.responses.company;

import java.io.Serializable;
import java.util.List;

import com.retargeting_branding.models.Company;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class GeneralCompanyResponse implements Serializable{
    
    /**
	 *
	 */
	private static final long serialVersionUID = -3336968091856081265L;

	private Integer status;

    private String message;

    private List<Company> data;
}