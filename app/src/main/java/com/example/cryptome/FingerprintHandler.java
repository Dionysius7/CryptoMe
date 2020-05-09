package com.example.cryptome;


import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.os.CancellationSignal;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

@TargetApi(Build.VERSION_CODES.M)
public class FingerprintHandler extends FingerprintManager.AuthenticationCallback{

    private Context context;
    private int counter = 5;
    private boolean verified=false;

    public FingerprintHandler(Context context){
        this.context=context;
    }

    public void startAuth(FingerprintManager fingerprintManager,FingerprintManager.CryptoObject cryptoObject){
        CancellationSignal cancellationSignal = new CancellationSignal();
        fingerprintManager.authenticate(cryptoObject,cancellationSignal,0,this,null);
    }
    public void onAuthenticationFailed(){
        this.update("Authentication Failed, Try Again",false);
    }

    @Override
    public void onAuthenticationHelp(int helpCode, CharSequence helpString) {
        this.update("Error : " + helpString, false);
    }

    @Override
    public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult result) {
        this.update("Access Verified",true);

        Intent i = new Intent(context,MainActivity.class);
        context.startActivity(i);
        ((Activity)context).finish();
    }

    public void update(String s, boolean b){

        TextView securityText = (TextView) ((Activity)context).findViewById(R.id.securityText);
        ImageView securityImg = (ImageView) ((Activity)context).findViewById(R.id.securityImg);

        securityText.setText(s);

        if(!b)
        {
            if(counter!=0)
            {
                counter--;
                securityText.setTextColor(ContextCompat.getColor(context,R.color.errorColor));
                securityImg.setImageResource(R.drawable.ic_warning_yellow_24dp);
                Toast.makeText(context, "Number of Attempt left : "+ counter, Toast.LENGTH_SHORT).show();
            }
            if(counter==0)
            {
                securityText.setText("Error Too Many Attempt, \nPlease Restart App");
                securityImg.setImageResource(R.drawable.ic_error_red_24dp);
                Toast.makeText(context, "System Error Please Restart App", Toast.LENGTH_SHORT).show();
            }
        }
        else {
            securityText.setTextColor(ContextCompat.getColor(context,R.color.startColor));
            securityImg.setImageResource(R.drawable.ic_check_green_24dp);
        }
    }

}
