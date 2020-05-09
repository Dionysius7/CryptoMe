package com.example.cryptome.Demo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cryptome.DemoActivity;
import com.example.cryptome.R;

import java.math.BigInteger;
import java.util.Map;

public class RsaDemoActivity extends AppCompatActivity {

    private EditText inputRSA, outputRSA;
    private ImageView backRsaDemo;
    private Button btnRsaEncrypt, btnRsaDecrypt;
    private String publicKey = "";
    private String privateKey = "";
    private byte[] encodeData = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rsa_demo);

        initView();
        initData();
        initListener();
    }

    private void initView() {
        inputRSA = findViewById(R.id.inputRSA);
        outputRSA = findViewById(R.id.outputRSA);
        backRsaDemo = findViewById(R.id.backRsaDemo);
        btnRsaEncrypt = findViewById(R.id.btnRsaEncrypt);
        btnRsaDecrypt = findViewById(R.id.btnRsaDecrypt);
    }

    private void initData() {
        try {
            Map<String, Object> keyMap = RsaHandler.initKey();
            publicKey = RsaHandler.getPublicKey(keyMap);
            privateKey = RsaHandler.getPrivateKey(keyMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initListener() {
        backRsaDemo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RsaDemoActivity.this, DemoActivity.class));
            }
        });
        btnRsaEncrypt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                encrypt(v);
            }
        });
        btnRsaDecrypt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                decrypt(v);
            }
        });
    }

    public void encrypt(View v) {
        byte[] rsaData = inputRSA.getText().toString().getBytes();

        try {
            encodeData = RsaHandler.encryptByPublicKey(rsaData, getPublicKey());
            String encodeStr = new BigInteger(1, encodeData).toString();
            outputRSA.setText(encodeStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void decrypt(View v) {
        try {
            byte[] decodeData = RsaHandler.decryptByPrivateKey(encodeData, getPrivateKey());
            String decodeStr = new String(decodeData);
            outputRSA.setText(decodeStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getPublicKey() {
        return publicKey;
    }

    public String getPrivateKey() {
        return privateKey;
    }
}
