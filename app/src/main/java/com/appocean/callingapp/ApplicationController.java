package com.appocean.callingapp;

import android.app.Application;
import android.content.Context;

public class ApplicationController extends Application {
    private static ApplicationController mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }

    public static Context getAppContext() {
        return mInstance;
    }
}
