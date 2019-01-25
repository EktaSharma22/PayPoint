package com.gws.pargati.paypoint.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gws.pargati.paypoint.R;

public class PaymentSuccessFragment extends Fragment implements View.OnClickListener{

    TextView tvtitle,tvAmt,tvTime,tvOrderNo,tvTransactionId;
    ImageView ivAddUsers, ivBack;
    LinearLayout llRechargeAgain;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.payment_successful,container,false);
        tvtitle = (TextView)view.findViewById(R.id.tvTitle);
        ivAddUsers =(ImageView)view.findViewById(R.id.ivAddUsers);
        ivBack = (ImageView)view.findViewById(R.id.ivBack);
        tvAmt = (TextView)view.findViewById(R.id.tvAmt);
        tvTime = (TextView)view.findViewById(R.id.tvTime);
        tvOrderNo = (TextView)view.findViewById(R.id.tvOrderNo);
        tvTransactionId = (TextView)view.findViewById(R.id.tvTransactionId);
        llRechargeAgain = (LinearLayout)view.findViewById(R.id.llRechargeAgain);

        llRechargeAgain.setOnClickListener(this);

        SharedPreferences p = getActivity().getSharedPreferences("AMOUNT",Context.MODE_PRIVATE);
        String amt = p.getString("AMT",null);

        SharedPreferences prfs = getActivity().getSharedPreferences("RECHARGEDETAIL", Context.MODE_PRIVATE);
        String time = prfs.getString("time",null);
        String transaction_id = prfs.getString("transaction_id",null);
        String order_id = prfs.getString("order_id",null);

        tvAmt.setText("₹ " +amt);
        tvTime.setText(time);
        tvOrderNo.setText(order_id);
        tvTransactionId.setText(transaction_id);

        tvtitle.setText("Payment Successful");
        ivAddUsers.setImageResource(R.drawable.ic_share_white_24dp);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });
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
           case R.id.llRechargeAgain:
               HomeFragment homeFragment = new HomeFragment();
               FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
               FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
               fragmentTransaction.replace(R.id.rvContainer, homeFragment);
               fragmentTransaction.addToBackStack(null);
               fragmentTransaction.commit();
               break;
       }
    }

}

