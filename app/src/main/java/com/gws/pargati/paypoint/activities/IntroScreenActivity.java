package com.gws.pargati.paypoint.activities;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.gws.pargati.paypoint.R;
import com.gws.pargati.paypoint.adapters.Pager;

import me.relex.circleindicator.CircleIndicator;

public class IntroScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro_screen);

        ViewPager pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(new Pager(getSupportFragmentManager()));
        CircleIndicator indicator = (CircleIndicator)findViewById(R.id.indicator);
        indicator.setViewPager(pager);
    }
}
