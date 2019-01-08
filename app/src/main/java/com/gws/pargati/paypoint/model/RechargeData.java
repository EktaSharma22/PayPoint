package com.gws.pargati.paypoint.model;

public class RechargeData
{
    private String _id;
    private String provider_name;
    private String provider_service;
    private String category_id;
    private String denomination_type;
    private String denomination;
    private String image;

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

    public String getProvider_service() {
        return provider_service;
    }

    public void setProvider_service(String provider_service) {
        this.provider_service = provider_service;
    }

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public String getDenomination_type() {
        return denomination_type;
    }

    public void setDenomination_type(String denomination_type) {
        this.denomination_type = denomination_type;
    }

    public String getDenomination() {
        return denomination;
    }

    public void setDenomination(String denomination) {
        this.denomination = denomination;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
