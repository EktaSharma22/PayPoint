package com.gws.pargati.paypoint.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gws.pargati.paypoint.R;
import com.gws.pargati.paypoint.adapters.SettingsAdapter;
import com.gws.pargati.paypoint.model.RechargeServices;
import com.gws.pargati.paypoint.storage.SharedPrefManager;

import java.util.ArrayList;

public class SettingsFragment extends Fragment {

    public RecyclerView mRecyclerView;
    public RecyclerView.Adapter mAdapter;
    public RecyclerView.LayoutManager mLayoutManager;
    ImageView ivBack, ivAddUsers;
    TextView tvTitle;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_settings,container,false);

        ivBack = (ImageView)view.findViewById(R.id.ivBack);
        ivAddUsers = (ImageView)view.findViewById(R.id.ivAddUsers);
        tvTitle = (TextView)view.findViewById(R.id.tvTitle);

        ivAddUsers.setVisibility(View.GONE);
        tvTitle.setText("Settings");
        ivBack = (ImageView)view.findViewById(R.id.ivBack);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment homeFragment = new HomeFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.rvContainer, homeFragment );
                transaction.disallowAddToBackStack();
                transaction.commit();
            }
        });
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ArrayList<RechargeServices> rechargeServices = new ArrayList<>();

        String user_type = SharedPrefManager.getInstance(getContext()).getUser().getUser_type();
        if(user_type.equals("user"))
        {
            rechargeServices.add(new RechargeServices(R.drawable.lock,"Change Password"));
        }
        if(user_type.equals("dealer"))
        {
            rechargeServices.add(new RechargeServices(R.drawable.lock,"Change Password"));
            rechargeServices.add(new RechargeServices(R.drawable.wallet,"Wallet Commission"));
        }
        if(user_type.equals("admin"))
        {
            rechargeServices.add(new RechargeServices(R.drawable.lock,"Change Password"));
            rechargeServices.add(new RechargeServices(R.drawable.wallet,"Wallet Commission"));
        }

        mRecyclerView = (RecyclerView)view.findViewById(R.id.rvSettings);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getContext());
        mAdapter = new SettingsAdapter(rechargeServices);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

}