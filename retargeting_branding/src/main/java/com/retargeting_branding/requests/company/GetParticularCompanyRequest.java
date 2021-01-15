package com.retargeting_branding.requests.company;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class GetParticularCompanyRequest implements Serializable {
    
    /**
	 *
	 */
    private static final long serialVersionUID = 129209688246536248L;
    
	private Long id;
}