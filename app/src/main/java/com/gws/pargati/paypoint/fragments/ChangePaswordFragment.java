package com.gws.pargati.paypoint.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.gws.pargati.paypoint.R;
import com.gws.pargati.paypoint.activities.LoginActivity;
import com.gws.pargati.paypoint.activities.RegisterActivity;
import com.gws.pargati.paypoint.api.RetrofitClient;
import com.gws.pargati.paypoint.model.SettingsWalletResponse;
import com.gws.pargati.paypoint.storage.SharedPrefSetting;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangePaswordFragment extends Fragment implements View.OnClickListener{

    ImageView ivBack, ivAddUsers;
    TextView tvTitle;
    EditText etCurrentPassword,etNewPassword,etConfirmPassword;
    Button buttonChangePassword;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.change_password_frag,container,false);

        ivBack = (ImageView)view.findViewById(R.id.ivBack);
        ivAddUsers = (ImageView)view.findViewById(R.id.ivAddUsers);
        tvTitle = (TextView)view.findViewById(R.id.tvTitle);
        etCurrentPassword = (EditText)view.findViewById(R.id.etCurrentPassword);
        etNewPassword = (EditText)view.findViewById(R.id.etNewPassword);
        etConfirmPassword = (EditText)view.findViewById(R.id.etConfirmPassword);
        buttonChangePassword = (Button)view.findViewById(R.id.buttonChangePassword);

        ivAddUsers.setVisibility(View.GONE);
        tvTitle.setText("Change Password");

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });
        buttonChangePassword.setOnClickListener(this);

        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

       /* tvId.setText(String.valueOf(SharedPrefManager.getInstance(getActivity()).getUser().getId()));
        tvName.setText(SharedPrefManager.getInstance(getActivity()).getUser().getName());
        tvEmail.setText(SharedPrefManager.getInstance(getActivity()).getUser().getEmail());*/

    }

    @Override
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.buttonChangePassword:
                changePassword();
                break;
        }

    }

    private void changePassword()
    {
        SharedPreferences preferences = getActivity().getSharedPreferences("MY_APP", Context.MODE_PRIVATE);
        String retrivedToken  = preferences.getString("TOKEN",null);

        String current_password = etCurrentPassword.getText().toString();
        String new_password = etNewPassword.getText().toString();
        String confirm_password = etConfirmPassword.getText().toString();

        if(current_password.isEmpty())
        {
            etCurrentPassword.setError("Cannot be empty");
            etCurrentPassword.requestFocus();
            return;
        }

        if(new_password.isEmpty())
        {
            etNewPassword.setError("Cannot be empty");
            etNewPassword.requestFocus();
            return;
        }

        if(confirm_password.isEmpty())
        {
            etConfirmPassword.setError("Cannot be empty");
            etConfirmPassword.requestFocus();
            return;
        }

        if(!new_password.equals(confirm_password))
        {
            Toast.makeText(getContext(),"New Password and Confirm Password should be the same",Toast.LENGTH_SHORT).show();
        }
        else
            {
                Call<ResponseBody> call = RetrofitClient.getmInstance()
                        .getApi()
                        .changePassword(retrivedToken,current_password,confirm_password);

                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                        if(response.code() == 200)
                        {
                            String s = null;
                            try {
                                if (response.code() == 200)
                                { s = response.body().string();
                                }
                                else
                                { s = response.errorBody().string(); } }
                            catch (IOException e)
                            {
                                e.printStackTrace();
                            }

                            if (s != null)
                            {
                                try {
                                    JSONObject jsonObject = new JSONObject(s);
                                    Toast.makeText(getContext(),jsonObject.getString("message"),Toast.LENGTH_SHORT).show();
                                }
                                catch (JSONException e)
                                {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t)
                    {
                        Toast.makeText(getContext(),"failure",Toast.LENGTH_SHORT).show();
                    }
                });

            }
    }


}

