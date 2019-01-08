package com.gws.pargati.paypoint.model;

public class WalletRequestResponse
{
    private boolean status;
    private String message;
    private WalletData data;

    public WalletRequestResponse(boolean status, String message, WalletData data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public boolean isStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public WalletData getData() {
        return data;
    }
}
