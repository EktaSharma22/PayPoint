package com.example.ganesha.paypoint.model;

public class ServiceData
{
    private String _id;
    private String provider_name;
    private String image;

    public ServiceData(String _id, String provider_name, String image) {
        this._id = _id;
        this.provider_name = provider_name;
        this.image = image;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getProvider_name() {
        return provider_name;
    }

    public void setProvider_name(String provider_name) {
        this.provider_name = provider_name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
