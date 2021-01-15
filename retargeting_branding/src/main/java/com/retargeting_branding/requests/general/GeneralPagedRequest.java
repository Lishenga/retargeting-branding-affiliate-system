package com.retargeting_branding.requests.general;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import lombok.Setter;
import lombok.Getter;

@Getter @Setter
public class GeneralPagedRequest implements Serializable{
    
    /**
	 *
	 */
	private static final long serialVersionUID = 5864718394872618097L;

	@NotNull(message = "Provide page")
    private Integer page;

    @NotNull(message = "Provide items")
    private Integer items;

    @NotNull(message = "Provide isDeleted")
    private Boolean isDeleted;
}