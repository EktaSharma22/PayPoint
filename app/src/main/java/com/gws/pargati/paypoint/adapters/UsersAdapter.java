package com.gws.pargati.paypoint.adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.gws.pargati.paypoint.R;
import com.gws.pargati.paypoint.api.RetrofitClient;
import com.gws.pargati.paypoint.model.GetUsersData;
import com.gws.pargati.paypoint.model.MakeDealerResponse;
import com.gws.pargati.paypoint.storage.SharedPrefManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.UsersViewHolder> {
    private Context mCtx;
    private List<GetUsersData> userList;

    public UsersAdapter(Context mCtx, List<GetUsersData> userList) {
        this.mCtx = mCtx;
        this.userList = userList;
    }

    @NonNull
    @Override
    public UsersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.recyclerview_users, parent, false);
        return new UsersViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UsersViewHolder holder, final int position) {
        final GetUsersData user = userList.get(position);
        String name = user.getName().substring(0,1).toUpperCase() + user.getName().substring(1);
        holder.textViewName.setText(name);
        holder.textViewEmail.setText(user.getEmail());
        holder.btnMakeDealer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Toast.makeText(mCtx,"ID of user"+ user.getId(),Toast.LENGTH_SHORT).show();
                String user_id = user.get_id();
                SharedPreferences preferences = mCtx.getSharedPreferences("MY_APP",Context.MODE_PRIVATE);
                final String retrivedToken  = preferences.getString("TOKEN",null);

                Call<MakeDealerResponse> call = RetrofitClient.getmInstance()
                        .getApi()
                        .changestatus(retrivedToken,user_id);

                call.enqueue(new Callback<MakeDealerResponse>() {
                    @Override
                    public void onResponse(Call<MakeDealerResponse> call, Response<MakeDealerResponse> response) {

                        MakeDealerResponse mr = response.body();

                        try {
                            Toast.makeText(mCtx,mr.getMessage(),Toast.LENGTH_SHORT).show();
                            removeAt(position);
                            notifyItemRemoved(position);

                        } catch (Exception e) {
                            try {
                                Toast.makeText(mCtx,"Already a Dealer",Toast.LENGTH_SHORT).show();
                                removeAt(position);
                                notifyItemRemoved(position);
                            } catch (Exception e1) {
                                e1.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<MakeDealerResponse> call, Throwable t) {
                        Log.e("ERror", "NET_ERROR:" + t.toString());
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    class UsersViewHolder extends RecyclerView.ViewHolder {
        TextView textViewName, textViewEmail;
        Button btnMakeDealer;

        public UsersViewHolder(View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.textViewName);
            textViewEmail = itemView.findViewById(R.id.textViewEmail);
            btnMakeDealer = itemView.findViewById(R.id.btnMakeDealer);

            String user_type = SharedPrefManager.getInstance(mCtx).getUser().getUser_type();
            if(user_type.equals("user"))
            {
                btnMakeDealer.setVisibility(View.GONE);
            }
            if(user_type.equals("dealer"))
            {
                btnMakeDealer.setVisibility(View.GONE);
            }
            if(user_type.equals("admin"))
            {
                btnMakeDealer.setVisibility(View.VISIBLE);
            }

        }
    }

    public void removeAt(int position) {
        userList.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, userList.size());
        notifyDataSetChanged();
    }
}