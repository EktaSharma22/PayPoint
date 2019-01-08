package com.gws.pargati.paypoint.model;

import java.util.List;

public class GetAllUsersResponse
{
    private boolean status;
    private List<AllUsersResponse> data;
    private String message;

    public GetAllUsersResponse(boolean status, List<AllUsersResponse> data, String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }

    public boolean isStatus() {
        return status;
    }

    public List<AllUsersResponse> getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }
}
