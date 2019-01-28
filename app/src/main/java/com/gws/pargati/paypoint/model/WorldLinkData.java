package com.gws.pargati.paypoint.model;

public class WorldLinkData
{
    private String message;
    private String fullClientName;
    private String title;
    private String OPTION_ID;
    private String VOLUME;
    private String RATE;
    private String DISCOUNTED_RATE;

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOPTION_ID() {
        return OPTION_ID;
    }

    public void setOPTION_ID(String OPTION_ID) {
        this.OPTION_ID = OPTION_ID;
    }

    public String getVOLUME() {
        return VOLUME;
    }

    public void setVOLUME(String VOLUME) {
        this.VOLUME = VOLUME;
    }

    public String getRATE() {
        return RATE;
    }

    public void setRATE(String RATE) {
        this.RATE = RATE;
    }

    public String getDISCOUNTED_RATE() {
        return DISCOUNTED_RATE;
    }

    public void setDISCOUNTED_RATE(String DISCOUNTED_RATE) {
        this.DISCOUNTED_RATE = DISCOUNTED_RATE;
    }
}
