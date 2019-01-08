package com.gws.pargati.paypoint.model;

import java.util.List;

public class RechargeCheckResponse
{
    private String message;
    private RechargeData data;
    private List<String> amount;
    private String mobile;

    public RechargeCheckResponse(String message, RechargeData data, List<String> amount, String mobile) {
        this.message = message;
        this.data = data;
        this.amount = amount;
        this.mobile = mobile;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public RechargeData getData() {
        return data;
    }

    public void setData(RechargeData data) {
        this.data = data;
    }

    public List<String> getAmount() {
        return amount;
    }

    public void setAmount(List<String> amount) {
        this.amount = amount;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
