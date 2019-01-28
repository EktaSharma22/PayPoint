package com.gws.pargati.paypoint.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gws.pargati.paypoint.R;
import com.gws.pargati.paypoint.model.UploadUserTypeResponse;

import java.util.List;

public class DealerUsersAdapter extends RecyclerView.Adapter<DealerUsersAdapter.DealerUserViewHolder> {
    private Context mCtx;
    private List<UploadUserTypeResponse.DealerUsers> walletList;

    public DealerUsersAdapter(Context mCtx, List<UploadUserTypeResponse.DealerUsers> walletList) {
        this.mCtx = mCtx;
        this.walletList = walletList;
    }

    @NonNull
    @Override
    public DealerUserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.dealeruser_child, parent, false);
        return new DealerUserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DealerUserViewHolder holder, int position) {
        final UploadUserTypeResponse.DealerUsers walletData = walletList.get(position);
        String name = walletData.getName().substring(0,1).toUpperCase() + walletData.getName().substring(1);
        holder.tvUserId.setText(name);
        holder.tvEmail.setText(walletData.getEmail());
    }

    @Override
    public int getItemCount() {
        return walletList.size();
    }

    class DealerUserViewHolder extends RecyclerView.ViewHolder {
        TextView tvUserId, tvEmail, tvViewDetails;

        public DealerUserViewHolder(View itemView) {
            super(itemView);
            tvUserId = itemView.findViewById(R.id.tvUserId);
            tvEmail = itemView.findViewById(R.id.tvEmail);
        }
    }
}