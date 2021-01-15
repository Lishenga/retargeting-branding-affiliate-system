package com.retargeting_branding.requests.tags;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.retargeting_branding.enums.Generation;
import com.retargeting_branding.enums.TagsStatus;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UpdateTagRequest implements Serializable {
    
    /**
	 *
	 */
	private static final long serialVersionUID = 3892332553076811269L;

	private String name;

    private String description;

    private Generation generationType;

    private TagsStatus tagStatus;

    private Long impressionId;

    private Long advertId;

    @NotNull(message = "Provide updatedBy")
    private Long updatedBy;

    @NotNull(message = "Provide tagId")
    private Long tagId;
}