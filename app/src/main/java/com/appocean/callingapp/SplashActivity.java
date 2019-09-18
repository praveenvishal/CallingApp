package com.appocean.callingapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.airbnb.lottie.TextDelegate;
import com.appocean.callingapp.databinding.ActivitySplashBinding;
import com.appocean.callingapp.phonenumberui.PhoneNumberActivity;
import com.appocean.callingapp.util.PrefConstant;
import com.appocean.callingapp.util.SessionManager;
import com.appocean.callingapp.util.Util;

public class SplashActivity extends AppCompatActivity {

    private static final long SPLASH_TIME = 10000;
    ActivitySplashBinding mBinding;
    TextDelegate textDelegate;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_splash);
        mBinding.splashAnimation.playAnimation();
        textDelegate = new TextDelegate(mBinding.splashAnimation);
        mBinding.splashAnimation.setTextDelegate(textDelegate);
        textDelegate.setText("Zello", "TALKATIVE");
        navigate();
    }

    private void navigate() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                String uId = SessionManager.getInstance().getString(PrefConstant.USER_ID);
                boolean isFirstTimeInstall = Util.isFirstTimeInstall();
                if (!TextUtils.isEmpty(uId)) {
                    Intent intent = new Intent(SplashActivity.this, EnterDetailsActivity.class);
                    startActivity(intent);
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                } else {
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
