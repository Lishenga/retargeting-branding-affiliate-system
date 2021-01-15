package com.retargeting_branding.requests.advert;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.retargeting_branding.enums.Status;

import lombok.Setter;
import lombok.Getter;

@Getter @Setter
public class GetAdvertsByStatusForAdvertiserRequest implements Serializable{
    
    /**
	 *
	 */
	private static final long serialVersionUID = 4326447590611583521L;

	@NotNull(message = "Provide page")
    private Integer page;

    @NotNull(message = "Provide items")
    private Integer items;

    @NotNull(message = "Provide advertStatus")
    private Status advertStatus;

    @NotNull(message = "Provide advertiserId")
    private Long advertiserId;
}