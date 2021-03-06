package com.gws.pargati.paypoint.adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.gws.pargati.paypoint.R;
import com.gws.pargati.paypoint.fragments.RechargeBillFragment;
import com.gws.pargati.paypoint.model.AccessDetails;
import com.gws.pargati.paypoint.model.Category;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder>
{
    private List<Category> items;
    private Context context;

    public ItemAdapter(Context context, List<Category> items)
    {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public ItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_user, viewGroup, false);
        context = viewGroup.getContext();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemAdapter.ViewHolder viewHolder, int i) {
        viewHolder.title.setText(items.get(i).getCategory_name());
       /* Picasso.with(context)
                .load(items.get(i).getImage())
                .placeholder(R.drawable.load)
                .into(viewHolder.imageView);*/
       /* Glide.with(context)
                .load(items.get(i).getImage())
                .into(viewHolder.imageView);
*/
            Glide.with(context).load(AccessDetails.serviceurl + items.get(i).getImage())
                    .placeholder(R.drawable.loading).into(viewHolder.imageView);

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private ImageView imageView;
        private RelativeLayout rrlayout;


        public ViewHolder(View view) {
            super(view);
         //   mCtx = itemView.getContext();
            title = (TextView) view.findViewById(R.id.title);
            imageView = (ImageView) view.findViewById(R.id.cover);

            rrlayout = (RelativeLayout)view.findViewById(R.id.rrlayout);
            rrlayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    String id = items.get(getAdapterPosition()).get_id();
                //    Toast.makeText(context,id,Toast.LENGTH_SHORT).show();

                    SharedPreferences pref = context.getSharedPreferences("MyPrefCat", 0); // 0 - for private mode
                    SharedPreferences.Editor editor = pref.edit();
                    editor.putString("category_id", items.get(getAdapterPosition()).get_id());
                    editor.commit();

                    AppCompatActivity activity = (AppCompatActivity) view.getContext();
                    Fragment myFragment = new RechargeBillFragment();
                    activity.getSupportFragmentManager().beginTransaction().replace(R.id.rvContainer, myFragment).addToBackStack(null).commit();

                    /*Intent intent = new Intent(context,RechargeBillActivity.class);
                    context.startActivity(intent);
*/
                }
            });



       /*     itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION) {
                        Category clickDataItem = items.get(pos);
                        Intent intent = new Intent(context, DetailActivity.class);
                        intent.putExtra("login", items.get(pos).getLogin());
                        intent.putExtra("html_url", items.get(pos).getHtmlUrl());
                        intent.putExtra("avatar_url", items.get(pos).getAvatarUrl());
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                        Toast.makeText(view.getContext(), "You Clicked" + clickDataItem.getLogin(), Toast.LENGTH_SHORT).show();


                    }
                }
            });*/
        }

    }
}
