package com.authorization.authentication.requests.messages;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

public class GetAllMessagesRequest implements Serializable {
    
    /**
	 *
	 */
	private static final long serialVersionUID = -8638528444594861224L;

    @NotNull(message = "Provide page")
	private int page;

    @NotNull(message = "Provide items")
    private int items;

    public int getPage() {
        return this.page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getItems() {
        return this.items;
    }

    public void setItems(int items) {
        this.items = items;
    }
}