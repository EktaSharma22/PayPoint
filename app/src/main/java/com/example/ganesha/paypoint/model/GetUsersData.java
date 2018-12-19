package com.example.ganesha.paypoint.model;

public class GetUsersData
{
    private int status;
    private String user_type;
    private int dealer_commission;
    private String name;
    private String email;
    private String _id;
    private DealerDetail dealer_id;

    public GetUsersData(int status, String user_type, int dealer_commission, String name, String email, String _id, DealerDetail dealer_id) {
        this.status = status;
        this.user_type = user_type;
        this.dealer_commission = dealer_commission;
        this.name = name;
        this.email = email;
        this._id = _id;
        this.dealer_id = dealer_id;
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

    public int getDealer_commission() {
        return dealer_commission;
    }

    public void setDealer_commission(int dealer_commission) {
        this.dealer_commission = dealer_commission;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public DealerDetail getDealer_id() {
        return dealer_id;
    }

    public void setDealer_id(DealerDetail dealer_id) {
        this.dealer_id = dealer_id;
    }

    public class DealerDetail
    {
        private String user_type;
        private String name;

        public String getUser_type() {
            return user_type;
        }

        public void setUser_type(String user_type) {
            this.user_type = user_type;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}

