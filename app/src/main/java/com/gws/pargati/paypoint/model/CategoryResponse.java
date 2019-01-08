package com.gws.pargati.paypoint.model;

import java.util.List;

public class CategoryResponse
{
    private boolean status;
    private List<Category> data;
    private String message;

    public boolean isStatus() {
        return status;
    }

    public void setData(List<Category> data) {
        this.data = data;
    }

    public List<Category> getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }
}
