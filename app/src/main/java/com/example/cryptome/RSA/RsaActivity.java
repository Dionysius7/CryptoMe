package com.example.cryptome.RSA;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Toast;

import com.example.cryptome.Demo.RsaDemoActivity;
import com.example.cryptome.R;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class RsaActivity extends AppCompatActivity {

    private ViewPager screenPager;
    RsaViewPagerAdapter RsaViewPagerAdapter;
    Button btnnext;
    int position=0;
    Animation btnanim;

    TabLayout tabindicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rsa);

        //init view
        tabindicator = findViewById(R.id.tab_indicator);
        btnanim = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fadeup);

        final List<RsaItem> mList = new ArrayList<>();
        mList.add(new RsaItem("Step 1","Select p, q where p & q both prime, p≠q",R.drawable.rsa1));
        mList.add(new RsaItem("Step 2","Calculate n = p × q",R.drawable.rsa2));
        mList.add(new RsaItem("Step 3","Calculate Ø(n) = (p Calculate Ø(n) = (p-1) × (q-1)",R.drawable.rsa2));
        mList.add(new RsaItem("Step 4","Select integer e such that gcd(Ø(n),e)=1; 1<e< Ø(n)",R.drawable.rsa1));
        mList.add(new RsaItem("Step 5","Calculate d, d ≡ e-1 (mod Ø(n)) or d.e ≡ 1 (mod Ø(n))",R.drawable.rsa5));
        mList.add(new RsaItem("Step 6","Publish public key PU={e,n}",R.drawable.rsa6));
        mList.add(new RsaItem("Step 7","Keep secret private key PR={d,n}",R.drawable.rsa7));
        mList.add(new RsaItem("Step 8","Encryption: C = (M ^ e) mod n",R.drawable.rsa8));
        mList.add(new RsaItem("Step 9","Decryption: M = (C ^ d) mod n",R.drawable.rsa9));

        btnnext = findViewById(R.id.btnnext);
        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(RsaActivity.this, "RSA Demo", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(RsaActivity.this, RsaDemoActivity.class));
            }
        });

        screenPager = findViewById(R.id.screen_viewpager);
        RsaViewPagerAdapter = new RsaViewPagerAdapter(RsaActivity.this,mList);
        screenPager.setAdapter(RsaViewPagerAdapter);

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
