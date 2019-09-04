package com.appocean.callingapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class ConnectionManager {
    protected SharedPreferences sharedPref;
    protected String keyprefVideoCallEnabled;
    protected String keyprefScreencapture;
    protected String keyprefCamera2;
    protected String keyprefResolution;
    protected String keyprefFps;
    protected String keyprefCaptureQualitySlider;
    protected String keyprefVideoBitrateType;
    protected String keyprefVideoBitrateValue;
    protected String keyprefVideoCodec;
    protected String keyprefAudioBitrateType;
    protected String keyprefAudioBitrateValue;
    protected String keyprefAudioCodec;
    protected String keyprefHwCodecAcceleration;
    protected String keyprefCaptureToTexture;
    protected String keyprefFlexfec;
    protected String keyprefNoAudioProcessingPipeline;
    protected String keyprefAecDump;
    protected String keyprefOpenSLES;
    protected String keyprefDisableBuiltInAec;
    protected String keyprefDisableBuiltInAgc;
    protected String keyprefDisableBuiltInNs;
    protected String keyprefEnableLevelControl;
    protected String keyprefDisableWebRtcAGCAndHPF;
    protected String keyprefDisplayHud;
    protected String keyprefTracing;
    protected String keyprefRoomServerUrl;
    protected String keyprefRoom;
    protected String keyprefRoomList;
    protected String keyprefEnableDataChannel;
    protected String keyprefOrdered;
    protected String keyprefMaxRetransmitTimeMs;
    protected String keyprefMaxRetransmits;
    protected String keyprefDataProtocol;
    protected String keyprefNegotiated;
    protected String keyprefDataId;


    public ConnectionManager(Context context) {
        PreferenceManager.setDefaultValues(context, R.xml.preferences, false);
        sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        keyprefVideoCallEnabled = context.getString(R.string.pref_videocall_key);
        keyprefScreencapture = context.getString(R.string.pref_screencapture_key);
        keyprefCamera2 = context.getString(R.string.pref_camera2_key);
        keyprefResolution = context.getString(R.string.pref_resolution_key);
        keyprefFps = context.getString(R.string.pref_fps_key);
        keyprefCaptureQualitySlider = context.getString(R.string.pref_capturequalityslider_key);
        keyprefVideoBitrateType = context.getString(R.string.pref_maxvideobitrate_key);
        keyprefVideoBitrateValue = context.getString(R.string.pref_maxvideobitratevalue_key);
        keyprefVideoCodec = context.getString(R.string.pref_videocodec_key);
        keyprefHwCodecAcceleration = context.getString(R.string.pref_hwcodec_key);
        keyprefCaptureToTexture = context.getString(R.string.pref_capturetotexture_key);
        keyprefFlexfec = context.getString(R.string.pref_flexfec_key);
        keyprefAudioBitrateType = context.getString(R.string.pref_startaudiobitrate_key);
        keyprefAudioBitrateValue = context.getString(R.string.pref_startaudiobitratevalue_key);
        keyprefAudioCodec = context.getString(R.string.pref_audiocodec_key);
        keyprefNoAudioProcessingPipeline = context.getString(R.string.pref_noaudioprocessing_key);
        keyprefAecDump = context.getString(R.string.pref_aecdump_key);
        keyprefOpenSLES = context.getString(R.string.pref_opensles_key);
        keyprefDisableBuiltInAec = context.getString(R.string.pref_disable_built_in_aec_key);
        keyprefDisableBuiltInAgc = context.getString(R.string.pref_disable_built_in_agc_key);
        keyprefDisableBuiltInNs = context.getString(R.string.pref_disable_built_in_ns_key);
        keyprefEnableLevelControl = context.getString(R.string.pref_enable_level_control_key);
        keyprefDisableWebRtcAGCAndHPF = context.getString(R.string.pref_disable_webrtc_agc_and_hpf_key);
        keyprefDisplayHud = context.getString(R.string.pref_displayhud_key);
        keyprefTracing = context.getString(R.string.pref_tracing_key);
        keyprefRoomServerUrl = context.getString(R.string.pref_room_server_url_key);
        keyprefRoom = context.getString(R.string.pref_room_key);
        keyprefRoomList = context.getString(R.string.pref_room_list_key);
        keyprefEnableDataChannel = context.getString(R.string.pref_enable_datachannel_key);
        keyprefOrdered = context.getString(R.string.pref_ordered_key);
        keyprefMaxRetransmitTimeMs = context.getString(R.string.pref_max_retransmit_time_ms_key);
        keyprefMaxRetransmits = context.getString(R.string.pref_max_retransmits_key);
        keyprefDataProtocol = context.getString(R.string.pref_data_protocol_key);
        keyprefNegotiated = context.getString(R.string.pref_negotiated_key);
        keyprefDataId = context.getString(R.string.pref_data_id_key);
    }

}
