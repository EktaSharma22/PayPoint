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

public class ChangePaswordFragment extends Fragment implements View.OnClickListener{

    ImageView ivBack, ivAddUsers;
    TextView tvTitle;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.change_password_frag,container,false);

        ivBack = (ImageView)view.findViewById(R.id.ivBack);
        ivAddUsers = (ImageView)view.findViewById(R.id.ivAddUsers);
        tvTitle = (TextView)view.findViewById(R.id.tvTitle);

        ivAddUsers.setVisibility(View.GONE);
        tvTitle.setText("Change Password");

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

       /* tvId.setText(String.valueOf(SharedPrefManager.getInstance(getActivity()).getUser().getId()));
        tvName.setText(SharedPrefManager.getInstance(getActivity()).getUser().getName());
        tvEmail.setText(SharedPrefManager.getInstance(getActivity()).getUser().getEmail());*/

    }

    @Override
    public void onClick(View view)
    {

        }

    }

