package com.authorization.authentication.requests.accesslogs;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

public class DeleteAccessLogRequest implements Serializable {

	/**
	 *
	 */
    private static final long serialVersionUID = 8498725158683042141L;
    
    @NotNull(message = "accessLogId must be provided")
    private Long accessLogId;

    public Long getAccessLogId() {
        return this.accessLogId;
    }

    public void setAccessLogId(Long accessLogId) {
        this.accessLogId = accessLogId;
    }    
}