package com.example.ganesha.paypoint.model;

public class WalletSettingsData
{
    private int status;
    private String user_id;
    private String _id;
    private String setting_name;
    private String setting_value;
    private String created_at;
    private String updated_at;
    private int __v;

    public WalletSettingsData(int status, String user_id, String _id, String setting_name, String setting_value, String created_at, String updated_at, int __v) {
        this.status = status;
        this.user_id = user_id;
        this._id = _id;
        this.setting_name = setting_name;
        this.setting_value = setting_value;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.__v = __v;
    }

    public int getStatus() {
        return status;
    }

    public String getUser_id() {
        return user_id;
    }

    public String get_id() {
        return _id;
    }

    public String getSetting_name() {
        return setting_name;
    }

    public String getSetting_value() {
        return setting_value;
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
