package com.retargeting_branding.requests.tags;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import lombok.Setter;
import lombok.Getter;

@Getter @Setter
public class GetAllTagsForAdvertRequest implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = -6379295045532442798L;
    
    @NotNull(message = "Provide page")
    private Integer page;

    @NotNull(message = "Provide items")
    private Integer items;

    @NotNull(message = "Provide advertId")
    private Long advertId;
}