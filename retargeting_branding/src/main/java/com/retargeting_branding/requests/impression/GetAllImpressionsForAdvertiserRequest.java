package com.retargeting_branding.requests.impression;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class GetAllImpressionsForAdvertiserRequest implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 6001092893332420542L;
    
    @NotNull(message = "Provide page")
    private Integer page;

    @NotNull(message = "Provide items")
    private Integer items;

    @NotNull(message = "Provide advertiserId")
    private Long advertiserId;
}