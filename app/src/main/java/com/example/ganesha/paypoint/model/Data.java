package com.example.ganesha.paypoint.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data
{
    @SerializedName("user_type")
    @Expose
    private String user_type;

    @SerializedName("dealer")
    @Expose
    private String dealer;

    public String getUser_type() {
        return user_type;
    }

    public void setUser_type(String user_type) {
        this.user_type = user_type;
    }

    public String getDealer() {
        return dealer;
    }

    public Data(String user_type, String dealer) {
        this.user_type = user_type;
        this.dealer = dealer;
    }

    public void setDealer(String dealer) {

        this.dealer = dealer;
    }
}
