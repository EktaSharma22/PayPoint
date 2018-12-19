package com.example.ganesha.paypoint.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ganesha.paypoint.R;
import com.example.ganesha.paypoint.adapters.RechargeServiceAdapter;
import com.example.ganesha.paypoint.model.RechargeServices;

import java.util.ArrayList;

public class DthActivity extends AppCompatActivity {

    public RecyclerView mRecyclerView;
    public RecyclerView.Adapter mAdapter;
    public RecyclerView.LayoutManager mLayoutManager;

    ImageView ivBack, ivAddUsers;
    TextView tvTitle;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dth);

        ivBack = (ImageView)findViewById(R.id.ivBack);
        ivAddUsers = (ImageView)findViewById(R.id.ivAddUsers);
        tvTitle = (TextView)findViewById(R.id.tvTitle);

        ivAddUsers.setVisibility(View.GONE);
        tvTitle.setText("DTH Top-Up");

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        ArrayList<RechargeServices> rechargeServices = new ArrayList<>();
        rechargeServices.add(new RechargeServices(R.drawable.normel_subisu_icon,"Subisu"));
        rechargeServices.add(new RechargeServices(R.drawable.normel_worldlink_icon,"Worldlink"));
        rechargeServices.add(new RechargeServices(R.drawable.normel_dishhome_icon,"DishHomed On-line Direct "));
        rechargeServices.add(new RechargeServices(R.drawable.normel_nettv_icon,"NET TV"));
        rechargeServices.add(new RechargeServices(R.drawable.normel_dishhome_icon,"DishHome-EPIN"));
        rechargeServices.add(new RechargeServices(R.drawable.normel_broadline_icon,"BroadLink"));
        rechargeServices.add(new RechargeServices(R.drawable.normel_simtv_icon,"SIMTV"));
        rechargeServices.add(new RechargeServices(R.drawable.normel_vanet_icon,"Vianet"));

    }
}
