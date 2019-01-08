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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.gws.pargati.paypoint.R;
import com.gws.pargati.paypoint.api.RetrofitClient;
import com.gws.pargati.paypoint.model.RechargeCheckResponse;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ServiceDetailFragment extends Fragment implements View.OnClickListener{

    private EditText etMobile;
    private Button btnSubmitMobile;
    ImageView ivBack, ivAddUsers;
    TextView tvTitle, tvEnterMobile;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.service_detail_frag,container,false);
        etMobile = (EditText)view.findViewById(R.id.etMobile);
        btnSubmitMobile = (Button)view.findViewById(R.id.btnSubmitMobile);

        ivBack = (ImageView)view.findViewById(R.id.ivBack);
        ivAddUsers = (ImageView)view.findViewById(R.id.ivAddUsers);
        tvTitle = (TextView)view.findViewById(R.id.tvTitle);
        tvEnterMobile = (TextView)view.findViewById(R.id.tvEnterMobile);

        ivAddUsers.setVisibility(View.GONE);
        tvTitle.setText("Mobile Number");

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });

        view.findViewById(R.id.btnSubmitMobile).setOnClickListener(this);


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
            case R.id.btnSubmitMobile:
                submitMobile();
                break;
        }
    }

    private void submitMobile()
    {
        String mobile = etMobile.getText().toString().trim();

        if(mobile.isEmpty())
        {
            etMobile.setError("Mobile Number is required");
            etMobile.requestFocus();
            return;
        }

        if(mobile.length() < 10)
        {
            etMobile.setError("Enter a valid Mobile Number");
            etMobile.requestFocus();
            return;
        }

        SharedPreferences preferences = getActivity().getSharedPreferences("MY_APP",Context.MODE_PRIVATE);
        String retrivedToken  = preferences.getString("TOKEN",null);

        SharedPreferences preferences1 = getContext().getSharedPreferences("MY_CAT_ID",Context.MODE_PRIVATE);
        String service_provider  = preferences1.getString("CATEGORYID",null);

        String mobile_number = etMobile.getText().toString().trim();
        if(mobile_number.isEmpty())
        {
            etMobile.setError("Please Enter Mobile Number to proceed further");
            etMobile.requestFocus();
            return;
        }

        Call<RechargeCheckResponse> call = RetrofitClient.getmInstance()
                .getApi()
                .rechargeCheck(retrivedToken,mobile_number,service_provider);

        call.enqueue(new Callback<RechargeCheckResponse>() {
            @Override
            public void onResponse(Call<RechargeCheckResponse> call, Response<RechargeCheckResponse> response) {

                RechargeCheckResponse lr = response.body();

                if(response.code() == 200) {
                    String message = lr.getMessage();
                    Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
                }
                else if (response.code() == 500)
                    {
                        Toast.makeText(getContext(),"Please check mobile number.",Toast.LENGTH_SHORT).show();
                    }
                    else
                        {
                            Toast.makeText(getContext(),"Something Went Wrong",Toast.LENGTH_SHORT).show();
                        }


            }

            @Override
            public void onFailure(Call<RechargeCheckResponse> call, Throwable t) {
                Toast.makeText(getContext(),"Failure",Toast.LENGTH_SHORT).show();
            }
        });

    }


}

