package com.retargeting_branding.requests.advertbehavior;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class GetAdvertBehaviorForAdvertRequest implements Serializable {
    
    /**
	 *
	 */
    private static final long serialVersionUID = 5218542131675987708L;
    
	@NotNull(message = "Provide advertId")
    private Long advertId;
}