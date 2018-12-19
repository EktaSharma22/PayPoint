package com.example.ganesha.paypoint.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ganesha.paypoint.model.DefaultResponse;
import com.example.ganesha.paypoint.R;
import com.example.ganesha.paypoint.api.RetrofitClient;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText etNameRegister, etEmailRegister,etMobileRegister, etPasswordRegister;
    private TextView tvAlreadyAccount,tvSignin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etNameRegister = (EditText)findViewById(R.id.etNameRegister);
        etEmailRegister = (EditText)findViewById(R.id.etEmailRegister);
        etPasswordRegister = (EditText)findViewById(R.id.etPasswordRegister);
        etMobileRegister = (EditText)findViewById(R.id.etMobileRegister);
        tvAlreadyAccount =(TextView)findViewById(R.id.tvAlreadyAccount);
        tvSignin = (TextView) findViewById(R.id.tvSignin);

        tvAlreadyAccount.setText(R.string.already_ac);
        tvSignin.setText(R.string.sign_in);

        findViewById(R.id.btnRegister).setOnClickListener(this);
        findViewById(R.id.tvSignin).setOnClickListener(this);
    }

    @Override
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.btnRegister:
                userSignup();
                break;

            case R.id.tvSignin:
                Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }

    private void userSignup()
    {
        String name = etNameRegister.getText().toString().trim();
        String email = etEmailRegister.getText().toString().trim();
        String mobile = etMobileRegister.getText().toString().trim();
        String password = etPasswordRegister.getText().toString().trim();

        if(name.isEmpty())
        {
            etNameRegister.setError("Name is required");
            etNameRegister.requestFocus();
            return;
        }

        if(email.isEmpty())
        {
            etEmailRegister.setError("Email is required");
            etEmailRegister.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            etEmailRegister.setError("Enter a valid Email");
            etEmailRegister.requestFocus();
            return;
        }

        if(mobile.isEmpty())
        {
            etEmailRegister.setError("Mobile Number is required");
            etEmailRegister.requestFocus();
            return;
        }

        if(password.isEmpty())
        {
            etPasswordRegister.setError("Password is required");
            etPasswordRegister.requestFocus();
            return;
        }

        Call<ResponseBody> call = RetrofitClient.getmInstance()
                .getApi()
                .createRegister(name,email,mobile,password);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                String s = null;
                try {
                    if (response.code() == 200)
                    { s = response.body().string();
                    Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                    startActivity(intent);
                    finish(); }
                    else
                        { s = response.errorBody().string(); } }
                catch (IOException e)
                {
                    e.printStackTrace();
                }

                if (s != null)
                {
                    try {
                        JSONObject jsonObject = new JSONObject(s);
                        Toast.makeText(RegisterActivity.this,jsonObject.getString("message"),Toast.LENGTH_SHORT).show();
                    }
                    catch (JSONException e)
                    {
                        e.printStackTrace();
                    }
                }

              /*  if(response.code() == 200)
                { DefaultResponse dr = response.body();
                  Toast.makeText(RegisterActivity.this,dr.getMessage(),Toast.LENGTH_SHORT).show();
                }
                else if(response.code() == 202)
                { DefaultResponse dr = response.body();
                    Toast.makeText(RegisterActivity.this,dr.getMessage(),Toast.LENGTH_SHORT).show();
                }*/

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

                Toast.makeText(RegisterActivity.this,"Error",Toast.LENGTH_SHORT).show();
            }
        });

    }
}
