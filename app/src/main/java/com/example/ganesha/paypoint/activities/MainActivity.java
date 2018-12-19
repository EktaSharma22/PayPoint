package com.example.ganesha.paypoint.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.ganesha.paypoint.R;
import com.example.ganesha.paypoint.storage.SharedPrefManager;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btnRegisterMain).setOnClickListener(this);
        findViewById(R.id.btnLoginMain).setOnClickListener(this);
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
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.btnRegisterMain:
                Intent intent_register = new Intent(MainActivity.this,RegisterActivity.class);
                startActivity(intent_register);
                break;

            case R.id.btnLoginMain:
                Intent intent_login = new Intent(MainActivity.this,LoginActivity.class);
                startActivity(intent_login);
                break;
        }
    }
}
