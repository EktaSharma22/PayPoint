package com.gws.pargati.paypoint.model;

public class RechargeStatusResponse
{
    private String message;
    private Recharge rechage_response;
    private Provider provider;

    public RechargeStatusResponse(String message, Recharge rechage_response, Provider provider) {
        this.message = message;
        this.rechage_response = rechage_response;
        this.provider = provider;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Recharge getRechage_response() {
        return rechage_response;
    }

    public void setRechage_response(Recharge rechage_response) {
        this.rechage_response = rechage_response;
    }

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }
}
