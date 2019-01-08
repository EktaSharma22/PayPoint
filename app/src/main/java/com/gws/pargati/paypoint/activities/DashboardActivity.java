package com.gws.pargati.paypoint.activities;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.gws.pargati.paypoint.R;
import com.gws.pargati.paypoint.fragments.DealerFragment;
import com.gws.pargati.paypoint.fragments.HomeFragment;
import com.gws.pargati.paypoint.fragments.MyAccountFragment;
import com.gws.pargati.paypoint.fragments.SettingsFragment;
import com.gws.pargati.paypoint.fragments.UserFragment;
import com.gws.pargati.paypoint.storage.SharedPrefManager;

public class DashboardActivity extends AppCompatActivity implements View.OnClickListener
{
    View view;
    LinearLayout llBottomNavHome, llBottomNavUser, llBottomNavDealer, llBottomNavSettings, llBottomNavMyAccount, llBottomNavLogout;
    ImageView ivHome,ivUser,ivDealer,ivSettings,ivMyAccount,ivLogout;
    LinearLayout llBottomNavHomeUsers, llBottomNavSettingsUsers, llBottomNavMyAccountUsers, llBottomNavLogoutUsrs;
    LinearLayout llBottomNavHomeDealer, llBottomNavUser_Dealer, llBottomNavSettingsDealer, llBottomNavMyAccountDealer, llBottomNavLogoutDealer;


    View bottom_bar_admin,bottom_bar_dealer,bottom_bar_users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        llBottomNavHome = (LinearLayout)findViewById(R.id.llBottomNavHome);
        llBottomNavUser = (LinearLayout)findViewById(R.id.llBottomNavUser);
        llBottomNavDealer = (LinearLayout)findViewById(R.id.llBottomNavDealer);
        llBottomNavSettings = (LinearLayout)findViewById(R.id.llBottomNavSettings);
        llBottomNavMyAccount= (LinearLayout)findViewById(R.id.llBottomNavMyAccount);
        llBottomNavLogout = (LinearLayout)findViewById(R.id.llBottomNavLogout);
        llBottomNavHomeUsers =(LinearLayout)findViewById(R.id.llBottomNavHomeUser);
        llBottomNavSettingsUsers=(LinearLayout)findViewById(R.id.llBottomNavSettingsUser);
        llBottomNavMyAccountUsers=(LinearLayout)findViewById(R.id.llBottomNavMyAccountUser);
        llBottomNavLogoutUsrs=(LinearLayout)findViewById(R.id.llBottomNavLogoutUser);
        llBottomNavHomeDealer = (LinearLayout)findViewById(R.id.llBottomNavHomeDealer);
        llBottomNavUser_Dealer = (LinearLayout)findViewById(R.id.llBottomNavUser_Dealer);
        llBottomNavSettingsDealer = (LinearLayout)findViewById(R.id.llBottomNavSettingsDealer);
        llBottomNavMyAccountDealer = (LinearLayout)findViewById(R.id.llBottomNavMyAccountDealer);
        llBottomNavLogoutDealer = (LinearLayout)findViewById(R.id.llBottomNavLogoutDealer);

        bottom_bar_admin = (View)findViewById(R.id.bottom_bar_admin);
        bottom_bar_dealer = (View)findViewById(R.id.bottom_bar_dealer);
        bottom_bar_users = (View)findViewById(R.id.bottom_bar_users);


        ivHome = (ImageView)findViewById(R.id.ivHome);
        ivUser = (ImageView)findViewById(R.id.ivUser);
        ivDealer = (ImageView)findViewById(R.id.ivDealer);
        ivSettings = (ImageView)findViewById(R.id.ivSettings);
        ivMyAccount = (ImageView)findViewById(R.id.ivMyAccount);
        ivLogout = (ImageView)findViewById(R.id.ivLogout);


        findViewById(R.id.llBottomNavHome).setOnClickListener(this);
        findViewById(R.id.llBottomNavUser).setOnClickListener(this);
        findViewById(R.id.llBottomNavDealer).setOnClickListener(this);
        findViewById(R.id.llBottomNavSettings).setOnClickListener(this);
        findViewById(R.id.llBottomNavMyAccount).setOnClickListener(this);
        findViewById(R.id.llBottomNavLogout).setOnClickListener(this);
        findViewById(R.id.llBottomNavHomeUser).setOnClickListener(this);
        findViewById(R.id.llBottomNavSettingsUser).setOnClickListener(this);
        findViewById(R.id.llBottomNavMyAccountUser).setOnClickListener(this);
        findViewById(R.id.llBottomNavLogoutUser).setOnClickListener(this);
        findViewById(R.id.llBottomNavHomeDealer).setOnClickListener(this);
        findViewById(R.id.llBottomNavUser_Dealer).setOnClickListener(this);
        findViewById(R.id.llBottomNavSettingsDealer).setOnClickListener(this);
        findViewById(R.id.llBottomNavMyAccountDealer).setOnClickListener(this);
        findViewById(R.id.llBottomNavLogoutDealer).setOnClickListener(this);
        findViewById(R.id.llBottomNavDealer).setOnClickListener(this);

        bottomBar(view);
        displayFragment(new HomeFragment());
    }

    private void displayFragment(Fragment fragment)
    {
         getSupportFragmentManager()
                 .beginTransaction()
                 .replace(R.id.rvContainer,fragment)
                 .commit();
    }

  @Override
    protected void onStart() {
        super.onStart();

        if(!SharedPrefManager.getInstance(this).isLoggedIn())
        {
            Intent intent = new Intent(this,MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }

    //Logout User
    private void logout() {
       SharedPrefManager.getInstance(this).clear();
        Intent intent = new Intent(this,MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        if(!SharedPrefManager.getInstance(this).isLoggedIn())
        {
            Intent intent = new Intent(this,MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }

    }

    @Override
    public void onClick(View view) {
        Fragment fragment = null;
        switch (view.getId())
        {

            case R.id.llBottomNavHome:
               // ivHome.setImageResource(R.drawable.ic_asset_name_activated_home_icon);
                fragment = new HomeFragment();
                break;

            case R.id.llBottomNavUser:
                //ivUser.setImageResource(R.drawable.ic_asset_name_activated_user_icon);
                fragment  = new UserFragment();
                break;

            case R.id.llBottomNavDealer:
                fragment = new DealerFragment();
              //  ivDealer.setImageResource(R.drawable.ic_asset_name_activated_dealer_icon);
                break;

            case R.id.llBottomNavSettings:
               // ivSettings.setImageResource(R.drawable.ic_asset_name_activated_setting_icon);
                fragment = new SettingsFragment();
                break;

            case R.id.llBottomNavMyAccount:
              //  ivMyAccount.setImageResource(R.drawable.ic_asset_name_activated_user_icon);
                fragment = new MyAccountFragment();
                break;

            case R.id.llBottomNavLogout:
              //  ivLogout.setImageResource(R.drawable.ic_asset_name_activated_logout);
                logout();
                break;

            case R.id.llBottomNavHomeUser:
                //  ivLogout.setImageResource(R.drawable.ic_asset_name_activated_logout);
                fragment = new HomeFragment();
                break;

            case R.id.llBottomNavSettingsUser:
                //  ivLogout.setImageResource(R.drawable.ic_asset_name_activated_logout);
                fragment = new SettingsFragment();
                break;

            case R.id.llBottomNavMyAccountUser:
                //  ivLogout.setImageResource(R.drawable.ic_asset_name_activated_logout);
                fragment = new MyAccountFragment();
                break;

            case R.id.llBottomNavLogoutUser:
                //  ivLogout.setImageResource(R.drawable.ic_asset_name_activated_logout);
                logout();
                break;

            case R.id.llBottomNavHomeDealer:
                //  ivLogout.setImageResource(R.drawable.ic_asset_name_activated_logout);
                fragment = new HomeFragment();
                break;

            case R.id.llBottomNavSettingsDealer:
                //  ivLogout.setImageResource(R.drawable.ic_asset_name_activated_logout);
                fragment = new SettingsFragment();
                break;

            case R.id.llBottomNavMyAccountDealer:
                //  ivLogout.setImageResource(R.drawable.ic_asset_name_activated_logout);
                fragment = new MyAccountFragment();
                break;

            case R.id.llBottomNavLogoutDealer:
                //  ivLogout.setImageResource(R.drawable.ic_asset_name_activated_logout);
                logout();
                break;

            case R.id.llBottomNavUser_Dealer:
                //ivUser.setImageResource(R.drawable.ic_asset_name_activated_user_icon);
                fragment  = new UserFragment();
                break;

        }

        if(fragment != null)
        {
            displayFragment(fragment);
        }
    }


    public void bottomBar(View view)
    {
        String user_type = SharedPrefManager.getInstance(DashboardActivity.this).getUser().getUser_type();
        if(user_type.equals("user"))
        {
            bottom_bar_users.setVisibility(View.VISIBLE);
            bottom_bar_dealer.setVisibility(View.GONE);
            bottom_bar_admin.setVisibility(View.GONE);

        }
        if(user_type.equals("dealer"))
        {
            bottom_bar_users.setVisibility(View.GONE);
            bottom_bar_dealer.setVisibility(View.VISIBLE);
            bottom_bar_admin.setVisibility(View.GONE);

        }
        if(user_type.equals("admin"))
        {
            bottom_bar_users.setVisibility(View.GONE);
            bottom_bar_dealer.setVisibility(View.GONE);
            bottom_bar_admin.setVisibility(View.VISIBLE);

        }

    }
}
