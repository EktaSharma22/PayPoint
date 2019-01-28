package com.gws.pargati.paypoint.activities;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

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
    ImageView ivHomeUser,ivSettingsUser,ivMyAccountUser,ivLogoutUser;
    ImageView ivHomeDealer,ivUserDealer,ivSettingsDealer,ivMyAccountDealer,ivLogoutDealer;
    TextView tvHome,tvUser,tvDealer,tvSettings,tvMyAccount,tvLogout;
    TextView tvHomeDealer,tvUserDealer,tvSettingsDealer,tvMyAccountDealer,tvLogoutDealer;
    TextView tvHomeUser,tvSettingsUser,tvMyAccountUser,tvLogoutUser;

    View bottom_bar_admin,bottom_bar_dealer,bottom_bar_users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        getId();
        clickListners();
        ivHome.setImageResource(R.drawable.active_home);
        tvHome.setTextColor(getResources().getColor(R.color.colorPrimaryDark));

        ivHomeUser.setImageResource(R.drawable.active_home);
        tvHomeUser.setTextColor(getResources().getColor(R.color.colorPrimaryDark));

        ivHomeDealer.setImageResource(R.drawable.active_home);
        tvHomeDealer.setTextColor(getResources().getColor(R.color.colorPrimaryDark));

        bottomBar(view);
        displayFragment(new HomeFragment());
    }

    private void clickListners()
    {
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
    }

    private void getId()
    {
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

        tvHome = (TextView)findViewById(R.id.tvHome);
        tvUser = (TextView)findViewById(R.id.tvUser);
        tvDealer = (TextView)findViewById(R.id.tvDealer);
        tvSettings = (TextView)findViewById(R.id.tvSettings);
        tvMyAccount = (TextView)findViewById(R.id.tvMyAccount);
        tvLogout = (TextView)findViewById(R.id.tvLogout);

        tvHomeDealer = (TextView)findViewById(R.id.tvHomeDealer);
        tvUserDealer = (TextView)findViewById(R.id.tvUserDealer);
        tvSettingsDealer = (TextView)findViewById(R.id.tvSettingsDealer);
        tvMyAccountDealer = (TextView)findViewById(R.id.tvMyAccountDealer);
        tvLogoutDealer = (TextView)findViewById(R.id.tvLogoutDealer);

        tvHomeUser = (TextView)findViewById(R.id.tvHomeUser);
        tvSettingsUser = (TextView)findViewById(R.id.tvSettingsUser);
        tvMyAccountUser = (TextView)findViewById(R.id.tvMyAccountUser);
        tvLogoutUser = (TextView)findViewById(R.id.tvLogoutUser);


        ivHome = (ImageView)findViewById(R.id.ivHome);
        ivUser = (ImageView)findViewById(R.id.ivUser);
        ivDealer = (ImageView)findViewById(R.id.ivDealer);
        ivSettings = (ImageView)findViewById(R.id.ivSettings);
        ivMyAccount = (ImageView)findViewById(R.id.ivMyAccount);
        ivLogout = (ImageView)findViewById(R.id.ivLogout);

        ivHomeUser = (ImageView)findViewById(R.id.ivHomeUser);
        ivSettingsUser = (ImageView)findViewById(R.id.ivSettingsUser);
        ivMyAccountUser = (ImageView)findViewById(R.id.ivMyAccountUser);
        ivLogoutUser = (ImageView)findViewById(R.id.ivLogoutUser);


        ivHomeDealer = (ImageView)findViewById(R.id.ivHomeDealer);
        ivUserDealer = (ImageView)findViewById(R.id.ivUserDealer);
        ivSettingsDealer = (ImageView)findViewById(R.id.ivSettingsDealer);
        ivMyAccountDealer = (ImageView)findViewById(R.id.ivMyAccountDealer);
        ivLogoutDealer = (ImageView)findViewById(R.id.ivLogoutDealer);
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
                fragment = new HomeFragment();

                ivHome.setImageResource(R.drawable.active_home);
                ivUser.setImageResource(R.drawable.asset_normel_name_user_icon);
                ivDealer.setImageResource(R.drawable.asset_normel_name_dealer_icon);
                ivLogout.setImageResource(R.drawable.asset_normel_name_logout_icon);
                ivMyAccount.setImageResource(R.drawable.asset_normel_name_user_icon);
                ivSettings.setImageResource(R.drawable.asset_normel_name_setting_icon);
                tvHome.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                tvUser.setTextColor(getResources().getColor(R.color.grey));
                tvDealer.setTextColor(getResources().getColor(R.color.grey));
                tvSettings.setTextColor(getResources().getColor(R.color.grey));
                tvMyAccount.setTextColor(getResources().getColor(R.color.grey));
                tvLogout.setTextColor(getResources().getColor(R.color.grey));
                break;

            case R.id.llBottomNavUser:
                fragment  = new UserFragment();

                ivHome.setImageResource(R.drawable.asset_normel_name_home_icon);
                ivUser.setImageResource(R.drawable.ic_asset_name_activated_user_icon);
                ivDealer.setImageResource(R.drawable.asset_normel_name_dealer_icon);
                ivLogout.setImageResource(R.drawable.asset_normel_name_logout_icon);
                ivMyAccount.setImageResource(R.drawable.asset_normel_name_user_icon);
                ivSettings.setImageResource(R.drawable.asset_normel_name_setting_icon);
                tvHome.setTextColor(getResources().getColor(R.color.grey));
                tvUser.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                tvDealer.setTextColor(getResources().getColor(R.color.grey));
                tvSettings.setTextColor(getResources().getColor(R.color.grey));
                tvMyAccount.setTextColor(getResources().getColor(R.color.grey));
                tvLogout.setTextColor(getResources().getColor(R.color.grey));

                break;

            case R.id.llBottomNavDealer:
                fragment = new DealerFragment();

                ivHome.setImageResource(R.drawable.asset_normel_name_home_icon);
                ivUser.setImageResource(R.drawable.asset_normel_name_user_icon);
                ivDealer.setImageResource(R.drawable.ic_asset_name_activated_dealer_icon);
                ivLogout.setImageResource(R.drawable.asset_normel_name_logout_icon);
                ivMyAccount.setImageResource(R.drawable.asset_normel_name_user_icon);
                ivSettings.setImageResource(R.drawable.asset_normel_name_setting_icon);
                tvHome.setTextColor(getResources().getColor(R.color.grey));
                tvUser.setTextColor(getResources().getColor(R.color.grey));
                tvDealer.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                tvSettings.setTextColor(getResources().getColor(R.color.grey));
                tvMyAccount.setTextColor(getResources().getColor(R.color.grey));
                tvLogout.setTextColor(getResources().getColor(R.color.grey));


                break;

            case R.id.llBottomNavSettings:
                fragment = new SettingsFragment();

                ivHome.setImageResource(R.drawable.asset_normel_name_home_icon);
                ivUser.setImageResource(R.drawable.asset_normel_name_user_icon);
                ivDealer.setImageResource(R.drawable.asset_normel_name_dealer_icon);
                ivLogout.setImageResource(R.drawable.asset_normel_name_logout_icon);
                ivMyAccount.setImageResource(R.drawable.asset_normel_name_user_icon);
                ivSettings.setImageResource(R.drawable.ic_asset_name_activated_setting_icon);
                tvHome.setTextColor(getResources().getColor(R.color.grey));
                tvUser.setTextColor(getResources().getColor(R.color.grey));
                tvDealer.setTextColor(getResources().getColor(R.color.grey));
                tvSettings.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                tvMyAccount.setTextColor(getResources().getColor(R.color.grey));
                tvLogout.setTextColor(getResources().getColor(R.color.grey));


                break;

            case R.id.llBottomNavMyAccount:
                fragment = new MyAccountFragment();

                ivHome.setImageResource(R.drawable.asset_normel_name_home_icon);
                ivUser.setImageResource(R.drawable.asset_normel_name_user_icon);
                ivDealer.setImageResource(R.drawable.asset_normel_name_dealer_icon);
                ivLogout.setImageResource(R.drawable.asset_normel_name_logout_icon);
                ivMyAccount.setImageResource(R.drawable.ic_asset_name_activated_user_icon);
                ivSettings.setImageResource(R.drawable.asset_normel_name_setting_icon);
                tvHome.setTextColor(getResources().getColor(R.color.grey));
                tvUser.setTextColor(getResources().getColor(R.color.grey));
                tvDealer.setTextColor(getResources().getColor(R.color.grey));
                tvSettings.setTextColor(getResources().getColor(R.color.grey));
                tvMyAccount.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                tvLogout.setTextColor(getResources().getColor(R.color.grey));

                break;

            case R.id.llBottomNavLogout:
                logout();

                ivHome.setImageResource(R.drawable.asset_normel_name_home_icon);
                ivUser.setImageResource(R.drawable.asset_normel_name_user_icon);
                ivDealer.setImageResource(R.drawable.asset_normel_name_dealer_icon);
                ivLogout.setImageResource(R.drawable.ic_asset_name_activated_logout);
                ivMyAccount.setImageResource(R.drawable.asset_normel_name_user_icon);
                ivSettings.setImageResource(R.drawable.asset_normel_name_setting_icon);
                tvHome.setTextColor(getResources().getColor(R.color.grey));
                tvUser.setTextColor(getResources().getColor(R.color.grey));
                tvDealer.setTextColor(getResources().getColor(R.color.grey));
                tvSettings.setTextColor(getResources().getColor(R.color.grey));
                tvMyAccount.setTextColor(getResources().getColor(R.color.grey));
                tvLogout.setTextColor(getResources().getColor(R.color.colorPrimaryDark));

                break;

            case R.id.llBottomNavHomeUser:
                 fragment = new HomeFragment();

                ivHomeUser.setImageResource(R.drawable.active_home);
                ivLogoutUser.setImageResource(R.drawable.asset_normel_name_logout_icon);
                ivMyAccountUser.setImageResource(R.drawable.asset_normel_name_user_icon);
                ivSettingsUser.setImageResource(R.drawable.asset_normel_name_setting_icon);

                tvHomeUser.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                tvSettingsUser.setTextColor(getResources().getColor(R.color.grey));
                tvMyAccountUser.setTextColor(getResources().getColor(R.color.grey));
                tvLogoutUser.setTextColor(getResources().getColor(R.color.grey));
                break;

            case R.id.llBottomNavSettingsUser:
                fragment = new SettingsFragment();

                ivHomeUser.setImageResource(R.drawable.asset_normel_name_home_icon);
                ivLogoutUser.setImageResource(R.drawable.asset_normel_name_logout_icon);
                ivMyAccountUser.setImageResource(R.drawable.asset_normel_name_user_icon);
                ivSettingsUser.setImageResource(R.drawable.ic_asset_name_activated_setting_icon);

                tvHomeUser.setTextColor(getResources().getColor(R.color.grey));
                tvSettingsUser.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                tvMyAccountUser.setTextColor(getResources().getColor(R.color.grey));
                tvLogoutUser.setTextColor(getResources().getColor(R.color.grey));

                break;

            case R.id.llBottomNavMyAccountUser:
                fragment = new MyAccountFragment();

                ivHomeUser.setImageResource(R.drawable.asset_normel_name_home_icon);
                ivLogoutUser.setImageResource(R.drawable.asset_normel_name_logout_icon);
                ivMyAccountUser.setImageResource(R.drawable.ic_asset_name_activated_user_icon);
                ivSettingsUser.setImageResource(R.drawable.asset_normel_name_setting_icon);

                tvHomeUser.setTextColor(getResources().getColor(R.color.grey));
                tvSettingsUser.setTextColor(getResources().getColor(R.color.grey));
                tvMyAccountUser.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                tvLogoutUser.setTextColor(getResources().getColor(R.color.grey));

                break;

            case R.id.llBottomNavLogoutUser:
                logout();
                ivHomeUser.setImageResource(R.drawable.asset_normel_name_home_icon);
                ivLogoutUser.setImageResource(R.drawable.ic_asset_name_activated_logout);
                ivMyAccountUser.setImageResource(R.drawable.asset_normel_name_user_icon);
                ivSettingsUser.setImageResource(R.drawable.asset_normel_name_setting_icon);

                tvHomeUser.setTextColor(getResources().getColor(R.color.grey));
                tvSettingsUser.setTextColor(getResources().getColor(R.color.grey));
                tvMyAccountUser.setTextColor(getResources().getColor(R.color.grey));
                tvLogoutUser.setTextColor(getResources().getColor(R.color.colorPrimaryDark));

                break;

            case R.id.llBottomNavHomeDealer:
                fragment = new HomeFragment();
                ivHomeDealer.setImageResource(R.drawable.active_home);
                ivUserDealer.setImageResource(R.drawable.asset_normel_name_user_icon);
                ivLogoutDealer.setImageResource(R.drawable.asset_normel_name_logout_icon);
                ivMyAccountDealer.setImageResource(R.drawable.asset_normel_name_user_icon);
                ivSettingsDealer.setImageResource(R.drawable.asset_normel_name_setting_icon);

                tvHomeDealer.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                tvUserDealer.setTextColor(getResources().getColor(R.color.grey));
                tvSettingsDealer.setTextColor(getResources().getColor(R.color.grey));
                tvMyAccountDealer.setTextColor(getResources().getColor(R.color.grey));
                tvLogoutDealer.setTextColor(getResources().getColor(R.color.grey));
                break;

            case R.id.llBottomNavSettingsDealer:
                fragment = new SettingsFragment();
                ivHomeDealer.setImageResource(R.drawable.asset_normel_name_home_icon);
                ivUserDealer.setImageResource(R.drawable.asset_normel_name_user_icon);
                ivLogoutDealer.setImageResource(R.drawable.asset_normel_name_logout_icon);
                ivMyAccountDealer.setImageResource(R.drawable.asset_normel_name_user_icon);
                ivSettingsDealer.setImageResource(R.drawable.ic_asset_name_activated_setting_icon);

                tvHomeDealer.setTextColor(getResources().getColor(R.color.grey));
                tvUserDealer.setTextColor(getResources().getColor(R.color.grey));
                tvSettingsDealer.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                tvMyAccountDealer.setTextColor(getResources().getColor(R.color.grey));
                tvLogoutDealer.setTextColor(getResources().getColor(R.color.grey));

                break;

            case R.id.llBottomNavMyAccountDealer:
                fragment = new MyAccountFragment();
                ivHomeDealer.setImageResource(R.drawable.asset_normel_name_home_icon);
                ivUserDealer.setImageResource(R.drawable.asset_normel_name_user_icon);
                ivLogoutDealer.setImageResource(R.drawable.asset_normel_name_logout_icon);
                ivMyAccountDealer.setImageResource(R.drawable.ic_asset_name_activated_user_icon);
                ivSettingsDealer.setImageResource(R.drawable.asset_normel_name_setting_icon);

                tvHomeDealer.setTextColor(getResources().getColor(R.color.grey));
                tvUserDealer.setTextColor(getResources().getColor(R.color.grey));
                tvSettingsDealer.setTextColor(getResources().getColor(R.color.grey));
                tvMyAccountDealer.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                tvLogoutDealer.setTextColor(getResources().getColor(R.color.grey));


                break;

            case R.id.llBottomNavLogoutDealer:
                logout();

                ivHomeDealer.setImageResource(R.drawable.asset_normel_name_home_icon);
                ivUserDealer.setImageResource(R.drawable.asset_normel_name_user_icon);
                ivLogoutDealer.setImageResource(R.drawable.ic_asset_name_activated_logout);
                ivMyAccountDealer.setImageResource(R.drawable.asset_normel_name_user_icon);
                ivSettingsDealer.setImageResource(R.drawable.asset_normel_name_setting_icon);

                tvHomeDealer.setTextColor(getResources().getColor(R.color.grey));
                tvUserDealer.setTextColor(getResources().getColor(R.color.grey));
                tvSettingsDealer.setTextColor(getResources().getColor(R.color.grey));
                tvMyAccountDealer.setTextColor(getResources().getColor(R.color.grey));
                tvLogoutDealer.setTextColor(getResources().getColor(R.color.colorPrimaryDark));


                break;

            case R.id.llBottomNavUser_Dealer:
                fragment  = new UserFragment();

                ivHomeDealer.setImageResource(R.drawable.asset_normel_name_home_icon);
                ivUserDealer.setImageResource(R.drawable.ic_asset_name_activated_user_icon);
                ivLogoutDealer.setImageResource(R.drawable.asset_normel_name_logout_icon);
                ivMyAccountDealer.setImageResource(R.drawable.asset_normel_name_user_icon);
                ivSettingsDealer.setImageResource(R.drawable.asset_normel_name_setting_icon);

                tvHomeDealer.setTextColor(getResources().getColor(R.color.grey));
                tvUserDealer.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                tvSettingsDealer.setTextColor(getResources().getColor(R.color.grey));
                tvMyAccountDealer.setTextColor(getResources().getColor(R.color.grey));
                tvLogoutDealer.setTextColor(getResources().getColor(R.color.grey));

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
