package com.rnplayview;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ViewGroup;

import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.uimanager.ThemedReactContext;
import com.wowza.gocoder.sdk.api.WowzaGoCoder;
import com.wowza.gocoder.sdk.api.errors.WZStreamingError;
import com.wowza.gocoder.sdk.api.player.WZPlayerConfig;
import com.wowza.gocoder.sdk.api.player.WZPlayerView;
import com.wowza.gocoder.sdk.api.status.WZState;
import com.wowza.gocoder.sdk.api.status.WZStatus;
import com.wowza.gocoder.sdk.api.status.WZStatusCallback;

/**
 * Created by bapvn on 27/07/2018.
 */

public class PlayerView extends WZPlayerView implements LifecycleEventListener {

    private WZPlayerView mWZPlayerView;
    private WZPlayerConfig mStreamPlayerConfig = null;
    private ThemedReactContext mContext;
    private WowzaGoCoder goCoder;
    private String sdkLicenseKey;
    private String hostAddress;
    private String applicationName;
    private String broadcastName;
    private String username;
    private String password;

    public PlayerView(ThemedReactContext context) {
        super(context);
        mWZPlayerView = new WZPlayerView(context);
        mWZPlayerView.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        // The streaming player configuration properties
        mStreamPlayerConfig = new WZPlayerConfig();
        mContext = context;
        mContext.addLifecycleEventListener(this);
        this.addView(mWZPlayerView);
    }

    public PlayerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void onHostResume() {
        Log.d("xxx", "onHostResume: "+ getHostAddress());
        }

    @Override
    public void onHostPause() {
        if (mWZPlayerView != null && mWZPlayerView.isPlaying()) {
            mWZPlayerView.stop();
            // Wait for the streaming player to disconnect and shutdown...
            mWZPlayerView.getCurrentStatus().waitForState(WZState.IDLE);
        }
    }

    @Override
    public void onHostDestroy() {

    }

    public void togglePlayer(boolean isPlay) {
        Log.d("xxx", "togglePlayer: "+isPlay);
        if (mWZPlayerView.isPlaying()) {
            mWZPlayerView.stop();
        } else if(mWZPlayerView.isReadyToPlay()) {

            WZStreamingError configValidationError = mStreamPlayerConfig.validateForPlayback();
//            if (configValidationError != null) {
//                Log.d("xxx", "togglePlayer: error");
////                mStatusView.setErrorMessage(configValidationError.getErrorDescription());
//            } else {
                Log.d("xxx", "togglePlayer: done1"+ getHostAddress());
                Log.d("xxx", "togglePlayer: done2"+ getApplicationName());
                mStreamPlayerConfig.setPreRollBufferDuration(0);
                mStreamPlayerConfig.setHostAddress(getHostAddress());
                mStreamPlayerConfig.setApplicationName(getApplicationName());
                mStreamPlayerConfig.setStreamName(getBroadcastName());
                mStreamPlayerConfig.setPortNumber(1935);
                mStreamPlayerConfig.setVideoBitRate(800);
                mStreamPlayerConfig.setVideoFrameSize(488,640);
                mStreamPlayerConfig.setAudioEnabled(true);

            Log.d("xxx", "togglePlayer: "+ mStreamPlayerConfig.getHostAddress());

                // Start playback of the live stream
                mWZPlayerView.play(mStreamPlayerConfig, new WZStatusCallback() {
                    @Override
                    public void onWZStatus(WZStatus wzStatus) {
                        Log.d("xxx", "onWZStatus: "+ wzStatus);
                    }

                    @Override
                    public void onWZError(WZStatus wzStatus) {
                        Log.d("xxx", "onWZError: "+ wzStatus);
                    }
                });
            }
//        }
    }

    public String getSdkLicenseKey() {
        return sdkLicenseKey;
    }

    public void setSdkLicenseKey(String sdkLicenseKey) {
        this.sdkLicenseKey = sdkLicenseKey;
    }

    public String getHostAddress() {
        return hostAddress;
    }

    public void setHostAddress(String hostAddress) {
        Log.d("xxx", "setHostAddress: "+hostAddress);
        this.hostAddress = hostAddress;
    }

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public String getBroadcastName() {
        return broadcastName;
    }

    public void setBroadcastName(String broadcastName) {
        this.broadcastName = broadcastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
