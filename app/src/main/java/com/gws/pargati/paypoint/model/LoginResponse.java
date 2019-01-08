package com.gws.pargati.paypoint.model;

public class LoginResponse
{
    private boolean status;
    private String message;
    private String token;
    private User user;

    public LoginResponse(boolean status, String message, String token, User user) {
        this.status = status;
        this.message = message;
        this.token = token;
        this.user = user;
    }

    public boolean isStatus()
    {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public String getToken() {
        return token;
    }

    public User getUser() {
        return user;
    }
}
