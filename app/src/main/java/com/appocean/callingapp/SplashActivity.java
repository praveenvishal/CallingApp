package com.appocean.callingapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.appocean.callingapp.phonenumberui.PhoneNumberActivity;
import com.appocean.callingapp.util.PrefConstant;
import com.appocean.callingapp.util.SessionManager;
import com.appocean.callingapp.util.Util;

public class SplashActivity extends AppCompatActivity {

    private static final long SPLASH_TIME = 3000;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        navigate();

    }

    private void navigate() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                String uId = SessionManager.getInstance().getString(PrefConstant.USER_ID);
                if (!TextUtils.isEmpty(uId)) {
                    Intent intent = new Intent(SplashActivity.this, CreateRoomActivity.class);
                    startActivity(intent);
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);

                } else {
                    boolean isFirstTimeInstall = Util.isFirstTimeInstall();
                    if (isFirstTimeInstall) {
                        Intent intent = new Intent(SplashActivity.this, TutorialActivity.class);
                        startActivity(intent);
                        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                    } else {
                        Intent intent = new Intent(SplashActivity.this, PhoneNumberActivity.class);
                        startActivity(intent);
                        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                    }


                }

                finish();


            }
        }, SPLASH_TIME);

    }
}
