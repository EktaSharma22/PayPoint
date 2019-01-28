package com.gws.pargati.paypoint.model;

import java.util.List;

public class WorldlinkResponse
{
    private boolean status;
    private Balance balance;
    private List<WorldLinkData> data;

    public WorldlinkResponse(boolean status, Balance balance, List<WorldLinkData> data) {
        this.status = status;
        this.balance = balance;
        this.data = data;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Balance getBalance() {
        return balance;
    }

    public void setBalance(Balance balance) {
        this.balance = balance;
    }

    public List<WorldLinkData> getData() {
        return data;
    }

    public void setData(List<WorldLinkData> data) {
        this.data = data;
    }
}
