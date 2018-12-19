package com.example.ganesha.paypoint.model;

import java.util.List;

public class UploadUserTypeResponse
{
    private boolean status;
    private List<DealerUsers> data;
    private String message;
    private CurrentUser currentUser;

    public UploadUserTypeResponse(boolean status, List<DealerUsers> data, String message, CurrentUser currentUser) {
        this.status = status;
        this.data = data;
        this.message = message;
        this.currentUser = currentUser;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public List<DealerUsers> getData() {
        return data;
    }

    public void setData(List<DealerUsers> data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public CurrentUser getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(CurrentUser currentUser) {
        this.currentUser = currentUser;
    }

    public static class DealerUsers
    {
       private String user_type;
       private String _id;
       private DealerId dealer_id;
       private String email;
       private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUser_type() {
            return user_type;
        }

        public void setUser_type(String user_type) {
            this.user_type = user_type;
        }

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public DealerId getDealer_id() {
            return dealer_id;
        }

        public void setDealer_id(DealerId dealer_id) {
            this.dealer_id = dealer_id;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }
    }

    public class CurrentUser
    {
        private String user_type;
        private String email;
        private String name;

        public String getUser_type() {
            return user_type;
        }

        public void setUser_type(String user_type) {
            this.user_type = user_type;
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

    public class DealerId
    {
        private String user_type;
        private String email;
        private String name;

        public String getUser_type() {
            return user_type;
        }

        public void setUser_type(String user_type) {
            this.user_type = user_type;
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
