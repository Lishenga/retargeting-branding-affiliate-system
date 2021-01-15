package com.authorization.authentication.responses.roles;

import com.authorization.authentication.models.Roles;

public class CreateRoleResponse {
    
    private int status;

    private String message;

    private Roles data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Roles getData() {
        return data;
    }

    public void setData(Roles data) {
        this.data = data;
    }
}