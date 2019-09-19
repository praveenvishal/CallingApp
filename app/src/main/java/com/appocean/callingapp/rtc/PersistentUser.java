package com.appocean.callingapp.rtc;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences.Editor;
import android.os.Build;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class PersistentUser extends Activity {
    private static final String ACCESSTOKEN = "accesstoken";
    private static final String ACCPETED_PACKAGE_LIST = "";
    public static final String ADDDISPLAY = "adddisplay";
    public static final String ADMIN_USER = "admin_user";
    public static final String BANNER_ID = "banner_id";
    public static final String BELOW_SEVEN_SEC_CALL_COUNTER = "below_seven_sec_call_counter";
    public static final String BLOCKING_ENABLE = "blocking_enable";
    public static final String BLOCK_ADD_PURCHASE_TOKEN = "block_add_purchase_token";
    public static final String BLOCK_CALL_TIME = "block_call_time";
    public static final String BLOCK_DISCUSSION_TOPIC = "block_discussion_topic";
    public static final String BLOCK_VOCABULARY_TOPIC = "block_vocabulary_topic";
    public static final String CALL_BUTTON_COUNTER = "call_button_counter";
    public static final String CALL_CENTER_USER = "call_center_user";
    public static final String CALL_COUNTER_MORE_THAN_ONE_MIN = "call_counter";
    public static final String CCC = "ccc";
    public static final String CONTAINER = "MyPref";
    public static final String CURRENT_CALL_TYPE = "current_call_type";
    public static final String CURRENT_PACKAGE_NAME = "current_package";
    public static final String CURRENT_ROOM_ID = "current_room_id";
    public static final String CURRENT_VERSION_NAME = "current_version";
    public static final String DELAYED_DISCONNECT_ROOM = "delayed";
    public static final String DETECTION_COUNTER = "detection_counter";
    public static final String DISCUSSION_TOPIC_CLICK = "discussion_topic_click";
    public static final String FACEBOOK_LIKE = "facebook_like";
    public static final String FEMALE_HIT = "female_hit";
    public static final String FEMALE_SEEKER_CALL_TIME = "female_seeker_call_time";
    public static final String FEMALE_SEEKER_PURCHASE_TOKEN = "female_seeker_purchase_token";
    public static final String FILTER_DIAL_RECORD = "filter_dial_record";
    public static final String FemaleSeeker = "femaleSeeker";
    public static final String GENDER_SELECTOR = "gender_selector";
    public static final String GOOD_CALL_TIME = "good_call_time";
    public static final String GOOD_SPEAKER_COUNTER = "good_speaker_counter";
    public static final String GOOD_USER_POINT = "good_user_point";
    public static final String ICE_COMPLETE_COUNTER = "ice_complete_counter";
    public static final String INTERSTITIAL_ID = "intersitial_id";
    public static final String IS_RECONNECTED = "is_reconneced";
    public static final String LASTCALLSGS = "lastcallsgs";
    public static final String LAST_ROOM_ID = "last_room_id";
    public static final String LONG_CALL_COUNTER = "long_call_counter";
    public static final String LUCKY_PATCHER = "lucky_patcher";
    public static final String MALE = "male";
    public static final String MALE_HIT = "male_hit";
    public static final String MaleSeeker = "maleSeeker";
    public static final String NORMAL_DIAL_RECORD = "normal_dial_record";
    public static final String NO_TALK_HIT = "no_talk_hit";
    public static final String ORDER_ID = "order_id";
    public static final String PACKAGE_UPDATE = "package_update";
    public static final String PAID_DIAL_RECORD = "paid_dial_record";
    private static final String PASSWORD = "password";
    public static final String POSSIBLE_FEMALE_COUNTER = "possible_female_counter";
    private static final String PREFS_FILE_NAME = "AppPreferences";
    public static final String PTS = "pts";
    public static final String RDS = "rds";
    public static final String REGISTERED = "registered";
    public static final String REGISTER_AS_MALE = "register_male";
    public static final String REMOTE_UPDATED = "remote_updated";
    public static final String REVIEW_SUBMIT = "review_submit";
    public static final String SERVER_MESSAGE = "server_message";
    public static final String SGS = "sgs";
    public static final String SHOWN_MESSAGE = "shown_message";
    public static final String SINGLE_DEVICE_ID = "single_device_id";
    public static final String SUGGESTED_PACKAGE_NAME = "suggested_package";
    public static final String SUSPICIOUS_COUNTER = "suspicious_counter";
    public static final String TEMP_FEMALE_SEEKER = "temp_female_seeker";
    public static final String TEMP_UNLIMITED = "temp_unlimited";
    public static final String THIRTY_SEC_CALL_COUNTER = "thirty_sec_call_counter";
    public static final String THREE_CALL_TALK_TIME = "three_call_talk_time";
    public static final String TOTAL_DIAL_RECORD = "total_dial_record";
    public static final String TSN = "tsn";
    public static final String UNLIMITED = "unlimited";
    private static final String USERDETAILS = "userdetails";
    private static final String USEREMAIL = "useremail";
    private static final String USERID = "uid";
    private static final String USERNAME = "username";
    public static final String UTS = "uts";
    public static final String VERIFY_GENDER_BY_ADMIN = "verify_gender_by_admin";
    public static final String VERIFY_THAT_PERSON = "verify_that_person";
    public static final String VERIFY_WINDOW_POP = "verify_window_pop";
    public static final String VERSION_UPDATE = "version_update";
    public static final String VOCABULARY_TOPIC_CLICK = "vocabulary_topic_click";
    public static final String WARNING_COUNTER = "warning_counter";
    public static final String WARNING_ENABLE = "warning_enable";
    public static final String WHATSAPP_SHARE = "whatsapp_share";
    public static final String WHATSAPP_SHAREED = "whatsapp_shared";
    public static final String YES_TALK_HIT = "yes_talk_hit";
    public static Context mContext = null;
    static final int registry_decoder = 8;

    public static void SetApplicationContext(Context context) {
        mContext = context;
    }

    public static Context GetApplicationContext() {
        return mContext;
    }

    public static String stringTransform(String str, int i) {
        char[] charArray = str.toCharArray();
        for (int i2 = 0; i2 < charArray.length; i2++) {
            charArray[i2] = (char) (charArray[i2] ^ i);
        }
        return String.valueOf(charArray);
    }

    public static int getFirst3CallTalkTime(Context context) {
        return context.getApplicationContext().getSharedPreferences(CONTAINER, 0).getInt(THREE_CALL_TALK_TIME, 0);
    }

    public static void setFirst3CallTalkTime(Context context, int i) {
        context.getApplicationContext().getSharedPreferences(CONTAINER, 0).edit().putInt(THREE_CALL_TALK_TIME, i).apply();
    }

    public static boolean isRegisterAsMale(Context context) {
        return context.getApplicationContext().getSharedPreferences(CONTAINER, 0).getBoolean(REGISTER_AS_MALE, true);
    }

    public static void setRegisterAsMale(Context context, boolean z) {
        context.getApplicationContext().getSharedPreferences(CONTAINER, 0).edit().putBoolean(REGISTER_AS_MALE, z).apply();
    }

    public static boolean isWhatsAppShared(Context context) {
        return context.getApplicationContext().getSharedPreferences(CONTAINER, 0).getBoolean(WHATSAPP_SHAREED, true);
    }

    public static void setWhatsAppShared(Context context, boolean z) {
        context.getApplicationContext().getSharedPreferences(CONTAINER, 0).edit().putBoolean(WHATSAPP_SHAREED, z).apply();
    }

    public static boolean isAdminUser(Context context) {
        return context.getApplicationContext().getSharedPreferences(CONTAINER, 0).getBoolean(ADMIN_USER, false);
    }

    public static void setAdminUser(Context context, boolean z) {
        context.getApplicationContext().getSharedPreferences(CONTAINER, 0).edit().putBoolean(ADMIN_USER, z).apply();
    }

    public static boolean isCallCenterUser(Context context) {
        return context.getApplicationContext().getSharedPreferences(CONTAINER, 0).getBoolean(CALL_CENTER_USER, false);
    }

    public static void setCallCenterUser(Context context, boolean z) {
        context.getApplicationContext().getSharedPreferences(CONTAINER, 0).edit().putBoolean(CALL_CENTER_USER, z).apply();
    }

    public static boolean isWhatsAppShareEnabled(Context context) {
        return context.getApplicationContext().getSharedPreferences(CONTAINER, 0).getBoolean(WHATSAPP_SHARE, false);
    }

    public static void setWhatsAppShareEnabled(Context context, boolean z) {
        context.getApplicationContext().getSharedPreferences(CONTAINER, 0).edit().putBoolean(WHATSAPP_SHARE, z).apply();
    }

    public static boolean isFacebookLikeEnabled(Context context) {
        return context.getApplicationContext().getSharedPreferences(CONTAINER, 0).getBoolean(FACEBOOK_LIKE, false);
    }

    public static void setFacebookLikeEnabled(Context context, boolean z) {
        context.getApplicationContext().getSharedPreferences(CONTAINER, 0).edit().putBoolean(FACEBOOK_LIKE, z).apply();
    }

    public static boolean isReviewSubmitEnabled(Context context) {
        return context.getApplicationContext().getSharedPreferences(CONTAINER, 0).getBoolean(REVIEW_SUBMIT, false);
    }

    public static void setReviewSubmitEnabled(Context context, boolean z) {
        context.getApplicationContext().getSharedPreferences(CONTAINER, 0).edit().putBoolean(REVIEW_SUBMIT, z).apply();
    }

    public static boolean isTemporaryBlockingEnabled(Context context) {
        return context.getApplicationContext().getSharedPreferences(CONTAINER, 0).getBoolean(BLOCKING_ENABLE, false);
    }

    public static void setWarningEnabled(Context context, boolean z) {
        context.getApplicationContext().getSharedPreferences(CONTAINER, 0).edit().putBoolean(WARNING_ENABLE, z).apply();
    }

    public static boolean isWarningEnabled(Context context) {
        return context.getApplicationContext().getSharedPreferences(CONTAINER, 0).getBoolean(WARNING_ENABLE, false);
    }

    public static void setVerifyWindowPop(Context context, boolean z) {
        context.getApplicationContext().getSharedPreferences(CONTAINER, 0).edit().putBoolean(VERIFY_WINDOW_POP, z).apply();
    }

    public static boolean shouldVerifyWindowPop(Context context) {
        return context.getApplicationContext().getSharedPreferences(CONTAINER, 0).getBoolean(VERIFY_WINDOW_POP, false);
    }

    public static void setTemporaryBlockingEnabled(Context context, boolean z) {
        context.getApplicationContext().getSharedPreferences(CONTAINER, 0).edit().putBoolean(BLOCKING_ENABLE, z).apply();
    }

    public static void setCallCounterMoreThan1minute(Context context, int i) {
        context.getApplicationContext().getSharedPreferences(CONTAINER, 0).edit().putInt(CALL_COUNTER_MORE_THAN_ONE_MIN, i).apply();
    }

    public static int getCallCounterMoreThan1minute(Context context) {
        return context.getApplicationContext().getSharedPreferences(CONTAINER, 0).getInt(CALL_COUNTER_MORE_THAN_ONE_MIN, 0);
    }

    public static void set_ICE_completed_counter(Context context, int i) {
        context.getApplicationContext().getSharedPreferences(CONTAINER, 0).edit().putInt(ICE_COMPLETE_COUNTER, i).apply();
    }

    public static int get_ICE_completed_counter(Context context) {
        return context.getApplicationContext().getSharedPreferences(CONTAINER, 0).getInt(ICE_COMPLETE_COUNTER, 0);
    }

    public static void setCallButtonCounter(Context context, int i) {
        context.getApplicationContext().getSharedPreferences(CONTAINER, 0).edit().putInt(CALL_BUTTON_COUNTER, i).apply();
    }

    public static int getCallButtonCounter(Context context) {
        return context.getApplicationContext().getSharedPreferences(CONTAINER, 0).getInt(CALL_BUTTON_COUNTER, 0);
    }

    public static void setLongCallCounter(Context context, int i) {
        context.getApplicationContext().getSharedPreferences(CONTAINER, 0).edit().putInt(LONG_CALL_COUNTER, i).apply();
    }

    public static int getGoodUserPoint(Context context) {
        return context.getApplicationContext().getSharedPreferences(CONTAINER, 0).getInt(GOOD_USER_POINT, 0);
    }

    public static void setGoodUserPoint(Context context, int i) {
        context.getApplicationContext().getSharedPreferences(CONTAINER, 0).edit().putInt(GOOD_USER_POINT, i).apply();
    }

    public static int getLongCallCounter(Context context) {
        return context.getApplicationContext().getSharedPreferences(CONTAINER, 0).getInt(LONG_CALL_COUNTER, 0);
    }

    public static void setPossibleFemaleCounter(Context context, int i) {
        context.getApplicationContext().getSharedPreferences(CONTAINER, 0).edit().putInt(POSSIBLE_FEMALE_COUNTER, i).apply();
    }

    public static int getPossibleFemaleCounter(Context context) {
        return context.getApplicationContext().getSharedPreferences(CONTAINER, 0).getInt(POSSIBLE_FEMALE_COUNTER, 0);
    }

    public static void setBlockingTime(Context context, long j) {
        context.getApplicationContext().getSharedPreferences(CONTAINER, 0).edit().putLong(BLOCK_CALL_TIME, j).apply();
    }

    public static long getBlockingTime(Context context) {
        return context.getApplicationContext().getSharedPreferences(CONTAINER, 0).getLong(BLOCK_CALL_TIME, 0);
    }

    public static void setGoodSpeakerStartingTime(Context context, long j) {
        context.getApplicationContext().getSharedPreferences(CONTAINER, 0).edit().putLong(GOOD_CALL_TIME, j).apply();
    }

    public static long getGoodSpeakerStartingTime(Context context) {
        return context.getApplicationContext().getSharedPreferences(CONTAINER, 0).getLong(GOOD_CALL_TIME, 0);
    }

    public static void setLastFemaleSeekerCallTime(Context context, long j) {
        context.getApplicationContext().getSharedPreferences(CONTAINER, 0).edit().putLong(FEMALE_SEEKER_CALL_TIME, j).apply();
    }

    public static long getLastFemaleSeekerCallTime(Context context) {
        return context.getApplicationContext().getSharedPreferences(CONTAINER, 0).getLong(FEMALE_SEEKER_CALL_TIME, 0);
    }

    public static void set30secCallCounter(Context context, int i) {
        context.getApplicationContext().getSharedPreferences(CONTAINER, 0).edit().putInt(THIRTY_SEC_CALL_COUNTER, i).apply();
    }

    public static int get30secCallCounter(Context context) {
        return context.getApplicationContext().getSharedPreferences(CONTAINER, 0).getInt(THIRTY_SEC_CALL_COUNTER, 0);
    }

    public static void setWarningCounter(Context context, int i) {
        context.getApplicationContext().getSharedPreferences(CONTAINER, 0).edit().putInt(WARNING_COUNTER, i).apply();
    }

    public static int getWarningCounter(Context context) {
        return context.getApplicationContext().getSharedPreferences(CONTAINER, 0).getInt(WARNING_COUNTER, 0);
    }

    public static void setGoodSpeakerCounter(Context context, int i) {
        context.getApplicationContext().getSharedPreferences(CONTAINER, 0).edit().putInt(GOOD_SPEAKER_COUNTER, i).apply();
    }

    public static int getGoodSpeakerCounter(Context context) {
        return context.getApplicationContext().getSharedPreferences(CONTAINER, 0).getInt(GOOD_SPEAKER_COUNTER, 0);
    }

    public static void setSuspiciousFemaleCounter(Context context, int i) {
        context.getApplicationContext().getSharedPreferences(CONTAINER, 0).edit().putInt(SUSPICIOUS_COUNTER, i).apply();
    }

    public static int getSuspiciousFemaleCounter(Context context) {
        return context.getApplicationContext().getSharedPreferences(CONTAINER, 0).getInt(SUSPICIOUS_COUNTER, 0);
    }

    public static void setDetectionCounter(Context context, int i) {
        context.getApplicationContext().getSharedPreferences(CONTAINER, 0).edit().putInt(DETECTION_COUNTER, i).apply();
    }

    public static int getDetectionCounter(Context context) {
        return context.getApplicationContext().getSharedPreferences(CONTAINER, 0).getInt(DETECTION_COUNTER, 0);
    }

    public static void setBelow7secCallCounter(Context context, int i) {
        context.getApplicationContext().getSharedPreferences(CONTAINER, 0).edit().putInt(BELOW_SEVEN_SEC_CALL_COUNTER, i).apply();
    }

    public static int getBelow7secCallCounter(Context context) {
        return context.getApplicationContext().getSharedPreferences(CONTAINER, 0).getInt(BELOW_SEVEN_SEC_CALL_COUNTER, 0);
    }

    public static String getBlockAddPurchaseToken(Context context) {
        return context.getSharedPreferences(PREFS_FILE_NAME, 0).getString(BLOCK_ADD_PURCHASE_TOKEN, "invalid_token");
    }

    public static void setBlockAddPurchaseToken(Context context, String str) {
        Editor edit = context.getSharedPreferences(PREFS_FILE_NAME, 0).edit();
        edit.putString(BLOCK_ADD_PURCHASE_TOKEN, str);
        edit.apply();
    }

    public static String getFemaleSeekerPurchaseToken(Context context) {
        return context.getSharedPreferences(PREFS_FILE_NAME, 0).getString(FEMALE_SEEKER_PURCHASE_TOKEN, "invalid_token");
    }

    public static void setFemaleSeekerPurchaseToken(Context context, String str) {
        Editor edit = context.getSharedPreferences(PREFS_FILE_NAME, 0).edit();
        edit.putString(FEMALE_SEEKER_PURCHASE_TOKEN, str);
        edit.apply();
    }

    public static String getLuckyPatcherPackages(Context context) {
        return context.getSharedPreferences(PREFS_FILE_NAME, 0).getString(LUCKY_PATCHER, "hackcheck_start cc.madkite.freedom com.amphoras.hidemyroot com.kingroot.kinguser com.devadvance.rootcloak2 com.devadvance.rootcloak com.devadvance.rootcloak1 de.robv.android.xposed.installer com.android.vending.billing.InAppBillingService.LOCK com.android.vending.billing.InAppBillingService.LACK com.chelpus.lackypatch com.dimonvideo.luckypatcher hackcheck_end");
    }

    public static void setLuckyPatcherPackages(Context context, String str) {
        Editor edit = context.getSharedPreferences(PREFS_FILE_NAME, 0).edit();
        edit.putString(LUCKY_PATCHER, str);
        edit.apply();
    }

    public static String getTSN(Context context) {
        return stringTransform(context.getSharedPreferences(PREFS_FILE_NAME, 0).getString(TSN, stringTransform("englishtalkapp.com ", 9)), 9);
    }

    public static void setTSN(Context context, String str) {
        Editor edit = context.getSharedPreferences(PREFS_FILE_NAME, 0).edit();
        edit.putString(TSN, stringTransform(str, 9));
        edit.apply();
    }

    public static String getCurrentCallType(Context context) {
        return context.getSharedPreferences(PREFS_FILE_NAME, 0).getString(CURRENT_CALL_TYPE, "level1");
    }

    public static void setCurrentCallType(Context context, String str) {
        Editor edit = context.getSharedPreferences(PREFS_FILE_NAME, 0).edit();
        edit.putString(CURRENT_CALL_TYPE, str);
        edit.apply();
    }

    public static int getCurrentCallRoomID(Context context) {
        return context.getApplicationContext().getSharedPreferences(CONTAINER, 0).getInt(CURRENT_ROOM_ID, 0);
    }

    public static void setCurrentCallRoomID(Context context, int i) {
        context.getApplicationContext().getSharedPreferences(CONTAINER, 0).edit().putInt(CURRENT_ROOM_ID, i).apply();
    }

    public static int getLastConnectedCallRoomID(Context context) {
        return context.getApplicationContext().getSharedPreferences(CONTAINER, 0).getInt(LAST_ROOM_ID, 0);
    }

    public static void setLastConnectedCallRoomID(Context context, int i) {
        context.getApplicationContext().getSharedPreferences(CONTAINER, 0).edit().putInt(LAST_ROOM_ID, i).apply();
    }

    public static int getGenerateSingleDeviceID(Context context) {
        return context.getApplicationContext().getSharedPreferences(CONTAINER, 0).getInt(SINGLE_DEVICE_ID, 0);
    }

    public static void setGenerateSingleDeviceID(Context context, int i) {
        context.getApplicationContext().getSharedPreferences(CONTAINER, 0).edit().putInt(SINGLE_DEVICE_ID, i).apply();
    }

    public static int getDiscussionTopicClickCounter(Context context) {
        return context.getApplicationContext().getSharedPreferences(CONTAINER, 0).getInt(DISCUSSION_TOPIC_CLICK, 0);
    }

    public static void setDiscussionTopicClickCounter(Context context, int i) {
        context.getApplicationContext().getSharedPreferences(CONTAINER, 0).edit().putInt(DISCUSSION_TOPIC_CLICK, i).apply();
    }

    public static int getVocabularyTopicClickCounter(Context context) {
        return context.getApplicationContext().getSharedPreferences(CONTAINER, 0).getInt(VOCABULARY_TOPIC_CLICK, 0);
    }

    public static void setVocabularyTopicClickCounter(Context context, int i) {
        context.getApplicationContext().getSharedPreferences(CONTAINER, 0).edit().putInt(VOCABULARY_TOPIC_CLICK, i).apply();
    }

    public static String getUTS(Context context) {
        return stringTransform(context.getSharedPreferences(PREFS_FILE_NAME, 0).getString(UTS, stringTransform("shahed ", 9)), 9);
    }

    public static void setUTS(Context context, String str) {
        Editor edit = context.getSharedPreferences(PREFS_FILE_NAME, 0).edit();
        edit.putString(UTS, stringTransform(str, 9));
        edit.apply();
    }

    public static String getPTS(Context context) {
        return stringTransform(context.getSharedPreferences(PREFS_FILE_NAME, 0).getString(PTS, stringTransform("ahmed ", 9)), 9);
    }

    public static void setPTS(Context context, String str) {
        Editor edit = context.getSharedPreferences(PREFS_FILE_NAME, 0).edit();
        edit.putString(PTS, stringTransform(str, 9));
        edit.apply();
    }

    public static String getRDS(Context context) {
        return stringTransform(context.getSharedPreferences(PREFS_FILE_NAME, 0).getString(RDS, stringTransform("http://englishtalklive.com:8000/station1 ", 9)), 9);
    }

    public static void setRDS(Context context, String str) {
        Editor edit = context.getSharedPreferences(PREFS_FILE_NAME, 0).edit();
        edit.putString(RDS, stringTransform(str, 9));
        edit.apply();
    }

    public static String getSGS(Context context) {
        return stringTransform(context.getSharedPreferences(PREFS_FILE_NAME, 0).getString(SGS, stringTransform("ws://englishtalkapp.com:443/ws ", 9)), 9);
    }

    public static void setSGS(Context context, String str) {
        Editor edit = context.getSharedPreferences(PREFS_FILE_NAME, 0).edit();
        edit.putString(SGS, stringTransform(str, 9));
        edit.apply();
    }

    public static String getCCC(Context context) {
        return context.getSharedPreferences(PREFS_FILE_NAME, 0).getString(CCC, "CCC_start 01:01:2018 CCC_end");
    }

    public static void setCCC(Context context, String str) {
        Editor edit = context.getSharedPreferences(PREFS_FILE_NAME, 0).edit();
        edit.putString(CCC, str);
        edit.apply();
    }

    public static String getLasCallSGServer(Context context) {
        return stringTransform(context.getSharedPreferences(PREFS_FILE_NAME, 0).getString(LASTCALLSGS, stringTransform("ws://englishtalkapp.com:443/ws", 9)), 9);
    }

    public static void setLasCallSGServer(Context context, String str) {
        Editor edit = context.getSharedPreferences(PREFS_FILE_NAME, 0).edit();
        edit.putString(LASTCALLSGS, stringTransform(str, 9));
        edit.apply();
    }

    public static String getShownMessage(Context context) {
        return context.getSharedPreferences(PREFS_FILE_NAME, 0).getString(SHOWN_MESSAGE, "Welcome to English Talk App");
    }

    public static void setShownMessage(Context context, String str) {
        Editor edit = context.getSharedPreferences(PREFS_FILE_NAME, 0).edit();
        edit.putString(SHOWN_MESSAGE, str);
        edit.apply();
    }

    public static String getServerMessage(Context context) {
        return context.getSharedPreferences(PREFS_FILE_NAME, 0).getString(SERVER_MESSAGE, "Welcome to English Talk app");
    }

    public static void setServerMessage(Context context, String str) {
        Editor edit = context.getSharedPreferences(PREFS_FILE_NAME, 0).edit();
        edit.putString(SERVER_MESSAGE, str);
        edit.apply();
    }

    public static String getBannerId(Context context) {
        return stringTransform(context.getSharedPreferences(PREFS_FILE_NAME, 0).getString(BANNER_ID, stringTransform("ca-app-pub-7226060081143871/2135010545", 9)), 9);
    }

    public static void setBannerId(Context context, String str) {
        Editor edit = context.getSharedPreferences(PREFS_FILE_NAME, 0).edit();
        edit.putString(BANNER_ID, stringTransform(str, 9));
        edit.apply();
    }

    public static String getInterstitialId(Context context) {
        return stringTransform(context.getSharedPreferences(PREFS_FILE_NAME, 0).getString(INTERSTITIAL_ID, stringTransform("ca-app-pub-7226060081143871/3611743741", 9)), 9);
    }

    public static void setInterstitialId(Context context, String str) {
        Editor edit = context.getSharedPreferences(PREFS_FILE_NAME, 0).edit();
        edit.putString(INTERSTITIAL_ID, stringTransform(str, 9));
        edit.apply();
    }

    public static String getCurrentPackage(Context context) {
        return context.getSharedPreferences(PREFS_FILE_NAME, 0).getString(CURRENT_PACKAGE_NAME, "");
    }

    public static void setCurrentPackage(Context context, String str) {
        Editor edit = context.getSharedPreferences(PREFS_FILE_NAME, 0).edit();
        edit.putString(CURRENT_PACKAGE_NAME, str);
        edit.apply();
    }

    public static String getSuggestedPackage(Context context) {
        return context.getSharedPreferences(PREFS_FILE_NAME, 0).getString(SUGGESTED_PACKAGE_NAME, "");
    }

    public static void setSuggestedPackage(Context context, String str) {
        Editor edit = context.getSharedPreferences(PREFS_FILE_NAME, 0).edit();
        edit.putString(SUGGESTED_PACKAGE_NAME, str);
        edit.apply();
    }

    public static String getCurrentVersion(Context context) {
        return context.getSharedPreferences(PREFS_FILE_NAME, 0).getString(CURRENT_VERSION_NAME, "");
    }

    public static void setCurrentVersion(Context context, String str) {
        Editor edit = context.getSharedPreferences(PREFS_FILE_NAME, 0).edit();
        edit.putString(CURRENT_VERSION_NAME, str);
        edit.apply();
    }

    public static boolean isDiscussionTopicBlocked(Context context) {
        return context.getApplicationContext().getSharedPreferences(CONTAINER, 0).getBoolean(BLOCK_DISCUSSION_TOPIC, false);
    }

    public static void setDiscussionTopicBlocked(Context context, boolean z) {
        context.getApplicationContext().getSharedPreferences(CONTAINER, 0).edit().putBoolean(BLOCK_DISCUSSION_TOPIC, z).apply();
    }

    public static boolean isVocabularyTopicBlocked(Context context) {
        return context.getApplicationContext().getSharedPreferences(CONTAINER, 0).getBoolean(BLOCK_VOCABULARY_TOPIC, false);
    }

    public static void setVocabularyTopicBlocked(Context context, boolean z) {
        context.getApplicationContext().getSharedPreferences(CONTAINER, 0).edit().putBoolean(BLOCK_VOCABULARY_TOPIC, z).apply();
    }

    public static boolean isVersionUpdateRequired(Context context) {
        return context.getApplicationContext().getSharedPreferences(CONTAINER, 0).getBoolean(VERSION_UPDATE, false);
    }

    public static void setVersionUpdateRequired(Context context, boolean z) {
        context.getApplicationContext().getSharedPreferences(CONTAINER, 0).edit().putBoolean(VERSION_UPDATE, z).apply();
    }

    public static boolean isLastCallReconnected(Context context) {
        return context.getApplicationContext().getSharedPreferences(CONTAINER, 0).getBoolean(IS_RECONNECTED, false);
    }

    public static void setLastCallReconnected(Context context, boolean z) {
        context.getApplicationContext().getSharedPreferences(CONTAINER, 0).edit().putBoolean(IS_RECONNECTED, z).apply();
    }

    public static boolean isPackageUpdateRequired(Context context) {
        return context.getApplicationContext().getSharedPreferences(CONTAINER, 0).getBoolean(PACKAGE_UPDATE, false);
    }

    public static void setPackageUpdateRequired(Context context, boolean z) {
        context.getApplicationContext().getSharedPreferences(CONTAINER, 0).edit().putBoolean(PACKAGE_UPDATE, z).apply();
    }

    public static void setMale(Context context, boolean z) {
        context.getApplicationContext().getSharedPreferences(CONTAINER, 0).edit().putBoolean(MALE, z).apply();
    }

    public static void setDelayedDisconnectRrom(Context context, boolean z) {
        context.getApplicationContext().getSharedPreferences(CONTAINER, 0).edit().putBoolean(DELAYED_DISCONNECT_ROOM, z).apply();
    }

    public static void setVerifyThatPersonByPeer(Context context, boolean z) {
        context.getApplicationContext().getSharedPreferences(CONTAINER, 0).edit().putBoolean(VERIFY_THAT_PERSON, z).apply();
    }

    public static void setGenderVerifiedByAdmin(Context context, boolean z) {
        context.getApplicationContext().getSharedPreferences(CONTAINER, 0).edit().putBoolean(VERIFY_GENDER_BY_ADMIN, z).apply();
    }

    public static void setFemaleSeeker(Context context, boolean z) {
        context.getApplicationContext().getSharedPreferences(CONTAINER, 0).edit().putBoolean(FemaleSeeker, z).apply();
    }

    public static void setMaleHit(Context context, int i) {
        context.getApplicationContext().getSharedPreferences(CONTAINER, 0).edit().putInt(MALE_HIT, i).apply();
    }

    public static void setFemaleHit(Context context, int i) {
        context.getApplicationContext().getSharedPreferences(CONTAINER, 0).edit().putInt(FEMALE_HIT, i).apply();
    }

    public static void setYesTalkHit(Context context, int i) {
        context.getApplicationContext().getSharedPreferences(CONTAINER, 0).edit().putInt(YES_TALK_HIT, i).apply();
    }

    public static void setNoTalkHit(Context context, int i) {
        context.getApplicationContext().getSharedPreferences(CONTAINER, 0).edit().putInt(NO_TALK_HIT, i).apply();
    }

    public static void setMaleSeeker(Context context, boolean z) {
        context.getApplicationContext().getSharedPreferences(CONTAINER, 0).edit().putBoolean(MaleSeeker, z).apply();
    }

    public static void setUnlimited(Context context, boolean z) {
        context.getApplicationContext().getSharedPreferences(CONTAINER, 0).edit().putBoolean(UNLIMITED, z).apply();
    }

    public static void setTemporaryUnlimited(Context context, boolean z) {
        context.getApplicationContext().getSharedPreferences(CONTAINER, 0).edit().putBoolean(TEMP_UNLIMITED, z).apply();
    }

    public static void setTemporaryFemaleSeeker(Context context, boolean z) {
        context.getApplicationContext().getSharedPreferences(CONTAINER, 0).edit().putBoolean(TEMP_FEMALE_SEEKER, z).apply();
    }

    public static void setTemporaryGenderSelector(Context context, boolean z) {
        context.getApplicationContext().getSharedPreferences(CONTAINER, 0).edit().putBoolean(GENDER_SELECTOR, z).apply();
    }

    public static void setRemoteUpdated(Context context, boolean z) {
        context.getApplicationContext().getSharedPreferences(CONTAINER, 0).edit().putBoolean(REMOTE_UPDATED, z).apply();
    }

    public static void setAddDisplay(Context context, boolean z) {
        context.getApplicationContext().getSharedPreferences(CONTAINER, 0).edit().putBoolean(ADDDISPLAY, z).apply();
    }

    public static void setRegister(Context context, boolean z) {
        context.getApplicationContext().getSharedPreferences(CONTAINER, 0).edit().putBoolean(REGISTERED, z).apply();
    }

    public static boolean isRegistered(Context context) {
        return context.getApplicationContext().getSharedPreferences(CONTAINER, 0).getBoolean(REGISTERED, false);
    }

    public static boolean isMale(Context context) {
        return context.getApplicationContext().getSharedPreferences(CONTAINER, 0).getBoolean(MALE, true);
    }

    public static boolean isFemaleSeeker(Context context) {
        return context.getApplicationContext().getSharedPreferences(CONTAINER, 0).getBoolean(FemaleSeeker, false);
    }

    public static boolean isMaleSeeker(Context context) {
        return context.getApplicationContext().getSharedPreferences(CONTAINER, 0).getBoolean(MaleSeeker, false);
    }

    public static boolean isUnlimited(Context context) {
        return context.getApplicationContext().getSharedPreferences(CONTAINER, 0).getBoolean(UNLIMITED, false);
    }

    public static boolean isTemporaryUnlimited(Context context) {
        return context.getApplicationContext().getSharedPreferences(CONTAINER, 0).getBoolean(TEMP_UNLIMITED, false);
    }

    public static boolean isTemporaryFemaleSeeker(Context context) {
        return context.getApplicationContext().getSharedPreferences(CONTAINER, 0).getBoolean(TEMP_FEMALE_SEEKER, false);
    }

    public static boolean isTemporaryGenderSelector(Context context) {
        return context.getApplicationContext().getSharedPreferences(CONTAINER, 0).getBoolean(GENDER_SELECTOR, false);
    }

    public static boolean isRemoteUpdated(Context context) {
        return context.getApplicationContext().getSharedPreferences(CONTAINER, 0).getBoolean(REMOTE_UPDATED, false);
    }

    public static boolean isAddDisplay(Context context) {
        return context.getApplicationContext().getSharedPreferences(CONTAINER, 0).getBoolean(ADDDISPLAY, false);
    }

    public static int getMaleHit(Context context) {
        return context.getApplicationContext().getSharedPreferences(CONTAINER, 0).getInt(MALE_HIT, 0);
    }

    public static int getFemaleHit(Context context) {
        return context.getApplicationContext().getSharedPreferences(CONTAINER, 0).getInt(FEMALE_HIT, 0);
    }

    public static int getYesTalkHit(Context context) {
        return context.getApplicationContext().getSharedPreferences(CONTAINER, 0).getInt(YES_TALK_HIT, 0);
    }

    public static int getNoTalkHit(Context context) {
        return context.getApplicationContext().getSharedPreferences(CONTAINER, 0).getInt(NO_TALK_HIT, 0);
    }

    public static boolean isDelayedDisconnectRrom(Context context) {
        return context.getApplicationContext().getSharedPreferences(CONTAINER, 0).getBoolean(DELAYED_DISCONNECT_ROOM, false);
    }

    public static boolean isVerifyThatPersonByPeer(Context context) {
        return context.getApplicationContext().getSharedPreferences(CONTAINER, 0).getBoolean(VERIFY_THAT_PERSON, false);
    }

    public static boolean isGenderVerifiedByAdmin(Context context) {
        return context.getApplicationContext().getSharedPreferences(CONTAINER, 0).getBoolean(VERIFY_GENDER_BY_ADMIN, false);
    }

    public static String getAcceptedPackageList(Context context) {
        return context.getSharedPreferences(PREFS_FILE_NAME, 0).getString("", "");
    }

    public static void setAcceptedPackageList(Context context, String str) {
        Editor edit = context.getSharedPreferences(PREFS_FILE_NAME, 0).edit();
        edit.putString("", str);
        edit.apply();
    }

    public static String getAccessToken(Context context) {
        return context.getSharedPreferences(PREFS_FILE_NAME, 0).getString(ACCESSTOKEN, "");
    }

    public static String getPassword(Context context) {
        return context.getSharedPreferences(PREFS_FILE_NAME, 0).getString(PASSWORD, "");
    }

    public static String getUserID(Context context) {
        return context.getSharedPreferences(PREFS_FILE_NAME, 0).getString(USERID, "0");
    }

    public static void setPassword(Context context, String str) {
        Editor edit = context.getSharedPreferences(PREFS_FILE_NAME, 0).edit();
        edit.putString(PASSWORD, str);
        edit.apply();
    }

    public static void setAccessToken(Context context, String str) {
        Editor edit = context.getSharedPreferences(PREFS_FILE_NAME, 0).edit();
        edit.putString(ACCESSTOKEN, str);
        edit.apply();
    }

    public static void setUserID(Context context, String str) {
        Editor edit = context.getSharedPreferences(PREFS_FILE_NAME, 0).edit();
        edit.putString(USERID, str);
        edit.apply();
    }

    public static String getUserName(Context context) {
        return context.getSharedPreferences(PREFS_FILE_NAME, 0).getString(USERNAME, "");
    }

    public static String getUserEmail(Context context) {
        return context.getSharedPreferences(PREFS_FILE_NAME, 0).getString(USEREMAIL, "");
    }

    public static String getUserDetails(Context context) {
        return context.getSharedPreferences(PREFS_FILE_NAME, 0).getString(USERDETAILS, "");
    }

    public static void setUserEmail(Context context, String str) {
        Editor edit = context.getSharedPreferences(PREFS_FILE_NAME, 0).edit();
        edit.putString(USEREMAIL, str);
        edit.apply();
    }

    public static void setUserName(Context context, String str) {
        Editor edit = context.getSharedPreferences(PREFS_FILE_NAME, 0).edit();
        edit.putString(USERNAME, str);
        edit.apply();
    }

    public static void setUserDetails(Context context, String str) {
        Editor edit = context.getSharedPreferences(PREFS_FILE_NAME, 0).edit();
        edit.putString(USERDETAILS, str);
        edit.apply();
    }

    public static void logOut(Context context) {
        context.getSharedPreferences(PREFS_FILE_NAME, 0).edit().putBoolean("LOGIN", false).apply();
    }

    public static void setLogin(Context context) {
        context.getSharedPreferences(PREFS_FILE_NAME, 0).edit().putBoolean("LOGIN", true).apply();
    }

    public static boolean isLogged(Context context) {
        return context.getSharedPreferences(PREFS_FILE_NAME, 0).getBoolean("LOGIN", false);
    }

    public static void resetAllData(Context context) {
        setUserName(context, "");
        setUserID(context, "");
        setUserEmail(context, "");
        setUserDetails(context, "");
        setAccessToken(context, "");
    }

    public static boolean isDeviceRooted() {
        return checkRootMethod1() || checkRootMethod2() || checkRootMethod3();
    }

    private static boolean checkRootMethod1() {
        String str = Build.TAGS;
        return str != null && str.contains("test-keys");
    }

    private static boolean checkRootMethod2() {
        for (String file : new String[]{"/system/app/Superuser.apk", "/sbin/su", "/system/bin/su", "/system/xbin/su", "/data/local/xbin/su", "/data/local/bin/su", "/system/sd/xbin/su", "/system/bin/failsafe/su", "/data/local/su", "/su/bin/su"}) {
            if (new File(file).exists()) {
                return true;
            }
        }
        return false;
    }

    private static boolean checkRootMethod3() {
        Process process = null;
        try {
            Process exec = Runtime.getRuntime().exec(new String[]{"/system/xbin/which", "su"});
            if (new BufferedReader(new InputStreamReader(exec.getInputStream())).readLine() != null) {
                if (exec != null) {
                    exec.destroy();
                }
                return true;
            }
            if (exec != null) {
                exec.destroy();
            }
            return false;
        } catch (Throwable th) {
            if (process != null) {
                process.destroy();
            }
            try {
                throw th;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public static String getPaidDialRecord(Context context) {
        return context.getSharedPreferences(PREFS_FILE_NAME, 0).getString(PAID_DIAL_RECORD, "0 0 0 0 0");
    }

    public static void setPaidDialRecord(Context context, String str) {
        Editor edit = context.getSharedPreferences(PREFS_FILE_NAME, 0).edit();
        edit.putString(PAID_DIAL_RECORD, str);
        edit.apply();
    }

    public static String getNormalDialRecord(Context context) {
        return context.getSharedPreferences(PREFS_FILE_NAME, 0).getString(NORMAL_DIAL_RECORD, "0 0 0 0 0");
    }

    public static void setNormalDialRecord(Context context, String str) {
        Editor edit = context.getSharedPreferences(PREFS_FILE_NAME, 0).edit();
        edit.putString(NORMAL_DIAL_RECORD, str);
        edit.apply();
    }

    public static String getFilterDialRecord(Context context) {
        return context.getSharedPreferences(PREFS_FILE_NAME, 0).getString(FILTER_DIAL_RECORD, "0 0 0");
    }

    public static void setFilterDialRecord(Context context, String str) {
        Editor edit = context.getSharedPreferences(PREFS_FILE_NAME, 0).edit();
        edit.putString(FILTER_DIAL_RECORD, str);
        edit.apply();
    }

    public static String getTotalDialRecord(Context context) {
        return context.getSharedPreferences(PREFS_FILE_NAME, 0).getString(TOTAL_DIAL_RECORD, "p0 = 0, p10 = 0, p30 = 0, p60 = 0, p120 = 0, n0 = 0, n10 = 0, n30 = 0, n60 = 0, n120 = 0, f = 0, fm = 0, ff = 0");
    }

    public static void setTotalDialRecord(Context context, String str) {
        Editor edit = context.getSharedPreferences(PREFS_FILE_NAME, 0).edit();
        edit.putString(TOTAL_DIAL_RECORD, str);
        edit.apply();
    }

    public static String getOrderID(Context context) {
        return context.getSharedPreferences(PREFS_FILE_NAME, 0).getString(ORDER_ID, "invalid");
    }

    public static void setOrderID(Context context, String str) {
        Editor edit = context.getSharedPreferences(PREFS_FILE_NAME, 0).edit();
        edit.putString(ORDER_ID, str);
        edit.apply();
    }
}
