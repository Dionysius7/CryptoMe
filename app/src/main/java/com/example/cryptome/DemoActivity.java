package com.example.cryptome;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.cryptome.Demo.AesDemoActivity;
import com.example.cryptome.Demo.RsaDemoActivity;

public class DemoActivity extends AppCompatActivity {

    RelativeLayout demoRsa, demoAes;
    ImageView backbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);

        initView();
        initListener();
    }

    private void initView() {
        demoRsa = findViewById(R.id.demoRsa);
        demoAes = findViewById(R.id.demoAes);
        backbtn = findViewById(R.id.backbtn);
    }

    private void initListener() {
        demoRsa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DemoActivity.this, RsaDemoActivity.class));
            }
        });
        demoAes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DemoActivity.this, AesDemoActivity.class));
            }
        });
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DemoActivity.this, MainActivity.class));
            }
        });
    }
}
