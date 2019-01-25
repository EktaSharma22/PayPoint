package com.gws.pargati.paypoint.fragments;

import android.content.Context;
import android.content.Intent;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.gws.pargati.paypoint.R;
import com.gws.pargati.paypoint.Singleton.GlobalApplication;
import com.gws.pargati.paypoint.api.RetrofitClient;
import com.gws.pargati.paypoint.model.RechargeCheckResponse;
import com.gws.pargati.paypoint.model.RechargeStatusResponse;
import com.gws.pargati.paypoint.model.UploadUserTypeResponse;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ServiceDetailFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    private EditText etMobile;
    private Button btnSubmitMobile,btnRecharge;
    ImageView ivBack, ivAddUsers;
    TextView tvTitle, tvEnterMobile;
    private LinearLayout llEnterMobile,llRechargeDetails;
    Spinner spinnerAmt;
    TextView tvMobileNumber,tvProviderName;
    ImageView ivRechargeIcon;
    LinearLayout llAmountEdit, llAmountDrop;

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
        llEnterMobile = (LinearLayout)view.findViewById(R.id.llEnterMobile);
        llRechargeDetails = (LinearLayout)view.findViewById(R.id.llRechargeDetails);
        spinnerAmt = (Spinner)view.findViewById(R.id.spinnerAmt);
        tvMobileNumber = (TextView)view.findViewById(R.id.tvMobileNumber);
        tvProviderName = (TextView)view.findViewById(R.id.tvProviderName);
        ivRechargeIcon = (ImageView)view.findViewById(R.id.ivRechargeIcon);
        llAmountEdit = (LinearLayout)view.findViewById(R.id.llAmountEdit);
        llAmountDrop = (LinearLayout)view.findViewById(R.id.llAmountDrop);
        ivBack = (ImageView)view.findViewById(R.id.ivBack);
        ivAddUsers = (ImageView)view.findViewById(R.id.ivAddUsers);
        tvTitle = (TextView)view.findViewById(R.id.tvTitle);
        btnRecharge = (Button)view.findViewById(R.id.btnRecharge);

        SharedPreferences preferences = getActivity().getSharedPreferences("IMAGE",Context.MODE_PRIVATE);
        String image  = preferences.getString("IMAGEID",null);
        Glide.with(getContext()).load(image)
                .placeholder(R.drawable.loading).into(ivRechargeIcon);

        SharedPreferences prefs = getActivity().getSharedPreferences("NAME",Context.MODE_PRIVATE);

        String provider_name = prefs.getString("PNAME",null);
        tvProviderName.setText(provider_name);

        ivAddUsers.setVisibility(View.GONE);
        tvTitle.setText("Recharge Details");

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });

        view.findViewById(R.id.btnSubmitMobile).setOnClickListener(this);
        view.findViewById(R.id.btnRecharge).setOnClickListener(this);
        spinnerAmt.setOnItemSelectedListener(this);


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

            case R.id.btnRecharge:
                status();
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();
        SharedPreferences p = getActivity().getSharedPreferences("AMOUNT",Context.MODE_PRIVATE);
        p.edit().putString("AMT",item).apply();
    }
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }

    private void status()
    {
        SharedPreferences preferences = getActivity().getSharedPreferences("MY_APP",Context.MODE_PRIVATE);
        String retrivedToken  = preferences.getString("TOKEN",null);

        SharedPreferences pref = getActivity().getSharedPreferences("MOBILE",Context.MODE_PRIVATE);
        String mobile = pref.getString("MOBILE",null);

        SharedPreferences prefs = getActivity().getSharedPreferences("SERVICEPROVIDER",Context.MODE_PRIVATE);
        String service_provider = prefs.getString("S_ID",null);

        SharedPreferences p = getActivity().getSharedPreferences("AMOUNT",Context.MODE_PRIVATE);
        int amt = Integer.parseInt(p.getString("AMT",null));

        Call<RechargeStatusResponse> call = RetrofitClient.getmInstance()
                .getApi()
                .recharge(retrivedToken,mobile,service_provider,amt);

        call.enqueue(new Callback<RechargeStatusResponse>() {
            @Override
            public void onResponse(Call<RechargeStatusResponse> call, Response<RechargeStatusResponse> response) {

                RechargeStatusResponse lr = response.body();


                if(response.code() == 200) {
                    String message = lr.getMessage();
                    String time = lr.getRechage_response().getOpration_date();
                    String transaction_id = lr.getRechage_response().getTransaction_id();
                    String order_id = lr.getRechage_response().getOrder_number();

                    SharedPreferences sharedpreferences = getActivity().getSharedPreferences("RECHARGEDETAIL", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    editor.putString("time", time);
                    editor.putString("transaction_id", transaction_id);
                    editor.putString("order_id", order_id);
                    editor.apply();


                    Toast.makeText(getContext(),message,Toast.LENGTH_SHORT).show();
                    PaymentSuccessFragment paymentSuccessFragment = new PaymentSuccessFragment();
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.rvContainer, paymentSuccessFragment);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                }
                else if (response.code() == 500)
                {
                     Toast.makeText(getContext(),"Insufficient Balance",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(getContext(),"Something Went Wrong",Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onFailure(Call<RechargeStatusResponse> call, Throwable t) {
                Toast.makeText(getContext(),"Failure",Toast.LENGTH_SHORT).show();
            }
        });
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

        SharedPreferences pref = getActivity().getSharedPreferences("MOBILE",Context.MODE_PRIVATE);
        pref.edit().putString("MOBILE",mobile).apply();

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
                    String mobile = lr.getMobile();
                    String provider_name = lr.getData().getProvider_name();
                    String image = lr.getData().getImage();
                    String id = lr.getData().get_id();

                    SharedPreferences pref = getActivity().getSharedPreferences("SERVICEPROVIDER",Context.MODE_PRIVATE);
                    pref.edit().putString("S_ID",id).apply();

                    tvMobileNumber.setText(mobile);
                    Log.d("ID",id);

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


                    Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
                    llEnterMobile.setVisibility(View.GONE);
                    llRechargeDetails.setVisibility(View.VISIBLE);
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

