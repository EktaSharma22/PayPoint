package com.gws.pargati.paypoint.adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.gws.pargati.paypoint.R;
import com.gws.pargati.paypoint.fragments.ServiceDetailFragment;
import com.gws.pargati.paypoint.model.AccessDetails;
import com.gws.pargati.paypoint.model.ServiceCategory;

import java.util.List;

public class RechargeServiceAdapter extends RecyclerView.Adapter<RechargeServiceAdapter.ViewHolder>
    {
        private List<ServiceCategory> items;
        private Context context;

        public RechargeServiceAdapter(Context context, List<ServiceCategory> items)
        {
            this.context = context;
            this.items = items;
        }

        @NonNull
        @Override
        public RechargeServiceAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.service_child, viewGroup, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull RechargeServiceAdapter.ViewHolder viewHolder, int i) {
            final ServiceCategory servicecat=items.get(i);

            viewHolder.title.setText(servicecat.getProvider_name());
            Glide.with(context).load(AccessDetails.serviceurl + servicecat.getImage())
                    .placeholder(R.drawable.loading).into(viewHolder.imageView);


            viewHolder.llService.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    String cat_id = servicecat.get_id();
                    Log.e("cat_id",cat_id);

                    SharedPreferences preferences = context.getSharedPreferences("MY_CAT_ID",Context.MODE_PRIVATE);
                    preferences.edit().putString("CATEGORYID",cat_id).apply();

                    SharedPreferences prefs = context.getSharedPreferences("IMAGE",Context.MODE_PRIVATE);
                    prefs.edit().putString("IMAGEID",AccessDetails.serviceurl + servicecat.getImage()).apply();

                    SharedPreferences pref = context.getSharedPreferences("NAME",Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = pref.edit();
                    editor.putString("PNAME", servicecat.getProvider_name());
                    editor.apply();

                    AppCompatActivity activity = (AppCompatActivity) view.getContext();
                    Fragment myFragment = new ServiceDetailFragment();
                    activity.getSupportFragmentManager().beginTransaction().replace(R.id.rvContainer, myFragment).addToBackStack(null).commit();
                }
            });

        }

        @Override
        public int getItemCount() {
            return items.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            private TextView title;
            private ImageView imageView;
            private LinearLayout llService;

            public ViewHolder(View view) {
                super(view);
                title = (TextView) view.findViewById(R.id.tvService);
                imageView = (ImageView) view.findViewById(R.id.ivService);
                llService = (LinearLayout)view.findViewById(R.id.llService);
                }

            }

        }

