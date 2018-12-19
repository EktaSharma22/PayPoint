package com.example.ganesha.paypoint.model;

public class SettingsWalletResponse
{
    private String message;
    private WalletSettingsData data;

    public SettingsWalletResponse(String message, WalletSettingsData data) {
        this.message = message;
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public WalletSettingsData getData() {
        return data;
    }
}
