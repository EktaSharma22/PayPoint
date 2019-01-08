package com.gws.pargati.paypoint.model;

import com.google.gson.annotations.SerializedName;

public class DefaultResponse
{
   @SerializedName("status")
    private boolean status;

   @SerializedName("message")
    private String message;

    public DefaultResponse(boolean status, String message) {
        this.status = status;
        this.message = message;
    }

    public boolean isStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
