package com.appocean.callingapp.util;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;

import com.appocean.callingapp.ApplicationController;
import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.google.common.eventbus.EventBus;

import org.json.JSONObject;

public class Util {
    public static int dpToPx(int dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }

    public static int pxToDp(int px) {
        return (int) (px / Resources.getSystem().getDisplayMetrics().density);
    }
    public static boolean isFirstTimeInstall() {
        Context context = ApplicationController.getAppContext();
        try {
            long firstInstallTime = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).firstInstallTime;
            long lastUpdateTime = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).lastUpdateTime;
            return firstInstallTime == lastUpdateTime;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void getFbDetails(AccessToken accessToken) {
        GraphRequest request = GraphRequest.newMeRequest(
                accessToken,
                new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        try {
                            Log.e("FbResponse", object.toString());
                         //   SocialMediaInfoBean socialMediaInfoBean = fromJson(object.toString(), SocialMediaInfoBean.class);

                            //Send result back to login screen
                          //  EventBus.getDefault().post(socialMediaInfoBean);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                });
        Bundle parameters = new Bundle();
        //parameters.putString("fields", "id,name,gender,birthday,friends,email,education,first_name,last_name,location,relationship_status,work,about,posts.limit(3){id,picture},photos.limit(6){id,picture},picture,albums,likes.limit(100)");
        parameters.putString("fields", "id,name,email,picture");
        request.setParameters(parameters);
        request.executeAsync();
    }
}
