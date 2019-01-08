package com.gws.pargati.paypoint.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Category
{
    private String _id;
    private String created_at;
    private String updated_at;
    private String category_name;

    @SerializedName("image")
    @Expose
    private String image;
    private int __v;

    public Category() {
    }

    public Category(String _id, String created_at, String updated_at, String category_name, String image, int __v) {
        this._id = _id;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.category_name = category_name;
        this.image = image;
        this.__v = __v;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
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

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int get__v() {
        return __v;
    }

    public void set__v(int __v) {
        this.__v = __v;
    }
}
