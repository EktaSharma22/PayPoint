package com.gws.pargati.paypoint.model;

public class Recharge
{
    private int amount;
    private String opration_date;
    private String transaction_id;
    private String order_number;

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getOpration_date() {
        return opration_date;
    }

    public void setOpration_date(String opration_date) {
        this.opration_date = opration_date;
    }

    public String getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(String transaction_id) {
        this.transaction_id = transaction_id;
    }

    public String getOrder_number() {
        return order_number;
    }

    public void setOrder_number(String order_number) {
        this.order_number = order_number;
    }
}
