package com.example.cryptome;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.KeyguardManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class SecurityActivity extends AppCompatActivity {

    private ImageView msecurityImg,mhpimg;
    private TextView msecurityText,minserttext;
    private FingerprintManager fingerprintManager;
    private KeyguardManager keyguardManager;
    private Animation animation;
    private Button btnFinger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_security);

        msecurityImg = (ImageView) findViewById(R.id.securityImg);
        mhpimg = (ImageView) findViewById(R.id.hpimg);
        msecurityText = (TextView) findViewById(R.id.securityText);
        minserttext = (TextView) findViewById(R.id.insertText);
        btnFinger = (Button) findViewById(R.id.btnFinger);

        // For Animation
        animation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fadeup);


//         TODO 1: Android version should be greater or equal to marshmellow
//         TODO 2: Device has fingerprint scanner
//         TODO 3: Have permission to use fingerprint in the app
//         TODO 4: LockScreen is secured with at least 1 type of lock
//         TODO 5: At least 1 fingerprint is registered

        btnFinger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SecurityActivity.this,MainActivity.class));
            }
        });

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            //setting animation
            mhpimg.setAnimation(animation);
            minserttext.setAnimation(animation);
            msecurityText.setAnimation(animation);
            msecurityImg.setAnimation(animation);
            btnFinger.setAnimation(animation);

            fingerprintManager = (FingerprintManager) getSystemService(FINGERPRINT_SERVICE);
            keyguardManager = (KeyguardManager) getSystemService(KEYGUARD_SERVICE);

            if(!fingerprintManager.isHardwareDetected())
            {
                msecurityText.setText("Fingerprint Scanner not Detected on this Device");
                startActivity(new Intent(SecurityActivity.this,MainActivity.class));
            }
            else if(ContextCompat.checkSelfPermission(this, Manifest.permission.USE_FINGERPRINT)!= PackageManager.PERMISSION_GRANTED)
            {
                msecurityText.setText("Permission not Granted");
            }
            else if(!keyguardManager.isKeyguardSecure())
            {
                msecurityText.setText("Add Lock to your Phone in Settings");
            }
            else if(!fingerprintManager.hasEnrolledFingerprints())
            {
                msecurityText.setText("No Fingerprint Registered on this Device");
            }
            else{
                msecurityText.setText("Please Verify Your Fingerprint");
                FingerprintHandler fingerprintHandler = new FingerprintHandler(this);
                fingerprintHandler.startAuth(fingerprintManager,null);
            }
        }
    }
}
