package com.appocean.callingapp.util;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.appocean.callingapp.ApplicationController;


import java.util.HashMap;
import java.util.List;

/**
 * SessionManager is class to save all types of data like string, boolean, int etc
 * {@link Application}
 */
public class SessionManager {

    private static final String SHARED_PREFERENCE = "heatmiser_pref";
    private static SessionManager mInstance;


    public SessionManager(Context context) {
    }

    public static SessionManager getInstance() {
        if (mInstance == null) {
            mInstance = new SessionManager(ApplicationController.getAppContext());
        }
        return mInstance;
    }

    /**
     * save string value in shared preference
     *
     * @param key
     * @param value
     */
    public void save(String key, String value) {
        SharedPreferences.Editor editor = getPreferences().edit();
        editor.putString(key, value);
        editor.apply();
    }





    /**
     * save boolean value in shared preference
     *
     * @param key
     * @param value
     */
    public void save(String key, boolean value) {
        SharedPreferences.Editor editor = getPreferences().edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    public void save(String key, int value) {
        SharedPreferences.Editor editor = getPreferences().edit();
        editor.putInt(key, value);
        editor.apply();
    }


    /**
     * save long value in shared preference
     *
     * @param key
     * @param value
     */
    public void save(String key, long value) {
        SharedPreferences.Editor editor = getPreferences().edit();
        editor.putLong(key, value);
        editor.apply();
    }

    /**
     * save float value in shared preference
     *
     * @param key
     * @param value
     */
    public void save(String key, float value) {
        SharedPreferences.Editor editor = getPreferences().edit();
        editor.putFloat(key, value);
        editor.apply();
    }

    /**
     * get string value from shared preference
     *
     * @param key
     * @return
     */
    public String getString(String key) {
        return getPreferences().getString(key, "");
    }

    /**
     * get integer value from shared preference
     *
     * @param key
     * @return
     */
    public int getInt(String key) {
        return getPreferences().getInt(key, 0);
    }

    /**
     * get boolean value from shared preference
     *
     * @param key
     * @return
     */
    public boolean getBoolean(String key) {
        return getPreferences().getBoolean(key, false);
    }

    /**
     * get long value from shared preference
     *
     * @param key
     * @return
     */
    public long getLong(String key) {
        return getPreferences().getLong(key, 0);
    }

    /**
     * remove particular key from shared preference
     *
     * @param key
     */
    public void remove(String key) {
        SharedPreferences.Editor editor = getPreferences().edit();
        editor.remove(key);
        editor.apply();
    }

    /**
     * get float value from shared preference
     *
     * @param key
     * @return
     */
    public float getFloat(String key) {
        return getPreferences().getFloat(key, 0f);
    }

    /**
     * clear shared preference from complete app
     */
    public void clear() {
        getPreferences().edit().clear().apply();
    }


    /**
     * return shared preference
     *
     * @return
     */
    private SharedPreferences getPreferences() {
        return ApplicationController.getAppContext().getSharedPreferences(SHARED_PREFERENCE, Context.MODE_PRIVATE);
    }


}
