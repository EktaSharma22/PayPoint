package com.example.ganesha.paypoint.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.ganesha.paypoint.R;
import com.example.ganesha.paypoint.activities.DthActivity;
import com.example.ganesha.paypoint.activities.HomeUtilitiesActivity;
import com.example.ganesha.paypoint.activities.RechargeBillActivity;
import com.example.ganesha.paypoint.fragments.RechargeBillFragment;
import com.example.ganesha.paypoint.model.AccessDetails;
import com.example.ganesha.paypoint.model.Category;
import com.example.ganesha.paypoint.model.CategoryResponse;
import com.example.ganesha.paypoint.model.RechargeServices;
import com.example.ganesha.paypoint.model.ServiceCategory;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

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
            ServiceCategory servicecat=items.get(i);

            viewHolder.title.setText(servicecat.getProvider_name());
            Glide.with(context).load(AccessDetails.serviceurl + servicecat.getImage())
                    .placeholder(R.drawable.loading).into(viewHolder.imageView);

        }

        @Override
        public int getItemCount() {
            return items.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            private TextView title;
            private ImageView imageView;

            public ViewHolder(View view) {
                super(view);
                title = (TextView) view.findViewById(R.id.tvService);
                imageView = (ImageView) view.findViewById(R.id.ivService);
                }

            }

        }

