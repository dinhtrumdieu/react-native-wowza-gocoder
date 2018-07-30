package com.rnplayview;

import android.view.View;

import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.annotations.ReactProp;

import java.util.Map;

import javax.annotation.Nullable;

/**
 * Created by bapvn on 27/07/2018.
 */

public class RCTPlayerView extends ViewGroupManager<PlayerView> {

    private PlayerView mPlayerView;

    private static final String PROP_HOST_ADDRESS = "hostAddress";
    private static final String PROP_BROADCAST_NAME = "broadcastName";
    private static final String PROP_APPLICATION_NAME = "applicationName";
    private static final String PROP_SDK_LICENSE = "sdkLicenseKey";
    private static final String PROP_SIZE_PRESET = "sizePreset";
    private static final String PROP_USERNAME = "username";
    private static final String PROP_PASSWORD = "password";
    private static final String PROP_BROADCASTING = "broadcasting";
    private static final String PROP_FLASH = "flashOn";
    private static final String PROP_CAMERA = "frontCamera";
    private static final String PROP_MUTED = "muted";
    private static final String PROP_IS_PLAY = "isPlay";
    private static final String PROP_IN_BACKGROUND_MODE = "backgroundMode";

    @Override
    public String getName() {
        return "RNPlayerView";
    }

    @Override
    protected PlayerView createViewInstance(ThemedReactContext themedReactContext) {
        mPlayerView = new PlayerView(themedReactContext);
        return mPlayerView;
    }

    @Override
    @Nullable
    public Map getExportedCustomDirectEventTypeConstants() {
        MapBuilder.Builder builder = MapBuilder.builder();
//        for (BroadcastView.Events event : BroadcastView.Events.values()) {
//            builder.put(event.toString(), MapBuilder.of("registrationName", event.toString()));
//        }
        return builder.build();
    }



    @Override
    public void addView(PlayerView  parent, View child, int index) {
        parent.addView(child, index + 1);   // index 0 for camera preview reserved
    }



    @ReactProp(name = PROP_HOST_ADDRESS)
    public void setHostAddress(PlayerView view, String hostAddress){
        view.setHostAddress(hostAddress);
    }
    @ReactProp(name = PROP_SDK_LICENSE)
    public void setSdkLicenseKey(PlayerView view, String sdkLicenseKey){
        view.setSdkLicenseKey(sdkLicenseKey);
    }
    @ReactProp(name = PROP_APPLICATION_NAME)
    public void setApplicationName(PlayerView view, String applicationName){
        view.setApplicationName(applicationName);
    }
    @ReactProp(name = PROP_BROADCAST_NAME)
    public void setBroadcastName(PlayerView view, String broadcastName){
        view.setBroadcastName(broadcastName);
    }

    @ReactProp(name = PROP_USERNAME)
    public void setUsername(PlayerView view, String username){
        view.setUsername(username);
    }
    @ReactProp(name = PROP_PASSWORD)
    public void setPassword(PlayerView view, String password){
        view.setPassword(password);
    }

    @ReactProp(name = PROP_IN_BACKGROUND_MODE)
    public void setBackgroundMode(PlayerView view, boolean background){

    }

    @ReactProp(name= PROP_IS_PLAY)
    public void setIsPlay(PlayerView view, boolean isPlay) {
        view.togglePlayer(isPlay);
    }
}
