package com.gws.pargati.paypoint.FirebaseFcm;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.gws.pargati.paypoint.Singleton.GlobalApplication;
import com.gws.pargati.paypoint.activities.LoginActivity;

public class MyFirebaseInstanceIdService extends com.google.firebase.iid.FirebaseInstanceIdService {

    private static final String TAG = "MyFirebaseIIDService";

    @Override
    public void onTokenRefresh() {

        //Getting registration token
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        String device_token = FirebaseInstanceId.getInstance().getId();

        //Displaying token in logcat
        Log.e(TAG, "Refreshed token: " + refreshedToken);

        SharedPreferences preferences = getBaseContext().getSharedPreferences("DEVICE_TOKEN",Context.MODE_PRIVATE);
        preferences.edit().putString("DTOKEN",refreshedToken).apply();




        Log.e(TAG,"Id: " + device_token);

    }

    private void sendRegistrationToServer(String token) {
        //You can implement this method to store the token on your server
        //Not required for current project
    }
}