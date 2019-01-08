package com.gws.pargati.paypoint.activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.gws.pargati.paypoint.R;
import com.gws.pargati.paypoint.adapters.RechargeServiceAdapter;
import com.gws.pargati.paypoint.api.RetrofitClient;
import com.gws.pargati.paypoint.model.ServiceCategory;
import com.gws.pargati.paypoint.model.ServiceProviderAllResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RechargeBillActivity extends AppCompatActivity {

    public RecyclerView mRecyclerView;
    public RecyclerView.Adapter mAdapter;
    public RecyclerView.LayoutManager mLayoutManager;

    ImageView ivBack, ivAddUsers;
    TextView tvTitle;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recharge_bill);

        ivBack = (ImageView)findViewById(R.id.ivBack);
        ivAddUsers = (ImageView)findViewById(R.id.ivAddUsers);
        tvTitle = (TextView)findViewById(R.id.tvTitle);
        ivAddUsers.setVisibility(View.GONE);
        mRecyclerView = (RecyclerView)findViewById(R.id.servicesRecyclerView);

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        try {

            SharedPreferences preferences = RechargeBillActivity.this.getSharedPreferences("MY_APP",Context.MODE_PRIVATE);
            String retrivedToken  = preferences.getString("TOKEN",null);

            SharedPreferences preferences1 = RechargeBillActivity.this.getSharedPreferences("MyPrefCat", 0);
            String caegory_id = preferences1.getString("category_id",null);

            Call<ServiceProviderAllResponse> call = RetrofitClient.getmInstance().getApi().serviceCategory(retrivedToken,caegory_id);
            call.enqueue(new Callback<ServiceProviderAllResponse>() {
                @Override
                public void onResponse(Call<ServiceProviderAllResponse> call, Response<ServiceProviderAllResponse> response) {
                    List<ServiceCategory> items = response.body().getData();
                    LinearLayoutManager horizontalLayoutManagaer = new LinearLayoutManager(RechargeBillActivity.this, LinearLayoutManager.VERTICAL, false);
                    mRecyclerView.setLayoutManager(horizontalLayoutManagaer);
                    mRecyclerView.setAdapter(new RechargeServiceAdapter(RechargeBillActivity.this,items));
                    mRecyclerView.smoothScrollToPosition(0);
                }
                @Override
                public void onFailure(Call<ServiceProviderAllResponse> call, Throwable t) {
                    Log.d("Error",t.getMessage());
                    Toast.makeText(RechargeBillActivity.this,"Error Fetching data....",Toast.LENGTH_SHORT).show();
                }
            });
        }catch (Exception e)
        {
            Log.d("Error",e.getMessage());
            Toast.makeText(RechargeBillActivity.this,e.toString(),Toast.LENGTH_SHORT).show();

        }




    }
}
