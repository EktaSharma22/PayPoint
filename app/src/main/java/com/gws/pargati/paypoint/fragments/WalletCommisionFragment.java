package com.gws.pargati.paypoint.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class WalletCommisionFragment extends Fragment implements View.OnClickListener{

    private EditText etSettingValue;
    ImageView ivBack, ivAddUsers;
    TextView tvTitle;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.wallet_commission_frag,container,false);

        ivBack = (ImageView)view.findViewById(R.id.ivBack);
        ivAddUsers = (ImageView)view.findViewById(R.id.ivAddUsers);
        tvTitle = (TextView)view.findViewById(R.id.tvTitle);

        etSettingValue = (EditText)view.findViewById(R.id.etSettingValue);

        ivAddUsers.setVisibility(View.GONE);
        tvTitle.setText("Wallet Commission");

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });

        view.findViewById(R.id.btnAddCommission).setOnClickListener(this);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
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

        SharedPreferences preferences = getActivity().getSharedPreferences("MY_APP",Context.MODE_PRIVATE);
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
                    SharedPrefSetting.getInstance(getActivity()).saveSettings(sr.getData());
                    Toast.makeText(getContext(),sr.getMessage(),Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(getContext(),"problem",Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<SettingsWalletResponse> call, Throwable t)
            {
                Toast.makeText(getContext(),"failure",Toast.LENGTH_SHORT).show();
            }
        });

    }
}