package com.retargeting_branding.requests.advert;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.retargeting_branding.enums.Status;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class GetAdvertsByStatusRequest implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = -8279422755672547058L;
    
    @NotNull(message = "Provide page")
    private Integer page;

    @NotNull(message = "Provide items")
    private Integer items;

    @NotNull(message = "Provide advertStatus")
    private Status advertStatus;
}