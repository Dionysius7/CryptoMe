package com.example.cryptome;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class IntroActivity extends AppCompatActivity {

    private ViewPager screenPager;
    IntroViewPagerAdapter introViewPagerAdapter;
    Button btnskip,btnnext;
    int position=0;
    Animation btnanim;

    TabLayout tabindicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        //check if user have already read intro once
        if(restorePrefData()){
            startActivity(new Intent(IntroActivity.this,SecurityActivity.class));
            finish();
        }


        //init view
        tabindicator = findViewById(R.id.tab_indicator);
        btnanim = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fadeup);

        final List<ScreenItem> mList = new ArrayList<>();
        mList.add(new ScreenItem("Cryptography"," Who says cryptography is boring? CryptoMe is fun and easy!",R.drawable.bitcoin));
        mList.add(new ScreenItem("Learn Smart","Our smart modules are designed to support a more enjoyable learning",R.drawable.idea));
        mList.add(new ScreenItem("Run, Don't Walk!","Understand more with hands-on experience and exclusive real-time demo",R.drawable.running));

        screenPager = findViewById(R.id.screen_viewpager);
        introViewPagerAdapter = new IntroViewPagerAdapter(IntroActivity.this,mList);
        screenPager.setAdapter(introViewPagerAdapter);

        tabindicator.setupWithViewPager(screenPager);

        //button
        btnnext = findViewById(R.id.btnnext);
        btnskip = findViewById(R.id.btnskip);

        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //open next Activity
                startActivity(new Intent(IntroActivity.this,SecurityActivity.class));
                //if user ever go through intro we should know, using sharedpreferences
                savePrefsData();
                finish();
            }
        });

        btnskip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(IntroActivity.this,SecurityActivity.class));
                //if user ever go through intro we should know, using sharedpreferences
                savePrefsData();
                finish();
                Toast.makeText(IntroActivity.this, "Skipped Introduction", Toast.LENGTH_SHORT).show();
            }
        });

        tabindicator.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if(tab.getPosition()==mList.size()-1)
                {
                    loadLastScreen();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    private boolean restorePrefData() {
        SharedPreferences pref = getApplicationContext().getSharedPreferences("myPrefs",MODE_PRIVATE);
        Boolean isIntroOpenedBefore = pref.getBoolean("isIntroOpened",false);
        return  isIntroOpenedBefore;
    }

    private void savePrefsData(){
        SharedPreferences pref = getApplicationContext().getSharedPreferences("myPrefs",MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean("isIntroOpened",true);
        editor.commit();
    }

    private void loadLastScreen(){
        btnnext.setVisibility(View.VISIBLE);
        tabindicator.setVisibility(View.INVISIBLE);

        btnnext.setAnimation(btnanim);
    }
}
