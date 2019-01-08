package com.gws.pargati.paypoint.model;

public class RechargeServices
{
    private String title;
    private int image;

    public RechargeServices(int image, String title) {
        this.image = image;
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
    public int getImage() {
        return image;
    }
}

