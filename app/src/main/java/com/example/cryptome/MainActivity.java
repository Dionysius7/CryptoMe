package com.example.cryptome;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.cryptome.AES.AesActivity;
import com.example.cryptome.RSA.RsaActivity;

public class MainActivity extends AppCompatActivity {

    RelativeLayout rRsa, rAes, rAbout, rDemo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initListener();

    }


    private void initView() {
        rRsa = findViewById(R.id.rRsa);
        rAes = findViewById(R.id.rAes);
        rAbout = findViewById(R.id.rAbout);
        rDemo = findViewById(R.id.rDemo);
    }

    private void initListener() {
        rRsa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, RsaActivity.class));
            }
        });
        rAes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AesActivity.class));
            }
        });
        rDemo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, DemoActivity.class));
            }
        });
        rAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AboutActivity.class));
            }
        });

    }
}
