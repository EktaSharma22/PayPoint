package com.gws.pargati.paypoint.model;

public class WalletApproveData
{
    private WalletUser user;
    private WalletDealer dealer;
    private WalletRequest wallet_request;

    public class WalletUser {

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

    public class WalletDealer {

        private String user_id;
        private String transaction_type;

        public String getUser_id() {
            return user_id;
        }
        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }
        public String getTransaction_type() {
            return transaction_type;
        }

        public void setTransaction_type(String transaction_type) {
            this.transaction_type = transaction_type;
        }
    }

    public class WalletRequest
    {
        private String payment_mode;

        public String getPayment_mode() {
            return payment_mode;
        }
        public void setPayment_mode(String payment_mode) {
            this.payment_mode = payment_mode;
        }

    }


}
