package com.gws.pargati.paypoint.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gws.pargati.paypoint.R;
import com.gws.pargati.paypoint.activities.MainActivity;

public class Intro1 extends Fragment implements View.OnClickListener
{
    TextView tvSkip;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.intro1, container, false);
        tvSkip = (TextView)view.findViewById(R.id.tvSkip);
        tvSkip.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.tvSkip :
                Intent intent = new Intent(getContext(),MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                getActivity().onBackPressed();

        }

    }
}
