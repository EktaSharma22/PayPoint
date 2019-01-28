package com.gws.pargati.paypoint.model;

public class Balance
{
    private String message;
    private String fullClientName;
    private String DISCOUNTED_PAYMENT_AMOUNT;
    private String PAYMENT_AMOUNT;
    private String DUE_AMOUNT;
    private String DISCOUNTED_DUE_AMOUNT;
    private String ADVANCE_PAYMENT_DISCOUNTED;
    private String PAY_TYPE;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getFullClientName() {
        return fullClientName;
    }

    public void setFullClientName(String fullClientName) {
        this.fullClientName = fullClientName;
    }

    public String getDISCOUNTED_PAYMENT_AMOUNT() {
        return DISCOUNTED_PAYMENT_AMOUNT;
    }

    public void setDISCOUNTED_PAYMENT_AMOUNT(String DISCOUNTED_PAYMENT_AMOUNT) {
        this.DISCOUNTED_PAYMENT_AMOUNT = DISCOUNTED_PAYMENT_AMOUNT;
    }

    public String getPAYMENT_AMOUNT() {
        return PAYMENT_AMOUNT;
    }

    public void setPAYMENT_AMOUNT(String PAYMENT_AMOUNT) {
        this.PAYMENT_AMOUNT = PAYMENT_AMOUNT;
    }

    public String getDUE_AMOUNT() {
        return DUE_AMOUNT;
    }

    public void setDUE_AMOUNT(String DUE_AMOUNT) {
        this.DUE_AMOUNT = DUE_AMOUNT;
    }

    public String getDISCOUNTED_DUE_AMOUNT() {
        return DISCOUNTED_DUE_AMOUNT;
    }

    public void setDISCOUNTED_DUE_AMOUNT(String DISCOUNTED_DUE_AMOUNT) {
        this.DISCOUNTED_DUE_AMOUNT = DISCOUNTED_DUE_AMOUNT;
    }

    public String getADVANCE_PAYMENT_DISCOUNTED() {
        return ADVANCE_PAYMENT_DISCOUNTED;
    }

    public void setADVANCE_PAYMENT_DISCOUNTED(String ADVANCE_PAYMENT_DISCOUNTED) {
        this.ADVANCE_PAYMENT_DISCOUNTED = ADVANCE_PAYMENT_DISCOUNTED;
    }

    public String getPAY_TYPE() {
        return PAY_TYPE;
    }

    public void setPAY_TYPE(String PAY_TYPE) {
        this.PAY_TYPE = PAY_TYPE;
    }
}
