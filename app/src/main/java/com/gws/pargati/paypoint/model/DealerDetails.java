package com.gws.pargati.paypoint.model;

public class DealerDetails
{
    private String email_verified_at;
    private int status;
    private String user_type;
    private String dealer;
    private int dealer_commission;
    private int commission;
    private int wallet_balance;
    private String _id;
    private String dealer_id;
    private String email;
    private String name;
    private String password;
    private String created_at;
    private String updated_at;
    private int __v;

    public DealerDetails(String email_verified_at, int status, String user_type, String dealer, int dealer_commission, int commission, int wallet_balance, String _id, String dealer_id, String email, String name, String password, String created_at, String updated_at, int __v) {
        this.email_verified_at = email_verified_at;
        this.status = status;
        this.user_type = user_type;
        this.dealer = dealer;
        this.dealer_commission = dealer_commission;
        this.commission = commission;
        this.wallet_balance = wallet_balance;
        this._id = _id;
        this.dealer_id = dealer_id;
        this.email = email;
        this.name = name;
        this.password = password;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.__v = __v;
    }

    public String getEmail_verified_at() {
        return email_verified_at;
    }

    public void setEmail_verified_at(String email_verified_at) {
        this.email_verified_at = email_verified_at;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getUser_type() {
        return user_type;
    }

    public void setUser_type(String user_type) {
        this.user_type = user_type;
    }

    public String getDealer() {
        return dealer;
    }

    public void setDealer(String dealer) {
        this.dealer = dealer;
    }

    public int getDealer_commission() {
        return dealer_commission;
    }

    public void setDealer_commission(int dealer_commission) {
        this.dealer_commission = dealer_commission;
    }

    public int getCommission() {
        return commission;
    }

    public void setCommission(int commission) {
        this.commission = commission;
    }

    public int getWallet_balance() {
        return wallet_balance;
    }

    public void setWallet_balance(int wallet_balance) {
        this.wallet_balance = wallet_balance;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getDealer_id() {
        return dealer_id;
    }

    public void setDealer_id(String dealer_id) {
        this.dealer_id = dealer_id;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public int get__v() {
        return __v;
    }

    public void set__v(int __v) {
        this.__v = __v;
    }
}
