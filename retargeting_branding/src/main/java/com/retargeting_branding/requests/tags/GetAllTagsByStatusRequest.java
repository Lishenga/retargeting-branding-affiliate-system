package com.retargeting_branding.requests.tags;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.retargeting_branding.enums.TagsStatus;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class GetAllTagsByStatusRequest implements Serializable {
    
    /**
	 *
	 */
	private static final long serialVersionUID = 1341792071614267885L;

	@NotNull(message = "Provide page")
    private Integer page;

    @NotNull(message = "Provide items")
    private Integer items;

    @NotNull(message = "Provide tagsStatus")
    private TagsStatus tagsStatus;
}