package com.example.ganesha.paypoint.model;

public class AddUserResponse
{
    private boolean status;
    private String message;
    private AddUser user;

    public AddUserResponse(boolean status, String data, AddUser user) {
        this.status = status;
        this.message = data;
        this.user = user;
    }

    public boolean isStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public AddUser getUser() {
        return user;
    }

    public class AddUser {
        private String user_type;
        private String dealer_id;
        private String _id;
        private String email;
        private String name;

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
    }

}

