package com.appocean.callingapp;

import android.app.Application;
import android.content.Context;

import com.appocean.callingapp.util.Util;

public class ApplicationController extends Application {
    private static ApplicationController mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        Util.generateKey(this);
    }

    public static Context getAppContext() {
        return mInstance;
    }
}
