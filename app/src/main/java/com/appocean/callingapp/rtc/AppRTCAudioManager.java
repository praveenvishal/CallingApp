/*
 *  Copyright 2014 The WebRTC Project Authors. All rights reserved.
 *
 *  Use of this source code is governed by a BSD-style license
 *  that can be found in the LICENSE file in the root of the source
 *  tree. An additional intellectual property rights grant can be found
 *  in the file PATENTS.  All contributing project authors may
 *  be found in the AUTHORS file in the root of the source tree.
 */

package com.appocean.callingapp.rtc;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.preference.PreferenceManager;
import android.util.Log;

import com.appocean.callingapp.R;
import com.google.android.exoplayer2.util.MimeTypes;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;



/**
 * AppRTCAudioManager manages all audio related parts of the AppRTC demo.
 */
public class AppRTCAudioManager {
  private static final String SPEAKERPHONE_AUTO = "auto";
  private static final String SPEAKERPHONE_FALSE = "false";
  private static final String SPEAKERPHONE_TRUE = "true";
  private static final String TAG = "AppRTCAudioManager";
  private final Context apprtcContext;
  private final Set<AudioDevice> audioDevices = new HashSet();
  private AudioManager audioManager;
  private final AudioDevice defaultAudioDevice;
  private boolean initialized = false;
  private final Runnable onStateChangeListener;
  private AppRTCProximitySensor proximitySensor = null;
  private int savedAudioMode = -2;
  private boolean savedIsMicrophoneMute = false;
  private boolean savedIsSpeakerPhoneOn = false;
  /* access modifiers changed from: private */
  public AudioDevice selectedAudioDevice;
  private final String useSpeakerphone;
  private BroadcastReceiver wiredHeadsetReceiver;

  public enum AudioDevice {
    SPEAKER_PHONE,
    WIRED_HEADSET,
    EARPIECE
  }

  /* access modifiers changed from: private */
  public void onProximitySensorChangedState() {
    if (this.useSpeakerphone.equals(SPEAKERPHONE_AUTO) && this.audioDevices.size() == 2 && this.audioDevices.contains(AudioDevice.EARPIECE) && this.audioDevices.contains(AudioDevice.SPEAKER_PHONE)) {
      if (this.proximitySensor.sensorReportsNearState()) {
        setAudioDevice(AudioDevice.EARPIECE);
      } else {
        setAudioDevice(AudioDevice.SPEAKER_PHONE);
      }
    }
  }

  public static AppRTCAudioManager create(Context context, Runnable runnable) {
    return new AppRTCAudioManager(context, runnable);
  }

  private AppRTCAudioManager(Context context, Runnable runnable) {
    this.apprtcContext = context;
    this.onStateChangeListener = runnable;
    this.audioManager = (AudioManager) context.getSystemService(MimeTypes.BASE_TYPE_AUDIO);
    this.useSpeakerphone = PreferenceManager.getDefaultSharedPreferences(context).getString(context.getString(R.string.pref_speakerphone_key), context.getString(R.string.pref_speakerphone_default));
    if (this.useSpeakerphone.equals(SPEAKERPHONE_FALSE)) {
      this.defaultAudioDevice = AudioDevice.EARPIECE;
    } else {
      this.defaultAudioDevice = AudioDevice.SPEAKER_PHONE;
    }
    this.proximitySensor = AppRTCProximitySensor.create(context, new Runnable() {
      public void run() {
        AppRTCAudioManager.this.onProximitySensorChangedState();
      }
    });
    AppRTCUtils.logDeviceInfo(TAG);
  }

  public void init() {
    Log.d(TAG, "init");
    if (!this.initialized) {
      this.savedAudioMode = this.audioManager.getMode();
      this.savedIsSpeakerPhoneOn = this.audioManager.isSpeakerphoneOn();
      this.savedIsMicrophoneMute = this.audioManager.isMicrophoneMute();
      this.audioManager.requestAudioFocus(null, 0, 2);
      this.audioManager.setMode(0);
      setMicrophoneMute(false);
      updateAudioDeviceState(hasWiredHeadset());
      registerForWiredHeadsetIntentBroadcast();
      this.initialized = true;
    }
  }

  public void SetAudioManagerMode(int i) {
    this.audioManager.setMode(i);
  }

  public void close() {
    Log.d(TAG, "close");
    if (this.initialized) {
      unregisterForWiredHeadsetIntentBroadcast();
      setSpeakerphoneOn(this.savedIsSpeakerPhoneOn);
      setMicrophoneMute(this.savedIsMicrophoneMute);
      this.audioManager.setMode(this.savedAudioMode);
      this.audioManager.abandonAudioFocus(null);
      AppRTCProximitySensor appRTCProximitySensor = this.proximitySensor;
      if (appRTCProximitySensor != null) {
        appRTCProximitySensor.stop();
        this.proximitySensor = null;
      }
      this.initialized = false;
    }
  }

  public void setAudioDevice(AudioDevice audioDevice) {
    String str = TAG;
    StringBuilder sb = new StringBuilder();
    sb.append("setAudioDevice(device=");
    sb.append(audioDevice);
    sb.append(")");
    Log.d(str, sb.toString());
    AppRTCUtils.assertIsTrue(this.audioDevices.contains(audioDevice));
    switch (audioDevice) {
      case SPEAKER_PHONE:
        setSpeakerphoneOn(true);
        this.selectedAudioDevice = AudioDevice.SPEAKER_PHONE;
        break;
      case EARPIECE:
        setSpeakerphoneOn(false);
        this.selectedAudioDevice = AudioDevice.EARPIECE;
        break;
      case WIRED_HEADSET:
        setSpeakerphoneOn(false);
        this.selectedAudioDevice = AudioDevice.WIRED_HEADSET;
        break;
      default:
        Log.e(TAG, "Invalid audio device selection");
        break;
    }
    onAudioManagerChangedState();
  }

  public Set<AudioDevice> getAudioDevices() {
    return Collections.unmodifiableSet(new HashSet(this.audioDevices));
  }

  public AudioDevice getSelectedAudioDevice() {
    return this.selectedAudioDevice;
  }

  private void registerForWiredHeadsetIntentBroadcast() {
    IntentFilter intentFilter = new IntentFilter("android.intent.action.HEADSET_PLUG");
    this.wiredHeadsetReceiver = new BroadcastReceiver() {
      private static final int HAS_MIC = 1;
      private static final int HAS_NO_MIC = 0;
      private static final int STATE_PLUGGED = 1;
      private static final int STATE_UNPLUGGED = 0;

      public void onReceive(Context context, Intent intent) {
        int intExtra = intent.getIntExtra("state", 0);
        int intExtra2 = intent.getIntExtra("microphone", 0);
        String stringExtra = intent.getStringExtra("name");
        String str = AppRTCAudioManager.TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("BroadcastReceiver.onReceive");
        sb.append(AppRTCUtils.getThreadInfo());
        sb.append(": a=");
        sb.append(intent.getAction());
        sb.append(", s=");
        sb.append(intExtra == 0 ? "unplugged" : "plugged");
        sb.append(", m=");
        boolean z = true;
        sb.append(intExtra2 == 1 ? "mic" : "no mic");
        sb.append(", n=");
        sb.append(stringExtra);
        sb.append(", sb=");
        sb.append(isInitialStickyBroadcast());
        Log.d(str, sb.toString());
        if (intExtra != 1) {
          z = false;
        }
        switch (intExtra) {
          case 0:
            AppRTCAudioManager.this.updateAudioDeviceState(z);
            return;
          case 1:
            if (AppRTCAudioManager.this.selectedAudioDevice != AudioDevice.WIRED_HEADSET) {
              AppRTCAudioManager.this.updateAudioDeviceState(z);
              return;
            }
            return;
          default:
            Log.e(AppRTCAudioManager.TAG, "Invalid state");
            return;
        }
      }
    };
    this.apprtcContext.registerReceiver(this.wiredHeadsetReceiver, intentFilter);
  }

  private void unregisterForWiredHeadsetIntentBroadcast() {
    this.apprtcContext.unregisterReceiver(this.wiredHeadsetReceiver);
    this.wiredHeadsetReceiver = null;
  }

  public void setSpeakerphoneOn(boolean z) {
    if (this.audioManager.isSpeakerphoneOn() != z) {
      this.audioManager.setSpeakerphoneOn(z);
    }
  }

  private void setMicrophoneMute(boolean z) {
    if (this.audioManager.isMicrophoneMute() != z) {
      this.audioManager.setMicrophoneMute(z);
    }
  }

  private boolean hasEarpiece() {
    return this.apprtcContext.getPackageManager().hasSystemFeature("android.hardware.telephony");
  }

  @Deprecated
  private boolean hasWiredHeadset() {
    return this.audioManager.isWiredHeadsetOn();
  }

  /* access modifiers changed from: private */
  public void updateAudioDeviceState(boolean z) {
    this.audioDevices.clear();
    if (z) {
      this.audioDevices.add(AudioDevice.WIRED_HEADSET);
    } else {
      this.audioDevices.add(AudioDevice.SPEAKER_PHONE);
      if (hasEarpiece()) {
        this.audioDevices.add(AudioDevice.EARPIECE);
      }
    }
    String str = TAG;
    StringBuilder sb = new StringBuilder();
    sb.append("audioDevices: ");
    sb.append(this.audioDevices);
    Log.d(str, sb.toString());
    if (z) {
      setAudioDevice(AudioDevice.WIRED_HEADSET);
    } else {
      setAudioDevice(this.defaultAudioDevice);
    }
  }

  private void onAudioManagerChangedState() {
    String str = TAG;
    StringBuilder sb = new StringBuilder();
    sb.append("onAudioManagerChangedState: devices=");
    sb.append(this.audioDevices);
    sb.append(", selected=");
    sb.append(this.selectedAudioDevice);
    Log.d(str, sb.toString());
    boolean z = true;
    if (this.audioDevices.size() == 2) {
      if (!this.audioDevices.contains(AudioDevice.EARPIECE) || !this.audioDevices.contains(AudioDevice.SPEAKER_PHONE)) {
        z = false;
      }
      AppRTCUtils.assertIsTrue(z);
      this.proximitySensor.start();
    } else if (this.audioDevices.size() == 1) {
      this.proximitySensor.stop();
    } else {
      Log.e(TAG, "Invalid device list");
    }
    Runnable runnable = this.onStateChangeListener;
    if (runnable != null) {
      runnable.run();
    }
  }
}
