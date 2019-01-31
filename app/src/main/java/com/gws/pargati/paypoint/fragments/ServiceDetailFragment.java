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
import com.gws.pargati.paypoint.activities.LoginActivity;
import com.gws.pargati.paypoint.activities.RegisterActivity;
import com.gws.pargati.paypoint.api.RetrofitClient;
import com.gws.pargati.paypoint.model.CheckBalResponse;
import com.gws.pargati.paypoint.model.ElectricityOfficeResponse;
import com.gws.pargati.paypoint.model.ElectricityPayResponse;
import com.gws.pargati.paypoint.model.Office;
import com.gws.pargati.paypoint.model.RechargeCheckResponse;
import com.gws.pargati.paypoint.model.RechargeStatusResponse;
import com.gws.pargati.paypoint.model.UploadUserTypeResponse;
import com.gws.pargati.paypoint.model.WorldLinkData;
import com.gws.pargati.paypoint.model.WorldlinkResponse;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ServiceDetailFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    private EditText etMobile,etUserrId,etMSISDNNumber,etSubscriberNumber;
    private Button btnSubmitMobile,btnRecharge,btnSubmitUserId,btnWorldinkRecharge,btnElectricityRecharge,btnElectricityPay;
    ImageView ivBack, ivAddUsers;
    TextView tvTitle, tvEnterMobile;
    private LinearLayout llEnterMobile,llRechargeDetails,llWorldlinkDetails,llMobile,llWorldlinkAmt,llWorldlinkAmountDrop,llElectricityDetails,llDishTv;
    Spinner spinnerAmt,spinnerWorldlinkAmt,spinnerOfficeCode,spinnerDishPlan;
    TextView tvMobileNumber,tvProviderName,tvWorldlinkAmt,tvCashId;
    ImageView ivRechargeIcon;
    LinearLayout llAmountEdit, llAmountDrop,llEnterUserId;
    private List<WorldLinkData> userList;
    private List<Office> offices;
    private String total_due_amount = "";
    private String service_charge = "";
    private String msisdn = "";
    private String subscriber = "";
    private String customerId = "";
    private String balance ="";
    private String expiryDate ="";
    private String pack = "";
    private String plan = "";
    private Button btnCheckBalanceDish,btnRechargeDish;

    private LinearLayout llOfficeCode,llElectricityAmount,llServiceCharge,llCheckDetail;
    private TextView tvServiceCharge,tvElectricityAmount,tvcustomerId,tvbalance,tvexpiryDate,tvpack;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.service_detail_frag,container,false);
        tvcustomerId = (TextView)view.findViewById(R.id.tvcustomerId);
        tvbalance = (TextView)view.findViewById(R.id.tvbalance);
        tvexpiryDate = (TextView)view.findViewById(R.id.tvexpiryDate);
        tvpack = (TextView)view.findViewById(R.id.tvpack);
        llCheckDetail = (LinearLayout)view.findViewById(R.id.llCheckDetail);

        etMobile = (EditText)view.findViewById(R.id.etMobile);
        btnSubmitMobile = (Button)view.findViewById(R.id.btnSubmitMobile);
        btnWorldinkRecharge = (Button)view.findViewById(R.id.btnWorldinkRecharge);
        btnElectricityRecharge = (Button)view.findViewById(R.id.btnElectricityRecharge);
        btnElectricityPay = (Button)view.findViewById(R.id.btnElectricityPay);
        btnCheckBalanceDish = (Button)view.findViewById(R.id.btnCheckBalanceDish);
        btnRechargeDish = (Button)view.findViewById(R.id.btnRechargeDish);

        tvWorldlinkAmt = (TextView)view.findViewById(R.id.tvWorldlinkAmt);
        llWorldlinkAmt = (LinearLayout)view.findViewById(R.id.llWorldlinkAmt);
        llWorldlinkAmountDrop = (LinearLayout)view.findViewById(R.id.llWorldlinkAmountDrop);
        llMobile = (LinearLayout)view.findViewById(R.id.llMobile);
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
        llEnterUserId = (LinearLayout)view.findViewById(R.id.llEnterUserId);
        etUserrId = (EditText)view.findViewById(R.id.etUserrId);
        btnSubmitUserId = (Button)view.findViewById(R.id.btnSubmitUserId);
        spinnerWorldlinkAmt = (Spinner)view.findViewById(R.id.spinnerWorldlinkAmt);
        llWorldlinkDetails = (LinearLayout)view.findViewById(R.id.llWorldlinkDetails);
        llElectricityDetails = (LinearLayout)view.findViewById(R.id.llElectricityDetails);
        spinnerOfficeCode = (Spinner)view.findViewById(R.id.spinnerOfficeCode);
        etSubscriberNumber = (EditText)view.findViewById(R.id.etSubscriberNumber);
        etMSISDNNumber = (EditText)view.findViewById(R.id.etMSISDNNumber);
        llOfficeCode = (LinearLayout)view.findViewById(R.id.llOfficeCode);
        llElectricityAmount = (LinearLayout)view.findViewById(R.id.llElectricityAmount);
        llServiceCharge = (LinearLayout)view.findViewById(R.id.llServiceCharge);
        tvElectricityAmount =(TextView)view.findViewById(R.id.tvElectricityAmount);
        tvServiceCharge = (TextView)view.findViewById(R.id.tvServiceCharge);
        llDishTv = (LinearLayout)view.findViewById(R.id.llDishTv);
        spinnerDishPlan = (Spinner)view.findViewById(R.id.spinnerDishPlan);
        tvCashId = (TextView)view.findViewById(R.id.tvCashId);

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

        SharedPreferences p = getActivity().getSharedPreferences("MY_CAT_ID",Context.MODE_PRIVATE);
        String id = p.getString("CATEGORYID",null);

        if (id.equals("5c4e96a53ff0862189bf8738"))
        {
           llEnterUserId.setVisibility(View.VISIBLE);
        }
        else if (id.equals("5c4e98153ff0862189bf873a"))
            {
              llElectricityDetails.setVisibility(View.VISIBLE);
              getOffices();
            }
            else if (id.equals("5c504d1127d1efdb2b4cd926"))
            {
               llDishTv.setVisibility(View.VISIBLE);
                SharedPreferences pr = getActivity().getSharedPreferences("DENOMINATION",Context.MODE_PRIVATE);
                String denomination = pr.getString("DENOMINATION",null);

                ArrayList<String> denominationList = new ArrayList<String>(Arrays.asList(denomination.split(",")));
                ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>
                        (getContext(), android.R.layout.simple_spinner_item, denominationList); //selected item will look like a spinner set from XML
                spinnerArrayAdapter.setDropDownViewResource(android.R.layout
                        .simple_spinner_dropdown_item);
                spinnerDishPlan.setAdapter(spinnerArrayAdapter);

                spinnerDishPlan.setOnItemSelectedListener(
                        new AdapterView.OnItemSelectedListener() {
                            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id)
                            {
                                ((TextView) parent.getChildAt(0)).setTextSize(12);
                                 plan = parent.getItemAtPosition(pos).toString();
                            }
                            public void onNothingSelected(AdapterView<?> parent) {
                            }
                        });


            }
            else
            {
                llEnterMobile.setVisibility(View.VISIBLE);
            }

        view.findViewById(R.id.btnSubmitMobile).setOnClickListener(this);
        view.findViewById(R.id.btnRecharge).setOnClickListener(this);
        view.findViewById(R.id.btnSubmitUserId).setOnClickListener(this);
        view.findViewById(R.id.btnWorldinkRecharge).setOnClickListener(this);
        view.findViewById(R.id.btnElectricityRecharge).setOnClickListener(this);
        view.findViewById(R.id.btnElectricityPay).setOnClickListener(this);
        view.findViewById(R.id.btnCheckBalanceDish).setOnClickListener(this);
        view.findViewById(R.id.btnRechargeDish).setOnClickListener(this);

        spinnerAmt.setOnItemSelectedListener(this);


        return view;
    }

    private void getOffices()
    {
        SharedPreferences preferences = getActivity().getSharedPreferences("MY_APP",Context.MODE_PRIVATE);
        String retrivedToken  = preferences.getString("TOKEN",null);

        Call<ElectricityOfficeResponse> call = RetrofitClient.getmInstance().getApi().getOffices(retrivedToken);
        call.enqueue(new Callback<ElectricityOfficeResponse>() {
            @Override
            public void onResponse(Call<ElectricityOfficeResponse> call, Response<ElectricityOfficeResponse> response)
            {
                final ElectricityOfficeResponse lr = response.body();
                offices = response.body().getData();
                final ArrayList<Office> listOffices= (ArrayList<Office>) offices;
                ArrayList officeNamelist=new ArrayList();
                for (int i=0;i<listOffices.size();i++){
                    officeNamelist.add(listOffices.get(i).getTitle());
                }
                Log.d("list",officeNamelist.toString());
                ArrayAdapter<UploadUserTypeResponse.DealerUsers> adapter = new ArrayAdapter<UploadUserTypeResponse.DealerUsers>(getContext(), R.layout.spinner_item, officeNamelist);
                adapter.setDropDownViewResource(R.layout.spinner_item);
                spinnerOfficeCode.setAdapter(adapter);

                spinnerOfficeCode.setOnItemSelectedListener(
                        new AdapterView.OnItemSelectedListener() {
                            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id)
                            {
                                ((TextView) parent.getChildAt(0)).setTextSize(12);
                                String office_code = lr.getData().get(pos).getOffice_code();
                                SharedPreferences sharedpreferences = getActivity().getSharedPreferences("ElectricityOfficeCode", Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedpreferences.edit();
                                editor.putString("office_code", office_code);
                                editor.apply();
                            }
                            public void onNothingSelected(AdapterView<?> parent) {
                            }
                        });
            }
            @Override
            public void onFailure(Call<ElectricityOfficeResponse> call, Throwable t) {
                Toast.makeText(getContext(),"Failure",Toast.LENGTH_SHORT).show();
            }
        });

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
                break;
                
            case R.id.btnSubmitUserId :
                checkQuery();
                break;

            case R.id.btnWorldinkRecharge:
                worldlinkPay();
                break;

            case R.id.btnElectricityRecharge :
                electricityConfirm();
                break;

            case R.id.btnElectricityPay :
                electricityPay();
                break;

            case R.id.btnCheckBalanceDish :
                checkBal();
                break;

            case R.id.btnRechargeDish :
                rechargeDish();
                break;
        }
    }

    private void rechargeDish()
    {
        SharedPreferences preferences = getActivity().getSharedPreferences("MY_APP",Context.MODE_PRIVATE);
        String retrivedToken  = preferences.getString("TOKEN",null);

        SharedPreferences p = getActivity().getSharedPreferences("MY_CAT_ID",Context.MODE_PRIVATE);
        String service_provider = p.getString("CATEGORYID",null);

        String cashid = tvCashId.getText().toString();

        int amount = Integer.parseInt(plan);


        Call<ResponseBody> call = RetrofitClient.getmInstance().getApi().rechargeDish(retrivedToken,service_provider,cashid,amount);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response)
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
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(getContext(),"Failure",Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void checkBal()
    {
        SharedPreferences preferences = getActivity().getSharedPreferences("MY_APP",Context.MODE_PRIVATE);
        String retrivedToken  = preferences.getString("TOKEN",null);

        SharedPreferences p = getActivity().getSharedPreferences("MY_CAT_ID",Context.MODE_PRIVATE);
        String service_provider = p.getString("CATEGORYID",null);

        String cashid = tvCashId.getText().toString();

        Call<CheckBalResponse> call = RetrofitClient.getmInstance().getApi().checkBal(retrivedToken,cashid,service_provider);
        call.enqueue(new Callback<CheckBalResponse>() {
            @Override
            public void onResponse(Call<CheckBalResponse> call, Response<CheckBalResponse> response)
            {
                CheckBalResponse checkBalResponse = response.body();

                if(response.code() == 200)
                {
                    Toast.makeText(getContext(),checkBalResponse.getMessage(),Toast.LENGTH_SHORT).show();
                    customerId = checkBalResponse.getData().getCustomerId();
                    balance = checkBalResponse.getData().getBalance();
                    expiryDate = checkBalResponse.getData().getExpiryDate();
                    pack = checkBalResponse.getData().getPack();

                    llCheckDetail.setVisibility(View.VISIBLE);
                    tvcustomerId.setText(customerId);
                    tvbalance.setText(balance);
                    tvexpiryDate.setText(expiryDate);
                    tvpack.setText(pack);
                }
                else
                {
                    Toast.makeText(getContext(),"Something went wrong.",Toast.LENGTH_SHORT).show();
                }

            }
            @Override
            public void onFailure(Call<CheckBalResponse> call, Throwable t) {
                Toast.makeText(getContext(),"Failure",Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void electricityPay()
    {
        SharedPreferences preferences = getActivity().getSharedPreferences("MY_APP",Context.MODE_PRIVATE);
        String retrivedToken  = preferences.getString("TOKEN",null);

        float amt = Float.parseFloat(total_due_amount) + Float.parseFloat(service_charge);
        String amount = String.valueOf(amt);

        String esrvice_charge = service_charge;

        SharedPreferences prfs = getActivity().getSharedPreferences("ElectricityOfficeCode", Context.MODE_PRIVATE);
        String office_code = prfs.getString("office_code",null);

        String subscriber_number = subscriber;

        String ms_isdn = msisdn;

        Call<ResponseBody> call = RetrofitClient.getmInstance().getApi().electricityPay(retrivedToken,ms_isdn,subscriber_number,office_code,amount,esrvice_charge);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response)
            {
                if(response.code() == 200)
                {
                    Toast.makeText(getContext(),"Success",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(getContext(),"Something went wrong. Please contact to provider.",Toast.LENGTH_SHORT).show();
                }

            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(getContext(),"Failure",Toast.LENGTH_SHORT).show();
            }
        });





    }

    private void electricityConfirm()
    {
        SharedPreferences preferences = getActivity().getSharedPreferences("MY_APP",Context.MODE_PRIVATE);
        String retrivedToken  = preferences.getString("TOKEN",null);

        msisdn = etMSISDNNumber.getText().toString();
        subscriber = etSubscriberNumber.getText().toString();

        SharedPreferences prfs = getActivity().getSharedPreferences("ElectricityOfficeCode", Context.MODE_PRIVATE);
        String office_code = prfs.getString("office_code",null);

        Call<ElectricityPayResponse> call = RetrofitClient.getmInstance().getApi().electricityBillQuery(retrivedToken,msisdn,subscriber,office_code);
        call.enqueue(new Callback<ElectricityPayResponse>() {
            @Override
            public void onResponse(Call<ElectricityPayResponse> call, Response<ElectricityPayResponse> response)
            {
                ElectricityPayResponse electricityPayResponse = response.body();
                if(response.code() == 200)
                {
                    total_due_amount = electricityPayResponse.getData().getTotal_due_amount();
                    service_charge = electricityPayResponse.getData().getService_charge();

                    tvElectricityAmount.setText(total_due_amount);
                    tvServiceCharge.setText(service_charge);
                    etMSISDNNumber.setEnabled(false);
                    etMSISDNNumber.setCursorVisible(false);
                    etSubscriberNumber.setEnabled(false);
                    etSubscriberNumber.setCursorVisible(false);
                    llOfficeCode.setVisibility(View.GONE);
                    llElectricityAmount.setVisibility(View.VISIBLE);
                    llServiceCharge.setVisibility(View.VISIBLE);
                    btnElectricityRecharge.setVisibility(View.GONE);
                    btnElectricityPay.setVisibility(View.VISIBLE);

                    Log.d("totalDueAmount",total_due_amount);
                    Log.d("serviceAmount",service_charge);
                }
                else
                    {
                        Toast.makeText(getContext(),"Something wrong",Toast.LENGTH_SHORT).show();
                    }

            }
            @Override
            public void onFailure(Call<ElectricityPayResponse> call, Throwable t) {
                Toast.makeText(getContext(),"Failure",Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void worldlinkPay()
    {
        SharedPreferences preferences = getActivity().getSharedPreferences("MY_APP",Context.MODE_PRIVATE);
        String retrivedToken  = preferences.getString("TOKEN",null);

        String user_id = etUserrId.getText().toString();

        String amount = "";
        String order_id = "";
        String pay_type = "";
        SharedPreferences prfs = getActivity().getSharedPreferences("WORLDLNKAMT", Context.MODE_PRIVATE);
        String amt = prfs.getString("WL_AMT",null);
        String id = prfs.getString("option_id",null);
        String type = prfs.getString("pay_type",null);

        SharedPreferences pref1 = getActivity().getSharedPreferences("WORLDLNKAMT1",Context.MODE_PRIVATE);
        String amt1 = pref1.getString("WL_AMT1",null);

        if (amt == null)
        {
            amount = amt1;
        }
        else
            {
                amount = amt;
            }

            if(id == null)
            {
                order_id = "";
            }
            else
                {
                    order_id = id;
                }


        if(type == null)
        {
            pay_type = "";
        }
        else
        {
            pay_type = type;
        }

        Call<ResponseBody> call = RetrofitClient.getmInstance()
                .getApi()
                .rechargeWorldlink(retrivedToken,user_id,amount,pay_type,order_id);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                if(response.code() == 200) {
                    Toast.makeText(getContext(),"success",Toast.LENGTH_SHORT).show();

                    PaymentSuccessFragment paymentSuccessFragment = new PaymentSuccessFragment();
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.rvContainer, paymentSuccessFragment);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();

                }
                else if(response.code() == 500)
                {
                    Toast.makeText(getContext(),"Something went wrong. Please contact to provider.",Toast.LENGTH_SHORT).show();
                }
                else
                    {
                        Toast.makeText(getContext(),"Something went wrong.",Toast.LENGTH_SHORT).show();
                    }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(getContext(),"Failure",Toast.LENGTH_SHORT).show();
            }
        });



    }

    private void checkQuery()
    {
        SharedPreferences preferences = getActivity().getSharedPreferences("MY_APP",Context.MODE_PRIVATE);
        String retrivedToken  = preferences.getString("TOKEN",null);

        String user_id = etUserrId.getText().toString();

        Call<WorldlinkResponse> call = RetrofitClient.getmInstance()
                .getApi()
                .query(retrivedToken,user_id);
        call.enqueue(new Callback<WorldlinkResponse>() {
            @Override
            public void onResponse(Call<WorldlinkResponse> call, Response<WorldlinkResponse> response)
            {
                final WorldlinkResponse lr = response.body();

                if(response.code() == 200)
                {
                    Toast.makeText(getContext(),"Success",Toast.LENGTH_SHORT).show();
                    final String pay_type = lr.getBalance().getPAY_TYPE();

                    userList = response.body().getData();
                    final ArrayList<WorldLinkData> listUser= (ArrayList<WorldLinkData>) userList;
                    ArrayList userNamelist=new ArrayList();
                    for (int i=0;i<listUser.size();i++){
                        userNamelist.add(listUser.get(i).getTitle());
                    }

                    Log.d("list",userNamelist.toString());

                    ArrayAdapter<UploadUserTypeResponse.DealerUsers> adapter = new ArrayAdapter<UploadUserTypeResponse.DealerUsers>(getContext(), R.layout.spinner_item, userNamelist);
                    adapter.setDropDownViewResource(R.layout.spinner_item);
                    spinnerWorldlinkAmt.setAdapter(adapter);

                    if (userNamelist.size() == 0)
                    {
                        llWorldlinkAmountDrop.setVisibility(View.GONE);
                        llWorldlinkAmt.setVisibility(View.VISIBLE);
                        if(lr.getBalance().getADVANCE_PAYMENT_DISCOUNTED() == null && lr.getBalance().getDISCOUNTED_DUE_AMOUNT() == null)
                        {
                            tvWorldlinkAmt.setText(lr.getBalance().getDISCOUNTED_PAYMENT_AMOUNT());
                        }
                        else if(lr.getBalance().getDISCOUNTED_PAYMENT_AMOUNT() == null && lr.getBalance().getDISCOUNTED_DUE_AMOUNT() == null)
                        {
                            tvWorldlinkAmt.setText(lr.getBalance().getADVANCE_PAYMENT_DISCOUNTED());
                        }
                        else
                            {
                                tvWorldlinkAmt.setText(lr.getBalance().getDISCOUNTED_DUE_AMOUNT());
                            }

                        String amt = tvWorldlinkAmt.getText().toString();
                        SharedPreferences pref = getActivity().getSharedPreferences("WORLDLNKAMT1",Context.MODE_PRIVATE);
                        pref.edit().putString("WL_AMT1",amt).apply();


                    }
                    else
                        {
                            llWorldlinkAmountDrop.setVisibility(View.VISIBLE);
                            llWorldlinkAmt.setVisibility(View.GONE);
                        }

                    llWorldlinkDetails.setVisibility(View.VISIBLE);
                    llEnterUserId.setVisibility(View.GONE);
                    spinnerWorldlinkAmt.setOnItemSelectedListener(
                            new AdapterView.OnItemSelectedListener() {
                                public void onItemSelected(AdapterView<?> parent, View view, int pos, long id)
                                {
                                    ((TextView) parent.getChildAt(0)).setTextSize(12);
                                    String item = lr.getData().get(pos).getDISCOUNTED_RATE();
                                    String option_id = lr.getData().get(pos).getOPTION_ID();
                                    SharedPreferences sharedpreferences = getActivity().getSharedPreferences("WORLDLNKAMT", Context.MODE_PRIVATE);
                                    SharedPreferences.Editor editor = sharedpreferences.edit();
                                    editor.putString("WL_AMT", item);
                                    editor.putString("option_id", option_id);
                                    editor.putString("pay_type",pay_type);
                                    editor.apply();



                                }
                                public void onNothingSelected(AdapterView<?> parent) {
                                }
                            });
                }
                  else
                      {
                          Toast.makeText(getContext(),"Something Wrong",Toast.LENGTH_SHORT).show();
                      }
            }

            @Override
            public void onFailure(Call<WorldlinkResponse> call, Throwable t) {

            }
        });



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

