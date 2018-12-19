package com.example.ganesha.paypoint.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ganesha.paypoint.R;
import com.example.ganesha.paypoint.api.RetrofitClient;
import com.example.ganesha.paypoint.model.AddUserResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddUsersFragment  extends Fragment implements View.OnClickListener{

    private EditText etUserName,etUserEmail,etUserPassword,etUserMobile;
    private Button buttonAddUser;
    ImageView ivBack, ivAddUsers;
    TextView tvTitle;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.add_user_frag,container,false);

        ivBack = (ImageView)view.findViewById(R.id.ivBack);
        ivAddUsers = (ImageView)view.findViewById(R.id.ivAddUsers);
        tvTitle = (TextView)view.findViewById(R.id.tvTitle);

        ivAddUsers.setVisibility(View.GONE);
        tvTitle.setText("Add Users");

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });

        etUserName = (EditText)view.findViewById(R.id.etUserName);
        etUserEmail = (EditText)view.findViewById(R.id.etUserEmail);
        etUserPassword =(EditText)view.findViewById(R.id.etUserPassword);
        etUserMobile = (EditText)view.findViewById(R.id.etUserMobile);

        view.findViewById(R.id.buttonAddUser).setOnClickListener(this);

        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.buttonAddUser:
                addUser();
                break;

        }

    }

    private void addUser()
    {
        String name = etUserName.getText().toString().trim();
        String email = etUserEmail.getText().toString().trim();
        String password = etUserPassword.getText().toString().trim();
        String mobile = etUserMobile.getText().toString().trim();

        if(name.isEmpty())
        {
            etUserName.setError("Email is required");
            etUserName.requestFocus();
            return;
        }

        if(email.isEmpty())
        {
            etUserEmail.setError("Email is required");
            etUserEmail.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            etUserEmail.setError("Enter a valid Email");
            etUserEmail.requestFocus();
            return;
        }

        if(password.isEmpty())
        {
            etUserPassword.setError("Password is required");
            etUserPassword.requestFocus();
            return;
        }

        if(mobile.isEmpty())
        {
            etUserEmail.setError("Phone Number is required");
            etUserEmail.requestFocus();
            return;
        }


        SharedPreferences preferences = getActivity().getSharedPreferences("MY_APP",Context.MODE_PRIVATE);
        String retrivedToken  = preferences.getString("TOKEN",null);

        Call<AddUserResponse> call = RetrofitClient.getmInstance()
                .getApi()
                .addNewUser(retrivedToken,name,email,password,mobile);

        call.enqueue(new Callback<AddUserResponse>() {
            @Override
            public void onResponse(Call<AddUserResponse> call, Response<AddUserResponse> response) {
                if(response.code() == 200)
                {
                    AddUserResponse lr = response.body();

                    if(lr.isStatus())
                    {
                        Toast.makeText(getContext(),lr.getMessage(),Toast.LENGTH_SHORT).show();

                    }else {

                        Toast.makeText(getContext(),lr.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                }
                if(response.code() == 205)
                {
                    Toast.makeText(getContext(),"Email not Confirmed",Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<AddUserResponse> call, Throwable t) {

                Toast.makeText(getContext(),"Failure",Toast.LENGTH_SHORT).show();

            }
        });


    }

}



