package com.appocean.callingapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;

import com.appocean.callingapp.databinding.ActivitySplashBinding;
import com.appocean.callingapp.phonenumberui.PhoneNumberActivity;
import com.appocean.callingapp.util.PrefConstant;
import com.appocean.callingapp.util.SessionManager;
import com.appocean.callingapp.util.Util;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

public class SplashActivity extends AppCompatActivity {

    private static final long SPLASH_TIME = 10000;
    ActivitySplashBinding mBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_splash);
        mBinding.splashAnimation1.playAnimation();
        mBinding.splashAnimation2.playAnimation();
        mBinding.splashAnimation3.playAnimation();
        mBinding.splashAnimation4.playAnimation();
        mBinding.splashAnimation5.playAnimation();
        mBinding.splashAnimation10.playAnimation();
        mBinding.splashAnimation7.playAnimation();
        mBinding.splashAnimation8.playAnimation();
        mBinding.splashAnimation9.playAnimation();


        navigate();

    }

    private void navigate() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                String uId = SessionManager.getInstance().getString(PrefConstant.USER_ID);
                if (!TextUtils.isEmpty(uId)) {
                    Intent intent = new Intent(SplashActivity.this, MainActivity.class);
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
