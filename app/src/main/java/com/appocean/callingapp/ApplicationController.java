package com.appocean.callingapp;

import android.app.Application;
import android.content.Context;

import com.appocean.callingapp.util.Util;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class ApplicationController extends Application {
    private static ApplicationController mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        Util.generateKey(this);
        initializeCalligraphy();
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
    }

    private void initializeCalligraphy() {
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Nunito-Light.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
    }

    public static Context getAppContext() {
        return mInstance;
    }
}
