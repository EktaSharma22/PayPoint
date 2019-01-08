package com.gws.pargati.paypoint.model;

import java.util.List;
public class UserResponse
{
    private boolean status;
    private List<GetUsersData> data;
    private String message;

    public UserResponse(boolean status, List<GetUsersData> data, String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }

    public boolean isStatus() {
        return status;
    }

    public List<GetUsersData> getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }
}