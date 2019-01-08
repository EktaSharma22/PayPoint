package com.gws.pargati.paypoint.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gws.pargati.paypoint.R;
import com.gws.pargati.paypoint.model.WalletData;

import java.util.List;

public class NotificationDetailFragment extends Fragment implements View.OnClickListener{

    private List<WalletData> walletList;
    ImageView ivBack, ivAddUsers;
    TextView tvTitle;
    int position;

    private TextView tvRequestedTo, tvRequestedBy, tvID, tvPaymentMode, tvChequeNumber, tvAmount, tvBankName, tvDepositDate, tvFileName;
    private ImageView ivImage;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.notification_details_frag,container,false);

        tvRequestedTo = (TextView)view.findViewById(R.id.tvRequestedTo);
        tvRequestedBy = (TextView)view.findViewById(R.id.tvRequestedBy);
        tvPaymentMode = (TextView)view.findViewById(R.id.tvPaymentMode);
        tvChequeNumber = (TextView)view.findViewById(R.id.tvChequeNumber);
        tvAmount = (TextView)view.findViewById(R.id.tvAmount);
        tvBankName = (TextView)view.findViewById(R.id.tvBankName);
        tvDepositDate = (TextView)view.findViewById(R.id.tvDepositDate);
        tvFileName = (TextView)view.findViewById(R.id.tvFileName);
        tvID = (TextView)view.findViewById(R.id.tvID);


        ivBack = (ImageView)view.findViewById(R.id.ivBack);
        ivAddUsers = (ImageView)view.findViewById(R.id.ivAddUsers);
        tvTitle = (TextView)view.findViewById(R.id.tvTitle);
        ivAddUsers.setVisibility(View.GONE);
        tvTitle.setText("Request Details");

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

        }

    }

}

