package com.gws.pargati.paypoint.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class RechargeBillFragment extends Fragment {

    public RecyclerView mRecyclerView;
    public RecyclerView.Adapter mAdapter;
    public RecyclerView.LayoutManager mLayoutManager;

    ImageView ivBack, ivAddUsers;
    TextView tvTitle;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recharge_bill_frag,container,false);

        ivBack = (ImageView)view.findViewById(R.id.ivBack);
        ivAddUsers = (ImageView)view.findViewById(R.id.ivAddUsers);
        tvTitle = (TextView)view.findViewById(R.id.tvTitle);
        mRecyclerView = (RecyclerView)view.findViewById(R.id.servicesRecyclerView);

        ivAddUsers.setVisibility(View.GONE);
        tvTitle.setText("Recharge / Bill Payment");

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

        try {

            SharedPreferences preferences = getContext().getSharedPreferences("MY_APP",Context.MODE_PRIVATE);
            String retrivedToken  = preferences.getString("TOKEN",null);

            SharedPreferences preferences1 = getContext().getSharedPreferences("MyPrefCat", 0);
            String caegory_id = preferences1.getString("category_id",null);

            Call<ServiceProviderAllResponse> call = RetrofitClient.getmInstance().getApi().serviceCategory(retrivedToken,caegory_id);
            call.enqueue(new Callback<ServiceProviderAllResponse>() {
                @Override
                public void onResponse(Call<ServiceProviderAllResponse> call, Response<ServiceProviderAllResponse> response) {
                    List<ServiceCategory> items = response.body().getData();
                 //   LinearLayoutManager horizontalLayoutManagaer = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
                    mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
                    mRecyclerView.setAdapter(new RechargeServiceAdapter(getContext(),items));
                    mRecyclerView.smoothScrollToPosition(0);

                }
                @Override
                public void onFailure(Call<ServiceProviderAllResponse> call, Throwable t) {
                    Log.d("Error",t.getMessage());
                    Toast.makeText(getContext(),"Error Fetching data....",Toast.LENGTH_SHORT).show();
                }
            });
        }catch (Exception e)
        {
            Log.d("Error",e.getMessage());
            Toast.makeText(getContext(),e.toString(),Toast.LENGTH_SHORT).show();

        }







    }

}