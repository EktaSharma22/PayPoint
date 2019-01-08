package com.gws.pargati.paypoint.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.gws.pargati.paypoint.R;
import com.gws.pargati.paypoint.Singleton.GlobalApplication;
import com.gws.pargati.paypoint.api.RetrofitClient;
import com.gws.pargati.paypoint.model.LoginResponse;
import com.gws.pargati.paypoint.storage.SharedPrefManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText etEmail, etPassword;
    private TextView tvSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etEmail = (EditText)findViewById(R.id.etEmail);
        etPassword = (EditText)findViewById(R.id.etPassword);
        tvSignUp = (TextView)findViewById(R.id.tvSignin);

        findViewById(R.id.btnLogin).setOnClickListener(this);
        findViewById(R.id.tvSignin).setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();

        if(SharedPrefManager.getInstance(this).isLoggedIn())
        {
            Intent intent = new Intent(this,DashboardActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }

    @Override
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.btnLogin:
                userLogin();
                break;

            case R.id.tvSignin:
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }

    private void userLogin()
    {
       // String device_token = GlobalApplication.getInstance().getDevice_token();
        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        if(email.isEmpty())
        {
            etEmail.setError("Email or Phone Number is required");
            etEmail.requestFocus();
            return;
        }

       /* if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            etEmail.setError("Enter a valid Email");
            etEmail.requestFocus();
            return;
        }*/

        if(password.isEmpty())
        {
            etPassword.setError("Password is required");
            etPassword.requestFocus();
            return;
        }

        SharedPreferences preferences = LoginActivity.this.getSharedPreferences("DEVICE_TOKEN",Context.MODE_PRIVATE);
        String device_token  = preferences.getString("DTOKEN",null);

        Call<LoginResponse> call = RetrofitClient.getmInstance()
                .getApi()
                .createLogin(device_token,email,password);

        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {


                LoginResponse lr = response.body();
              //  Toast.makeText(LoginActivity.this,lr.getMessage(),Toast.LENGTH_SHORT).show();

                if(response.code() == 200)
                {
                    if(lr.isStatus())
                    {
                        SharedPrefManager.getInstance(LoginActivity.this).saveUser(lr.getUser());
                        String token  = lr.getToken();

                        SharedPreferences preferences = LoginActivity.this.getSharedPreferences("MY_APP",Context.MODE_PRIVATE);
                        preferences.edit().putString("TOKEN",token).apply();

                        Toast.makeText(LoginActivity.this,lr.getMessage(),Toast.LENGTH_SHORT).show();

                        SharedPreferences preferences1 = PreferenceManager.getDefaultSharedPreferences(LoginActivity.this);
                        SharedPreferences.Editor editor = preferences1.edit();
                        editor.putString("token", token);
                        editor.commit();

                        Intent intent = new Intent(LoginActivity.this,DashboardActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);

                    }else {

                        Toast.makeText(LoginActivity.this,lr.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    Toast.makeText(LoginActivity.this,"Invalid Email or Password",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {

                Toast.makeText(LoginActivity.this,"Failure",Toast.LENGTH_SHORT).show();

            }
        });

    }
}
