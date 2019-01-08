package com.gws.pargati.paypoint.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gws.pargati.paypoint.R;
import com.gws.pargati.paypoint.adapters.OrdersAdapter;

public class OrdersFragment extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    ImageView ivBack, ivAddUsers;
    TextView tvTitle;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.orders_frag,container,false);

        ivBack = (ImageView)view.findViewById(R.id.ivBack);
        ivAddUsers = (ImageView)view.findViewById(R.id.ivAddUsers);
        tvTitle = (TextView)view.findViewById(R.id.tvTitle);

        ivAddUsers.setVisibility(View.GONE);
        tvTitle.setText("Orders");

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

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerViewOrders);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new OrdersAdapter(getContext());
        recyclerView.setAdapter(mAdapter);



    }

}
