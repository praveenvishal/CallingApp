package com.appocean.callingapp.googleads;

import android.app.Activity;
import android.provider.Settings;
import android.util.Log;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

public class GoogleAds {

    public static String TAG = GoogleAds.class.getSimpleName();
    private static InterstitialAd interstitialAd;
    //   private static final String AD_UNIT_ID = "ca-app-pub-4549181860437064/9726342866";
    private static final String AD_UNIT_ID = "ca-app-pub-3940256099942544/1033173712";

    public static void setInterstitialAd(Activity activity) {
        // Create the InterstitialAd and set the adUnitId.
        interstitialAd = new InterstitialAd(activity);
        // Defined in res/values/strings.xml
        interstitialAd.setAdUnitId(AD_UNIT_ID);
        interstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                Log.e(TAG, "onAdLoaded()");
                showInterstitial(activity);
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                Log.e(TAG, "onAdFailedToLoad() with error code: " + errorCode);
            }

            @Override
            public void onAdClosed() {
                Log.e(TAG, "onAdClosed()");
                // startAd();
            }
        });
        startAd();
    }


    private static void startAd() {
        // Request a new ad if one isn't already loaded, hide the button, and kick off the timer.
        if (!interstitialAd.isLoading() && !interstitialAd.isLoaded()) {
//            AdRequest adRequest = new AdRequest.Builder().build();
            AdRequest adRequest = new AdRequest.Builder().addTestDevice(Settings.Secure.ANDROID_ID).build();
            interstitialAd.loadAd(adRequest);
        }
    }

    public static void showInterstitial(Activity activity) {
        // Show the ad if it's ready. Otherwise toast and restart the game.
        if (interstitialAd != null && interstitialAd.isLoaded()) {
            interstitialAd.show();
        } else {
            Log.e(TAG, "Ad did not load");
//            Toast.makeText(activity, , Toast.LENGTH_SHORT).show();
            startAd();
        }
    }

}
