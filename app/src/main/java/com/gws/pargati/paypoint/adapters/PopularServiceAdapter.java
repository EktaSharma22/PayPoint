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

import com.bumptech.glide.Glide;
import com.gws.pargati.paypoint.R;
import com.gws.pargati.paypoint.fragments.PopularServiceFragment;
import com.gws.pargati.paypoint.fragments.ServiceDetailFragment;
import com.gws.pargati.paypoint.model.AccessDetails;
import com.gws.pargati.paypoint.model.PopularService;
import com.gws.pargati.paypoint.model.ServiceCategory;

import java.util.List;

public class PopularServiceAdapter extends RecyclerView.Adapter<PopularServiceAdapter.ViewHolder>
{
    private List<PopularService> items;
    private Context context;

    public PopularServiceAdapter(Context context, List<PopularService> items)
    {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public PopularServiceAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.popularservice_child, viewGroup, false);
        context = viewGroup.getContext();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PopularServiceAdapter.ViewHolder viewHolder, int i) {
        final PopularService servicecat=items.get(i);

        Glide.with(context).load(AccessDetails.serviceurl + "/" +items.get(i).getImage())
                .placeholder(R.drawable.hviz).into(viewHolder.imageView);
        viewHolder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                SharedPreferences pref = context.getSharedPreferences("POPULAR",Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();
                editor.putString("PNAME", servicecat.getProvider_name());
                editor.putString("CATEGORYID", servicecat.get_id());
                editor.putString("IMAGEID",AccessDetails.serviceurl+"/"+servicecat.getImage());
                editor.putString("DENOMINATION",servicecat.getDenomination());
                editor.apply();

                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                Fragment myFragment = new PopularServiceFragment();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.rvContainer, myFragment).addToBackStack(null).commit();

            }
        });

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        public ViewHolder(View view) {
            super(view);

            imageView = (ImageView) view.findViewById(R.id.ivPopularService);
        }

    }
}

