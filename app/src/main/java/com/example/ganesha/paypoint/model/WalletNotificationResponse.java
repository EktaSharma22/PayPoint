package com.example.ganesha.paypoint.model;

import java.util.List;

public class WalletNotificationResponse {

    private boolean status;
    private String message;
    private List<WalletReqData> data;

    public WalletNotificationResponse(boolean status, String message, List<WalletReqData> data) {
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

    public List<WalletReqData> getData() {
        return data;
    }
}
