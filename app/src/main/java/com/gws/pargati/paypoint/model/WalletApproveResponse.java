package com.gws.pargati.paypoint.model;

public class WalletApproveResponse
{
    private boolean status;
    private String message;
    private WalletApproveData data;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public WalletApproveData getData() {
        return data;
    }

    public void setData(WalletApproveData data) {
        this.data = data;
    }
}
