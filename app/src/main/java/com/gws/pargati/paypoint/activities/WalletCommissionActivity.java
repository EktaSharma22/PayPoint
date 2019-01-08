package com.gws.pargati.paypoint.activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.gws.pargati.paypoint.R;
import com.gws.pargati.paypoint.api.RetrofitClient;
import com.gws.pargati.paypoint.model.SettingsWalletResponse;
import com.gws.pargati.paypoint.storage.SharedPrefSetting;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WalletCommissionActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etSettingValue;
    ImageView ivBack, ivAddUsers;
    TextView tvTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet_commission);

        ivBack = (ImageView)findViewById(R.id.ivBack);
        ivAddUsers = (ImageView)findViewById(R.id.ivAddUsers);
        tvTitle = (TextView)findViewById(R.id.tvTitle);

        etSettingValue = (EditText)findViewById(R.id.etSettingValue);

        ivAddUsers.setVisibility(View.GONE);
        tvTitle.setText("Wallet Commission");

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        findViewById(R.id.btnAddCommission).setOnClickListener(this);


    }

    @Override
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.btnAddCommission:
                walletCommission();
                break;
        }

    }

    private void walletCommission()
    {
        String setting_value = etSettingValue.getText().toString().trim();
        String setting_name = "commission";

        if(setting_value.isEmpty())
        {
            etSettingValue.setError("Cannot be empty");
            etSettingValue.requestFocus();
            return;
        }

        SharedPreferences preferences = WalletCommissionActivity.this.getSharedPreferences("MY_APP",Context.MODE_PRIVATE);
        String retrivedToken  = preferences.getString("TOKEN",null);

        Call<SettingsWalletResponse> call = RetrofitClient.getmInstance()
                .getApi()
                .addWalletCommission(retrivedToken,setting_name,setting_value);

        call.enqueue(new Callback<SettingsWalletResponse>() {
            @Override
            public void onResponse(Call<SettingsWalletResponse> call, Response<SettingsWalletResponse> response) {

                SettingsWalletResponse sr = response.body();

                if(response.code() == 200)
                {
                    SharedPrefSetting.getInstance(WalletCommissionActivity.this).saveSettings(sr.getData());
                    Toast.makeText(WalletCommissionActivity.this,sr.getMessage(),Toast.LENGTH_SHORT).show();
                }
                else
                    {
                        Toast.makeText(WalletCommissionActivity.this,"problem",Toast.LENGTH_SHORT).show();
                    }

            }

            @Override
            public void onFailure(Call<SettingsWalletResponse> call, Throwable t)
            {
                Toast.makeText(WalletCommissionActivity.this,"failure",Toast.LENGTH_SHORT).show();
            }
        });

    }
}
