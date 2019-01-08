package com.gws.pargati.paypoint.Singleton;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

public class GlobalApplication  extends Application
{
    private static final String TAG = GlobalApplication.class.getSimpleName();
    private static GlobalApplication singleton;
    private String userid;
    private String device_token;

    public String getDevice_token() {
        return device_token;
    }

    public void setDevice_token(String device_token) {
        this.device_token = device_token;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }





    public static GlobalApplication getInstance() {
        return singleton;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        singleton = this;
    }


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

//    public void setConnectivityListener(ConnectivityReceiver.ConnectivityReceiverListener listener) {
//        ConnectivityReceiver.connectivityReceiverListener = listener;
//    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }


}
