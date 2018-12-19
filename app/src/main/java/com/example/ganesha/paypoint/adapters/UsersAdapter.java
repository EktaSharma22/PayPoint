package com.example.ganesha.paypoint.adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ganesha.paypoint.R;
import com.example.ganesha.paypoint.api.RetrofitClient;
import com.example.ganesha.paypoint.model.GetUsersData;
import com.example.ganesha.paypoint.model.MakeDealerResponse;
import com.example.ganesha.paypoint.model.User;
import com.example.ganesha.paypoint.model.UserResponse;
import com.example.ganesha.paypoint.storage.SharedPrefManager;

import java.io.IOException;
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
        holder.textViewName.setText(user.getName());
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

                        } catch (Exception e) {
                            try {
                                Toast.makeText(mCtx,"Already a Dealer",Toast.LENGTH_SHORT).show();
                                removeAt(position);
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