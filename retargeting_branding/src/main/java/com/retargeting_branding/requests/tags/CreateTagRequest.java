package com.retargeting_branding.requests.tags;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.retargeting_branding.enums.Generation;
import com.retargeting_branding.enums.TagsStatus;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CreateTagRequest implements Serializable {
    
    /**
	 *
	 */
	private static final long serialVersionUID = -8296289628693559452L;

	@NotNull(message = "Provide name")
    private String name;

    private String description;

    @NotNull(message = "Provide generationType")
    private Generation generationType;

    @NotNull(message = "Provide tagStatus")
    private TagsStatus tagStatus;

    private Long impressionId;

    private Long advertId;

    @NotNull(message = "Provide createdBy")
    private Long createdBy;
}