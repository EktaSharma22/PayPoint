package com.gws.pargati.paypoint.model;

public class User
{

    private String user_type;
    private String dealer_id;
    private String _id;
    private String email;
    private String name;
    private float wallet_balance;
    private String mobile;

    public User(String user_type, String dealer_id, String _id, String email, String name, float wallet_balance, String mobile) {
        this.user_type = user_type;
        this.dealer_id = dealer_id;
        this._id = _id;
        this.email = email;
        this.name = name;
        this.wallet_balance = wallet_balance;
        this.mobile = mobile;
    }

    public String getUser_type() {
        return user_type;
    }

    public void setUser_type(String user_type) {
        this.user_type = user_type;
    }

    public String getDealer_id() {
        return dealer_id;
    }

    public void setDealer_id(String dealer_id) {
        this.dealer_id = dealer_id;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getWallet_balance() {
        return wallet_balance;
    }

    public void setWallet_balance(float wallet_balance) {
        this.wallet_balance = wallet_balance;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
