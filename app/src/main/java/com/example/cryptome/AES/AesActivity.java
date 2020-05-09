package com.example.cryptome.AES;

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

import com.example.cryptome.Demo.AesDemoActivity;
import com.example.cryptome.IntroViewPagerAdapter;
import com.example.cryptome.R;
import com.example.cryptome.ScreenItem;
import com.example.cryptome.SecurityActivity;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class AesActivity extends AppCompatActivity {

    private ViewPager screenPager;
    AesViewPagerAdapter AesViewPagerAdapter;
    Button btnnext;
    int position=0;
    Animation btnanim;

    TabLayout tabindicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aes);

        //init view
        tabindicator = findViewById(R.id.tab_indicator);
        btnanim = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fadeup);

        final List<AesItem> mList = new ArrayList<>();
        mList.add(new AesItem("Step 1","Derive the set of round keys from the cipher key.",R.drawable.aes1));
        mList.add(new AesItem("Step 2","Initialize the state array with the block data (plaintext).",R.drawable.aes2));
        mList.add(new AesItem("Step 3","Add the initial round key to the starting state array.",R.drawable.aes3));
        mList.add(new AesItem("Step 4","Perform nine rounds of state manipulation.",R.drawable.aes4));
        mList.add(new AesItem("Step 5","Perform the tenth and final round of state manipulation.",R.drawable.aes4));
        mList.add(new AesItem("Step 6","Copy the final state array out as the encrypted data (ciphertext).",R.drawable.rsa8));

        btnnext = findViewById(R.id.btnnext);
        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AesActivity.this, "AES Demo", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(AesActivity.this, AesDemoActivity.class));
            }
        });

        screenPager = findViewById(R.id.screen_viewpager);
        AesViewPagerAdapter = new AesViewPagerAdapter(AesActivity.this,mList);
        screenPager.setAdapter(AesViewPagerAdapter);

        tabindicator.setupWithViewPager(screenPager);


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

    private void loadLastScreen(){
        btnnext.setVisibility(View.VISIBLE);
        tabindicator.setVisibility(View.INVISIBLE);

        btnnext.setAnimation(btnanim);
    }
}
