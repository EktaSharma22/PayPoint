package com.gws.pargati.paypoint.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gws.pargati.paypoint.R;
import com.gws.pargati.paypoint.storage.SharedPrefManager;

public class MyAccountFragment  extends Fragment {

    ImageView ivBack, ivAddUsers;
    TextView tvTitle;
    TextView token;
    private TextView tvName, tvEmail, tvMobile, tvUserType;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.my_account_fragment,container,false);

        ivBack = (ImageView)view.findViewById(R.id.ivBack);
        ivAddUsers = (ImageView)view.findViewById(R.id.ivAddUsers);
        tvTitle = (TextView)view.findViewById(R.id.tvTitle);
        tvName = (TextView)view.findViewById(R.id.tvName);
        tvEmail = (TextView)view.findViewById(R.id.tvEmail);
        tvMobile = (TextView)view.findViewById(R.id.tvMobile);
        tvUserType = (TextView)view.findViewById(R.id.tvUserType);

        ivAddUsers.setVisibility(View.GONE);
        tvTitle.setText("My Account");

        ivBack = (ImageView)view.findViewById(R.id.ivBack);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment homeFragment = new HomeFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.rvContainer, homeFragment);
                transaction.disallowAddToBackStack();
                transaction.commit();
            }
        });

        String name = SharedPrefManager.getInstance(getActivity()).getUser().getName();
        tvName.setText(name);

        String email = SharedPrefManager.getInstance(getActivity()).getUser().getEmail();
        tvEmail.setText(email);

        /*String mobile = String.valueOf(SharedPrefManager.getInstance(getActivity()).getUser().getMobile());
        tvMobile.setText(mobile);*/

        String user_type = SharedPrefManager.getInstance(getActivity()).getUser().getUser_type();
        tvUserType.setText(user_type);

        return view;
    }
}

