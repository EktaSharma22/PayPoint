package com.gws.pargati.paypoint.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.gws.pargati.paypoint.R;
import com.gws.pargati.paypoint.api.RetrofitClient;
import com.gws.pargati.paypoint.model.AccessDetails;
import com.gws.pargati.paypoint.model.WalletApproveResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotificationDetailActivity extends AppCompatActivity implements View.OnClickListener{

    ImageView ivBack, ivAddUsers;
    TextView tvTitle;

    private TextView tvRequestedTo, tvRequestedBy, tvID, tvPaymentMode, tvChequeNumber, tvAmount, tvBankName, tvDepositDate, tvFileName;
    private ImageView ivImage;
    private Button btnWalletApprove,btnWalletDecline;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_detail);

        ivImage = (ImageView)findViewById(R.id.ivImage);
        tvRequestedTo = (TextView)findViewById(R.id.tvRequestedTo);
        tvRequestedBy = (TextView)findViewById(R.id.tvRequestedBy);
        tvID = (TextView)findViewById(R.id.tvID);
        tvPaymentMode = (TextView)findViewById(R.id.tvPaymentMode);
        tvChequeNumber = (TextView)findViewById(R.id.tvChequeNumber);
        tvAmount = (TextView)findViewById(R.id.tvAmount);
        tvBankName = (TextView)findViewById(R.id.tvBankName);
        tvDepositDate = (TextView)findViewById(R.id.tvDepositDate);
        tvFileName = (TextView)findViewById(R.id.tvFileName);
        btnWalletApprove = (Button)findViewById(R.id.btnWalletApprove);
        btnWalletDecline = (Button)findViewById(R.id.btnWalletDecline);

        ivBack = (ImageView)findViewById(R.id.ivBack);
        ivAddUsers = (ImageView)findViewById(R.id.ivAddUsers);
        tvTitle = (TextView)findViewById(R.id.tvTitle);

        ivAddUsers.setVisibility(View.GONE);
        tvTitle.setText("Notification Detail");

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        String reqTo = getIntent().getExtras().getString("reqTo");
        String reqBy = getIntent().getExtras().getString("reqBy");
        String _id = getIntent().getExtras().getString("_id");
        String mode = getIntent().getExtras().getString("mode");
        String cheque = getIntent().getExtras().getString("cheque");
        String amt = getIntent().getExtras().getString("amt");
        String bank = getIntent().getExtras().getString("bank");
        String date = getIntent().getExtras().getString("date");
        String filepath = getIntent().getExtras().getString("filepath");


        tvRequestedTo.setText(reqTo);
        tvRequestedBy.setText(reqBy);
        tvID.setText(_id);
        tvPaymentMode.setText(mode);
        tvChequeNumber.setText(cheque);
        tvAmount.setText(amt);
        tvBankName.setText(bank);
        tvDepositDate.setText(date);

        Glide.with(this).load(AccessDetails.serviceurl +"/"+filepath)
                .placeholder(R.drawable.load).into(ivImage);

        btnWalletApprove.setOnClickListener(this);
        btnWalletDecline.setOnClickListener(this);

    }

    @Override
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.btnWalletApprove:

                SharedPreferences preferences = this.getSharedPreferences("MY_APP",Context.MODE_PRIVATE);
                final String retrivedToken  = preferences.getString("TOKEN",null);
                String id = getIntent().getExtras().getString("_id");

                Call<WalletApproveResponse> call = RetrofitClient.getmInstance()
                        .getApi()
                        .walletApprove(retrivedToken,id);

                call.enqueue(new Callback<WalletApproveResponse>() {
                    @Override
                    public void onResponse(Call<WalletApproveResponse> call, Response<WalletApproveResponse> response) {

                        WalletApproveResponse wr = response.body();
                        Toast.makeText(NotificationDetailActivity.this,wr.getMessage(),Toast.LENGTH_SHORT).show();
                        dashBoard();
                    }

                    @Override
                    public void onFailure(Call<WalletApproveResponse> call, Throwable t) {
                       Toast.makeText(NotificationDetailActivity.this,"Failure !!",Toast.LENGTH_SHORT).show();
                    }
                });

                break;

            case R.id.btnWalletDecline:
                Toast.makeText(NotificationDetailActivity.this,"Your Request has been been Declined",Toast.LENGTH_SHORT).show();
                break;
        }

    }

    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent(NotificationDetailActivity.this,DashboardActivity.class);
        startActivity(intent);
        finish();

    }

    private void dashBoard()
    {
        Intent intent = new Intent(NotificationDetailActivity.this,DashboardActivity.class);
        startActivity(intent);
        finish();
    }


}
