package com.example.cryptome.Demo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cryptome.DemoActivity;
import com.example.cryptome.R;

import java.security.MessageDigest;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class AesDemoActivity extends AppCompatActivity {

    private EditText inputAes, keyAes;
    private TextView outputAes;
    private Button btnAesEncrypt, btnAesDecrypt;
    private ImageView btnBackAesDemo;
    private String outputString;
    private String AES = "AES";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aes_demo);

        initView();
        initListener();
    }

    private void initView() {
        inputAes = findViewById(R.id.inputAES);
        keyAes = findViewById(R.id.keyAES);
        outputAes = findViewById(R.id.outputAES);
        btnAesEncrypt = findViewById(R.id.btnAesEncrypt);
        btnAesDecrypt = findViewById(R.id.btnAesDecrypt);
        btnBackAesDemo = findViewById(R.id.backAesDemo);
    }
    private void initListener(){

        btnAesEncrypt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    outputString = encrypt(inputAes.getText().toString(),keyAes.getText().toString());
                    outputAes.setText(outputString);
                }catch(Exception e) {
                    e.printStackTrace();
                }
            }
        });

        btnAesDecrypt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    outputString = decrypt(outputString,keyAes.getText().toString());
                    outputAes.setText(outputString);
                }catch (Exception e){
                    Toast.makeText(AesDemoActivity.this, "Wrong Key", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        });

        btnBackAesDemo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AesDemoActivity.this, DemoActivity.class));
            }
        });

    }
    private String encrypt(String data, String password) throws Exception{
        SecretKeySpec key = generateKey(password);
        Cipher c = Cipher.getInstance(AES);
        c.init(Cipher.ENCRYPT_MODE,key);
        byte[] encVal = c.doFinal(data.getBytes());
        String encryptedValue = Base64.encodeToString(encVal,Base64.DEFAULT);
        return encryptedValue;
    }
    private String decrypt(String data,String password)throws Exception{
        SecretKeySpec key = generateKey(password);
        Cipher c = Cipher.getInstance(AES);
        c.init(Cipher.DECRYPT_MODE,key);
        byte[] decodedValue = Base64.decode(data,Base64.DEFAULT);
        byte[] decValue = c.doFinal(decodedValue);
        String decryptedValue = new String (decValue);
        return decryptedValue;
    }

    private SecretKeySpec generateKey(String password) throws Exception {
        final MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] bytes = password.getBytes("UTF-8");
        digest.update(bytes,0,bytes.length);
        byte[] key = digest.digest();
        SecretKeySpec secretKeySpec = new SecretKeySpec(key,"AES");
        return secretKeySpec;
    }

}
