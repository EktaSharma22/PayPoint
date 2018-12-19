package com.example.ganesha.paypoint.adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ganesha.paypoint.R;
import com.example.ganesha.paypoint.model.UploadUserTypeResponse;

import java.util.List;

public class UploadDealerUserAdapter extends RecyclerView.Adapter<UploadDealerUserAdapter.DealerUserViewHolder> {
    private Context mCtx;
    private List<UploadUserTypeResponse.DealerUsers> walletList;
    private SparseBooleanArray selectedItems;

    public UploadDealerUserAdapter(Context mCtx, List<UploadUserTypeResponse.DealerUsers> walletList) {
        this.mCtx = mCtx;
        this.walletList = walletList;
    }

    @NonNull
    @Override
    public DealerUserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.upload_dealeruser_child, parent, false);
        return new DealerUserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final DealerUserViewHolder holder, int position) {
        final UploadUserTypeResponse.DealerUsers walletData = walletList.get(position);
        holder.tvUserId.setText(walletData.getName());
        holder.cvSelectUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.cvSelectUser.setSelected(true);
                Toast.makeText(mCtx,"ID of user"+ walletData.get_id(),Toast.LENGTH_SHORT).show();
                String id = walletData.get_id();
                String dealer_id = walletData.get_id();

                SharedPreferences preferencesID = mCtx.getSharedPreferences("MY_APP_ID",Context.MODE_PRIVATE);
                preferencesID.edit().putString("USERID",id).apply();

                SharedPreferences preferencesDealerID = mCtx.getSharedPreferences("MY_APP_DEALER_ID",Context.MODE_PRIVATE);
                preferencesDealerID.edit().putString("DEALERID",dealer_id).apply();

            }
        });
    }

    @Override
    public int getItemCount() {
        return walletList.size();
    }

    class DealerUserViewHolder extends RecyclerView.ViewHolder {
        TextView tvUserId;
        CardView cvSelectUser;
        LinearLayout llCardView;

        public DealerUserViewHolder(View itemView) {
            super(itemView);
            tvUserId = itemView.findViewById(R.id.tvUserId);
            cvSelectUser = itemView.findViewById(R.id.cvSelectUser);
            llCardView = itemView.findViewById(R.id.llCardView);

        }
    }
}