package com.gws.pargati.paypoint.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.gws.pargati.paypoint.R;

public class HomeUtilitiesActivity extends AppCompatActivity {

    public RecyclerView mRecyclerView;
    public RecyclerView.Adapter mAdapter;
    public RecyclerView.LayoutManager mLayoutManager;

    ImageView ivBack, ivAddUsers;
    TextView tvTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_utilities);

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

    }
}
