package com.gws.pargati.paypoint.fragments;

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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gws.pargati.paypoint.R;
import com.gws.pargati.paypoint.storage.SharedPrefManager;

public class AddMoneyFragment  extends Fragment implements View.OnClickListener{

    private EditText etAddAmount;
    View view;
    private Button btn100Rs, btn200Rs, btn300Rs;
    TextView tvtitle;
    ImageView ivAddUsers, ivBack;
    private LinearLayout llAddMoneyDealer, llAddMoneyAdmin;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.add_money_frag,container,false);

        etAddAmount = (EditText)view.findViewById(R.id.etEnterAmt);
        btn100Rs = (Button)view.findViewById(R.id.btn100Rs);
        btn200Rs = (Button)view.findViewById(R.id.btn200Rs);
        btn300Rs = (Button)view.findViewById(R.id.btn300Rs);
        tvtitle = (TextView)view.findViewById(R.id.tvTitle);
        ivAddUsers =(ImageView)view.findViewById(R.id.ivAddUsers);
        ivBack = (ImageView)view.findViewById(R.id.ivBack);
        llAddMoneyAdmin = (LinearLayout)view.findViewById(R.id.llAddMoneyAdmin);
        llAddMoneyDealer = (LinearLayout)view.findViewById(R.id.llAddMoneyDealer);

        tvtitle.setText("Add Money");
        ivAddUsers.setVisibility(View.GONE);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });

        btn100Rs.setOnClickListener(this);
        btn200Rs.setOnClickListener(this);
        btn300Rs.setOnClickListener(this);

        bottomBar(view);

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
            case R.id.btn100Rs :
                etAddAmount.setText("₹ 100");
                break;

            case R.id.btn200Rs :
                etAddAmount.setText("₹ 200");
                break;

            case R.id.btn300Rs :
                etAddAmount.setText("₹ 300");
                break;

            case R.id.ivBack :
                getActivity().onBackPressed();
                break;

        }

    }

    public void bottomBar(View view)
    {
        String user_type = SharedPrefManager.getInstance(getActivity()).getUser().getUser_type();
        if(user_type.equals("user"))
        {
            llAddMoneyDealer.setVisibility(View.GONE);
            llAddMoneyAdmin.setVisibility(View.GONE);

        }
        if(user_type.equals("dealer"))
        {
            llAddMoneyDealer.setVisibility(View.VISIBLE);
            llAddMoneyAdmin.setVisibility(View.GONE);

        }
        if(user_type.equals("admin"))
        {
            llAddMoneyDealer.setVisibility(View.GONE);
            llAddMoneyAdmin.setVisibility(View.VISIBLE);

        }

    }


}
