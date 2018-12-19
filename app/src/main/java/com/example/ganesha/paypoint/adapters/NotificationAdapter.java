package com.example.ganesha.paypoint.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ganesha.paypoint.R;
import com.example.ganesha.paypoint.activities.NotificationDetailActivity;
import com.example.ganesha.paypoint.fragments.NotificationDetailFragment;
import com.example.ganesha.paypoint.model.User;
import com.example.ganesha.paypoint.model.WalletData;
import com.example.ganesha.paypoint.model.WalletReqData;

import java.util.List;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.NotificationViewHolder> {
    private Context mCtx;
    private List<WalletReqData> walletList;

    public NotificationAdapter(Context mCtx, List<WalletReqData> walletList) {
        this.mCtx = mCtx;
        this.walletList = walletList;
    }

    @NonNull
    @Override
    public NotificationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.notification_child, parent, false);
        return new NotificationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationViewHolder holder, int position) {
        final WalletReqData walletData = walletList.get(position);
        holder.tvUserId.setText(walletData.getRequested_by().getName());
        holder.tvAmount.setText(String.valueOf(walletData.getAmount()));

        holder.user_id_name.setText(walletData.getUser_id().getName());
        holder.requested_by_name.setText(walletData.getRequested_by().getName());
        holder._id.setText(walletData.getUser_id().get_id());
        holder.paymentmode.setText(walletData.getPayment_mode());
        holder.chequenumber.setText(walletData.getCheque_no());
        holder.amount.setText(String.valueOf(walletData.getAmount()));
        holder.bankname.setText(walletData.getBank_name());
        holder.depositdate.setText(walletData.getDeposit_date());
        holder.filename.setText(walletData.getFilename());
        holder.filepath.setText(walletData.getFilepath());

    }

    @Override
    public int getItemCount() {
        return walletList.size();
    }

    class NotificationViewHolder extends RecyclerView.ViewHolder {
        TextView tvUserId, tvAmount, tvViewDetails;
        TextView user_id_name, requested_by_name, _id, paymentmode,chequenumber, amount,bankname, depositdate, filename, filepath;
        CardView cardViewNotification;

        public NotificationViewHolder(View itemView) {
            super(itemView);
            tvUserId = itemView.findViewById(R.id.tvUserId);
            tvAmount = itemView.findViewById(R.id.tvAmount);
            cardViewNotification = itemView.findViewById(R.id.cardViewNotification);
            tvViewDetails = itemView.findViewById(R.id.tvViewDetails);

            user_id_name =itemView.findViewById(R.id.user_id_name);
            requested_by_name=itemView.findViewById(R.id.requested_by_name);
            _id =itemView.findViewById(R.id._id);
            paymentmode=itemView.findViewById(R.id.paymentmode);
            chequenumber=itemView.findViewById(R.id.chequenumber);
            amount=itemView.findViewById(R.id.amount);
            bankname=itemView.findViewById(R.id.bankname);
            depositdate=itemView.findViewById(R.id.depositdate);
            filename=itemView.findViewById(R.id.filename);
            filepath=itemView.findViewById(R.id.filepath);

            tvViewDetails.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION) {
                        final WalletReqData walletData = walletList.get(pos);
                        Intent intent = new Intent(mCtx, NotificationDetailActivity.class);
                        intent.putExtra("reqTo",walletData.getUser_id().getName());
                        intent.putExtra("reqBy",walletData.getRequested_by().getName());
                        intent.putExtra("_id",walletData.get_id());
                        intent.putExtra("mode",walletData.getPayment_mode());
                        intent.putExtra("cheque",walletData.getCheque_no());
                        intent.putExtra("amt",String.valueOf(walletData.getAmount()));
                        intent.putExtra("bank",walletData.getBank_name());
                        intent.putExtra("date",walletData.getDeposit_date());
                        intent.putExtra("filepath",walletData.getFilepath());
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        mCtx.startActivity(intent);
                    }

                }
            });


            cardViewNotification.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION) {
                        final WalletReqData walletData = walletList.get(pos);
                        Intent intent = new Intent(mCtx, NotificationDetailActivity.class);
                        intent.putExtra("reqTo",walletData.getUser_id().getName());
                        intent.putExtra("reqBy",walletData.getRequested_by().getName());
                        intent.putExtra("_id",walletData.get_id());
                        intent.putExtra("mode",walletData.getPayment_mode());
                        intent.putExtra("cheque",walletData.getCheque_no());
                        intent.putExtra("amt",String.valueOf(walletData.getAmount()));
                        intent.putExtra("bank",walletData.getBank_name());
                        intent.putExtra("date",walletData.getDeposit_date());
                        intent.putExtra("filepath",walletData.getFilepath());
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        mCtx.startActivity(intent);
                    }

                }
            });

            }
    }
}