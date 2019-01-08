package com.gws.pargati.paypoint.storage;

import android.content.Context;
import android.content.SharedPreferences;

import com.gws.pargati.paypoint.model.User;

public class SharedPrefManager
{
    private static final String SHARED_PREF_NAME = "my_shared_preff";
    private static SharedPrefManager mInstance;
    private Context mCtx;

    private SharedPrefManager(Context mCtx)
    {
        this.mCtx = mCtx;
    }

    public static synchronized SharedPrefManager getInstance(Context mCtx)
    {
        if(mInstance == null)
        {
            mInstance = new SharedPrefManager(mCtx);
        }
        return mInstance;
    }

    public void saveUser(User user)
    {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("user_type",user.getUser_type());
        editor.putString("dealer_id",user.getDealer_id());
        editor.putString("_id",user.get_id());
        editor.putString("email",user.getEmail());
        editor.putString("name",user.getName());
        editor.putFloat("wallet_balance",user.getWallet_balance());
        editor.putString("mobile",user.getMobile());
        editor.apply();
    }

    public boolean isLoggedIn()
    {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        return sharedPreferences.getString("_id",null) != null;
    }

    public User getUser()
    {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        return new User(
                sharedPreferences.getString("user_type",null),
                sharedPreferences.getString("dealer_id",null),
                sharedPreferences.getString("_id",null),
                sharedPreferences.getString("email",null),
                sharedPreferences.getString("name",null),
                sharedPreferences.getFloat("wallet_balance",0),
                sharedPreferences.getString("mobile",null)

        );

    }


    // Logout User
    public void clear()
    {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }

}
