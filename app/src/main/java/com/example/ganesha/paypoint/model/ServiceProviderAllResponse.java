package com.example.ganesha.paypoint.model;

import java.util.List;

public class ServiceProviderAllResponse
{
    private boolean status;
    private List<ServiceCategory> data;
    private String message;

    public boolean isStatus() {
        return status;
    }

    public void setData(List<ServiceCategory> data) {
        this.data = data;
    }

    public List<ServiceCategory> getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }
}
