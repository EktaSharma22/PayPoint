package com.example.ganesha.paypoint.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.ganesha.paypoint.R;
import com.example.ganesha.paypoint.fragments.ChangePaswordFragment;
import com.example.ganesha.paypoint.fragments.WalletCommisionFragment;
import com.example.ganesha.paypoint.model.RechargeServices;

import java.util.ArrayList;

public class SettingsAdapter extends RecyclerView.Adapter<SettingsAdapter.SettingsViewHolder>
{
    private ArrayList<RechargeServices> mSettingList;

    @NonNull
    @Override
    public SettingsAdapter.SettingsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_settings_child,viewGroup,false);
        SettingsAdapter.SettingsViewHolder settingsViewHolder = new SettingsAdapter.SettingsViewHolder(v);
        return settingsViewHolder;
    }

    public SettingsAdapter(ArrayList<RechargeServices> settingList)
    {
        mSettingList = settingList;
    }

    @Override
    public void onBindViewHolder(@NonNull SettingsAdapter.SettingsViewHolder rechargeViewHolder, int i)
    {
        RechargeServices rechargeServices = mSettingList.get(i);
        rechargeViewHolder.mImageView.setImageResource(rechargeServices.getImage());
        rechargeViewHolder.mTextRechargeService.setText(rechargeServices.getTitle());

    }

    @Override
    public int getItemCount() {
        return mSettingList.size();
    }

    public class SettingsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        private ImageView mImageView;
        private TextView mTextRechargeService;
        private RelativeLayout rvContainer;
        private final Context context;

        public SettingsViewHolder(@NonNull View itemView)
        {
            super(itemView);
            context = itemView.getContext();
            mImageView = itemView.findViewById(R.id.imgSettings);
            mTextRechargeService = itemView.findViewById(R.id.tvSettingName);
            rvContainer = itemView.findViewById(R.id.rvSettingsContainer);
            rvContainer.setOnClickListener(this);

        }

        @Override
        public void onClick(View view)
        {
            //final Intent intent;
            switch (getAdapterPosition()){
                case 0:
                    AppCompatActivity activity1 = (AppCompatActivity) view.getContext();
                    WalletCommisionFragment myFragment1 = new WalletCommisionFragment();
                    activity1.getSupportFragmentManager().beginTransaction().replace(R.id.rvContainer, myFragment1).addToBackStack(null).commit();
                    break;

                case 1:
                    AppCompatActivity activity = (AppCompatActivity) view.getContext();
                    ChangePaswordFragment myFragment = new ChangePaswordFragment();
                    activity.getSupportFragmentManager().beginTransaction().replace(R.id.rvContainer, myFragment).addToBackStack(null).commit();
                    break;

                default:
                //    intent =  new Intent(context, WalletCommissionActivity.class);
                    break;
            }
          //  context.startActivity(intent);

        }
    }

}
