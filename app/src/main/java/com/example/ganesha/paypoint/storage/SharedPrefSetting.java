package com.example.ganesha.paypoint.storage;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.ganesha.paypoint.model.User;
import com.example.ganesha.paypoint.model.WalletSettingsData;

public class SharedPrefSetting
{
    private static final String SHARED_PREF_NAME = "my_shared_preff";
    private static SharedPrefSetting mInstance;
    private Context mCtx;

    private SharedPrefSetting(Context mCtx)
    {
        this.mCtx = mCtx;
    }

    public static synchronized SharedPrefSetting getInstance(Context mCtx)
    {
        if(mInstance == null)
        {
            mInstance = new SharedPrefSetting(mCtx);
        }
        return mInstance;
    }

    public void saveSettings(WalletSettingsData w)
    {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("status",w.getStatus());
        editor.putString("user_id",w.getUser_id());
        editor.putString("_id",w.get_id());;
        editor.putString("setting_name",w.getSetting_name());
        editor.putString("setting_value",w.getSetting_value());
        editor.putString("created_at",w.getCreated_at());
        editor.putString("updated_at",w.getUpdated_at());
        editor.putInt("__v",w.get__v());
        editor.apply();
    }


    public WalletSettingsData getSettings()
    {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        return new WalletSettingsData(
                sharedPreferences.getInt("status",-1),
                sharedPreferences.getString("user_id",null),
                sharedPreferences.getString("_id",null),
                sharedPreferences.getString("settings_name",null),
                sharedPreferences.getString("setting_value",null),
                sharedPreferences.getString("created_at",null),
                sharedPreferences.getString("updated_at",null),
                sharedPreferences.getInt("__v",-1)
        );

    }

}
