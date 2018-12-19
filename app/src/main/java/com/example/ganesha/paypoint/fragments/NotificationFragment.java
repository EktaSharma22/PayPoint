package com.example.ganesha.paypoint.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ganesha.paypoint.R;
import com.example.ganesha.paypoint.adapters.NotificationAdapter;
import com.example.ganesha.paypoint.adapters.UsersAdapter;
import com.example.ganesha.paypoint.api.RetrofitClient;
import com.example.ganesha.paypoint.model.User;
import com.example.ganesha.paypoint.model.UserResponse;
import com.example.ganesha.paypoint.model.WalletData;
import com.example.ganesha.paypoint.model.WalletNotificationResponse;
import com.example.ganesha.paypoint.model.WalletReqData;
import com.example.ganesha.paypoint.model.WalletRequestResponse;

import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotificationFragment extends Fragment
{
    private ImageView ivBack, ivAddUsers;
    private RecyclerView recyclerView;
    private NotificationAdapter adapter;
    private List<WalletReqData> walletList;
    TextView tvTitle;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //  recyclerView.setAdapter(new UsersAdapter(getActivity(),userList));
        View view = inflater.inflate(R.layout.notification_frag, container, false);

        ivBack = (ImageView)view.findViewById(R.id.ivBack);
        ivAddUsers = (ImageView)view.findViewById(R.id.ivAddUsers);
        tvTitle = (TextView)view.findViewById(R.id.tvTitle);

        ivAddUsers.setVisibility(View.GONE);
        tvTitle.setText("Notification");

        ivBack = (ImageView)view.findViewById(R.id.ivBack);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment homeFragment = new HomeFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.rvContainer, homeFragment ); // give your fragment container id in first parameter
                transaction.disallowAddToBackStack();  // if written, this transaction will be added to backstack
                transaction.commit();
            }
        });
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recyclerViewNotification);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        SharedPreferences preferences = getActivity().getSharedPreferences("MY_APP",Context.MODE_PRIVATE);
        String retrivedToken  = preferences.getString("TOKEN",null);

        Call<WalletNotificationResponse> call = RetrofitClient.getmInstance().getApi().getWallet(retrivedToken);
        call.enqueue(new Callback<WalletNotificationResponse>() {
            @Override
            public void onResponse(Call<WalletNotificationResponse> call, Response<WalletNotificationResponse> response)
            {
                walletList = response.body().getData();
                adapter = new NotificationAdapter(getActivity(), walletList);
                recyclerView.setAdapter(adapter);

            }

            @Override
            public void onFailure(Call<WalletNotificationResponse> call, Throwable t) {

                Toast.makeText(getContext(),"There is Something Wrong !!",Toast.LENGTH_SHORT).show();

            }
        });
    }


}