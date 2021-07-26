package com.example.picturebook.service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.picturebook.MyConfig;

public class BackgroundVideoService2 extends Service {
    private MediaPlayer mp;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        try {
//            mp = MediaPlayer.create(this, Uri.parse(MyConfig.PATHS[(int) (7 * Math.random())]));
            mp = MediaPlayer.create(this, Uri.parse(MyConfig.PATHS[0]));
            mp.start();
            Log.d(MyConfig.SERVICE_VIDEO_TAG,"BackgroundVideoService2");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        if (mp != null) {
            mp.stop();
            mp.release();
        }
        super.onDestroy();
    }
}
