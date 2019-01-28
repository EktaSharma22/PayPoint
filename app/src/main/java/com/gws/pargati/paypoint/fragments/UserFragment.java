package com.gws.pargati.paypoint.fragments;

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
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.gws.pargati.paypoint.R;
import com.gws.pargati.paypoint.adapters.UsersAdapter;
import com.gws.pargati.paypoint.api.RetrofitClient;
import com.gws.pargati.paypoint.model.GetUsersData;
import com.gws.pargati.paypoint.model.UserResponse;

import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserFragment extends Fragment
{
    private ImageView ivBack, ivAddUsers;
    private RecyclerView recyclerView;
    private UsersAdapter adapter;
    private List<GetUsersData> userList;
    private TextView tvAdd;
    private RelativeLayout rlAddUser;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //  recyclerView.setAdapter(new UsersAdapter(getActivity(),userList));
        View view = inflater.inflate(R.layout.fragment_user, container, false);

        tvAdd = (TextView)view.findViewById(R.id.tvAdd);
        tvAdd.setVisibility(View.VISIBLE);
        rlAddUser = (RelativeLayout)view.findViewById(R.id.rlAddUser);

        ivAddUsers = (ImageView) view.findViewById(R.id.ivAddUsers);
        rlAddUser.setOnClickListener(new View.OnClickListener() {
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
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        SharedPreferences preferences = getActivity().getSharedPreferences("MY_APP",Context.MODE_PRIVATE);
        String retrivedToken  = preferences.getString("TOKEN",null);

        Call<UserResponse> call = RetrofitClient.getmInstance().getApi().getUsers(retrivedToken);
        call.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                    userList = response.body().getData();
                    adapter = new UsersAdapter(getActivity(), userList);
                    recyclerView.setAdapter(adapter);

            }
            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Toast.makeText(getContext(),"Failure",Toast.LENGTH_SHORT).show();
            }
        });
    }
}