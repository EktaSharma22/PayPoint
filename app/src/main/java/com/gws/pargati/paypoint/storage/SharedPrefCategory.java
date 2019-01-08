package com.gws.pargati.paypoint.storage;

import android.content.Context;
import android.content.SharedPreferences;

import com.gws.pargati.paypoint.model.Category;

public class SharedPrefCategory
{
    private static final String SHARED_PREF_NAME = "shared_preff_category";
    private static SharedPrefCategory mInstance;
    private Context mCtx;

    private SharedPrefCategory(Context mCtx)
    {
        this.mCtx = mCtx;
    }

    public static synchronized SharedPrefCategory getInstance(Context mCtx)
    {
        if(mInstance == null)
        {
            mInstance = new SharedPrefCategory(mCtx);
        }
        return mInstance;
    }

    public void saveCategory(Category category)
    {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("_id",category.get_id());
        editor.putString("created_at",category.getCreated_at());
        editor.putString("updated_at",category.getUpdated_at());
        editor.putString("category_name",category.getCategory_name());
        editor.putString("image",category.getImage());
        editor.putInt("__v",category.get__v());
        editor.apply();
    }


    public Category getCategory()
    {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        return new Category(
                sharedPreferences.getString("_id",null),
                sharedPreferences.getString("created_at",null),
                sharedPreferences.getString("updated_at",null),
                sharedPreferences.getString("category_name",null),
                sharedPreferences.getString("image",null),
                sharedPreferences.getInt("__v",-1)
        );

    }

}
