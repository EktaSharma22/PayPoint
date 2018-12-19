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
import android.widget.Toast;

import com.example.ganesha.paypoint.R;
import com.example.ganesha.paypoint.adapters.DealerUsersAdapter;
import com.example.ganesha.paypoint.api.RetrofitClient;
import com.example.ganesha.paypoint.model.UploadUserTypeResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DealerFragment  extends Fragment
{
    private ImageView ivBack, ivAddUsers;
    private RecyclerView recyclerViewDealerUsers;
    private DealerUsersAdapter adapter;
    private List<UploadUserTypeResponse.DealerUsers> userList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //  recyclerView.setAdapter(new UsersAdapter(getActivity(),userList));
        View view = inflater.inflate(R.layout.fragment_dealerusers, container, false);

        ivAddUsers = (ImageView)view.findViewById(R.id.ivAddUsers);
        ivAddUsers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddUsersFragment addUsersFragment = new AddUsersFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.rvContainer, addUsersFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

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
        recyclerViewDealerUsers = view.findViewById(R.id.recyclerViewDealerUsers);
        recyclerViewDealerUsers.setLayoutManager(new LinearLayoutManager(getActivity()));

        SharedPreferences preferences = getActivity().getSharedPreferences("MY_APP",Context.MODE_PRIVATE);
        String retrivedToken  = preferences.getString("TOKEN",null);

        String dealer = "dealer";

        Call<UploadUserTypeResponse> call = RetrofitClient.getmInstance().getApi().getDealerUsers(retrivedToken,dealer);
        call.enqueue(new Callback<UploadUserTypeResponse>() {
            @Override
            public void onResponse(Call<UploadUserTypeResponse> call, Response<UploadUserTypeResponse> response) {
                userList = response.body().getData();
                adapter = new DealerUsersAdapter(getActivity(), userList);
                recyclerViewDealerUsers.setAdapter(adapter);


            }
            @Override
            public void onFailure(Call<UploadUserTypeResponse> call, Throwable t) {
                Toast.makeText(getContext(),"Failure",Toast.LENGTH_SHORT).show();
            }
        });
    }


}

