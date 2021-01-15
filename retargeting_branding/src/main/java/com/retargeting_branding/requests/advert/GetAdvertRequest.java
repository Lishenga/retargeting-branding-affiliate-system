package com.retargeting_branding.requests.advert;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class GetAdvertRequest implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -1139115253073075638L;
    
    @NotNull(message = "Provide advertId")
    private Long advertId;
}