package com.gws.pargati.paypoint.model;

import java.util.List;

public class AllUsersResponse
{
    private String email_verified_at;
    private int status;
    private String user_type;
    private List<DealerDetails> dealer;
    private int dealer_commission;
    private int commission;
    private int wallet_balance;
    private String _id;
    private String email;
    private String name;
    private String password;
    private String created_at;
    private String updated_at;
    private int __v;

    public AllUsersResponse(String email_verified_at, int status, String user_type, List<DealerDetails> dealer, int dealer_commission, int commission, int wallet_balance, String _id, String email, String name, String password, String created_at, String updated_at, int __v) {
        this.email_verified_at = email_verified_at;
        this.status = status;
        this.user_type = user_type;
        this.dealer = dealer;
        this.dealer_commission = dealer_commission;
        this.commission = commission;
        this.wallet_balance = wallet_balance;
        this._id = _id;
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

    public int getStatus() {
        return status;
    }

    public String getUser_type() {
        return user_type;
    }

    public List<DealerDetails> getDealer() {
        return dealer;
    }

    public int getDealer_commission() {
        return dealer_commission;
    }

    public int getCommission() {
        return commission;
    }

    public int getWallet_balance() {
        return wallet_balance;
    }

    public String get_id() {
        return _id;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getCreated_at() {
        return created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public int get__v() {
        return __v;
    }
}
