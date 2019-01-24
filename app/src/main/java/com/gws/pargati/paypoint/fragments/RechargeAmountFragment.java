package com.gws.pargati.paypoint.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.gws.pargati.paypoint.R;
import com.gws.pargati.paypoint.Singleton.GlobalApplication;
import com.gws.pargati.paypoint.api.RetrofitClient;
import com.gws.pargati.paypoint.model.AccessDetails;
import com.gws.pargati.paypoint.model.RechargeCheckResponse;
import com.gws.pargati.paypoint.model.UploadUserTypeResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RechargeAmountFragment extends Fragment implements View.OnClickListener
{
    Spinner spinnerAmt;
    TextView tvMobileNumber,tvProviderName;
    ImageView ivRechargeIcon;
    LinearLayout llAmountEdit, llAmountDrop;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recharge_amount,container,false);
        spinnerAmt = (Spinner)view.findViewById(R.id.spinnerAmt);
        tvMobileNumber = (TextView)view.findViewById(R.id.tvMobileNumber);
        tvProviderName = (TextView)view.findViewById(R.id.tvProviderName);
        ivRechargeIcon = (ImageView)view.findViewById(R.id.ivRechargeIcon);
        llAmountEdit = (LinearLayout)view.findViewById(R.id.llAmountEdit);
        llAmountDrop = (LinearLayout)view.findViewById(R.id.llAmountDrop);
        submitMobile();
        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onClick(View view)
    {
    }

    private void submitMobile()
    {
        SharedPreferences preferences = getActivity().getSharedPreferences("MY_APP", Context.MODE_PRIVATE);
        String retrivedToken  = preferences.getString("TOKEN",null);

        SharedPreferences preferences1 = getContext().getSharedPreferences("MY_CAT_ID",Context.MODE_PRIVATE);
        String service_provider  = preferences1.getString("CATEGORYID",null);

        SharedPreferences pref = getContext().getSharedPreferences("MOBILE",Context.MODE_PRIVATE);
        String mobile_number  = pref.getString("MOBILE",null);

        Call<RechargeCheckResponse> call = RetrofitClient.getmInstance()
                .getApi()
                .rechargeCheck(retrivedToken,mobile_number,service_provider);

        call.enqueue(new Callback<RechargeCheckResponse>() {
            @Override
            public void onResponse(Call<RechargeCheckResponse> call, Response<RechargeCheckResponse> response) {

                RechargeCheckResponse lr = response.body();

                if(response.code() == 200) {
                    String message = lr.getMessage();
                    String mobile = lr.getMobile();
                    String provider_name = lr.getData().getProvider_name();
                    String image = lr.getData().getImage();

                    tvMobileNumber.setText(mobile);
                    tvProviderName.setText(provider_name);
                    Glide.with(getContext()).load(image)
                            .placeholder(R.drawable.loading).into(ivRechargeIcon);

                    List<String> amount = lr.getAmount();
                    for (int index=0; index < amount.size();index++)
                    {
                        Log.i("Value of element "+index,amount.get(index));
                    }

                    if (amount.size()<3)
                    {
                        llAmountDrop.setVisibility(View.GONE);
                        llAmountEdit.setVisibility(View.VISIBLE);
                    }
                    else
                    {
                       llAmountDrop.setVisibility(View.VISIBLE);
                       llAmountEdit.setVisibility(View.GONE);
                    }

                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), R.layout.spinner_item, amount);
                    adapter.setDropDownViewResource(R.layout.spinner_item);
                    spinnerAmt.setAdapter(adapter);

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
