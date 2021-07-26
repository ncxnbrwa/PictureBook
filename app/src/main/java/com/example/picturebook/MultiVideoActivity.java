package com.example.picturebook;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import com.example.picturebook.view.CustomVideoView;

public class MultiVideoActivity extends AppCompatActivity {
    private static final String TAG = "MultiVideoActivity";

    private CustomVideoView video1, video2, video3, video4, video5, video6, video7, video8, video9, video10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_video);
        initView();
        configVideo(video1, false);
//        configVideo(video2, true);
//        configVideo(video3, true);
//        configVideo(video4, true);
//        configVideo(video5, true);
//        configVideo(video6, true);
//        configVideo(video7, true);
//        configVideo(video8, true);
//        configVideo(video9, true);
//        configVideo(video10, true);
    }

    private void initView() {
        video1 = findViewById(R.id.video1);
//        video2 = findViewById(R.id.video2);
//        video3 = findViewById(R.id.video3);
//        video4 = findViewById(R.id.video4);
//        video5 = findViewById(R.id.video5);
//        video6 = findViewById(R.id.video6);
//        video7 = findViewById(R.id.video7);
//        video8 = findViewById(R.id.video8);
//        video9 = findViewById(R.id.video9);
//        video10 = findViewById(R.id.video10);
    }

    private void configVideo(CustomVideoView videoView, boolean needMute) {
//        videoView.setVideoURI(Uri.parse(MyConfig.PATHS[(int) (MyConfig.PATHS.length * Math.random())]));
//        videoView.setVideoURI(Uri.parse(MyConfig.GDYD_DJ_VIDEO_URL[(int) (MyConfig.GDYD_DJ_VIDEO_URL.length * Math.random())]));
        videoView.setVideoURI(Uri.parse(MyConfig.GDYD_WJ_VIDEO_URL[(int) (MyConfig.GDYD_WJ_VIDEO_URL.length * Math.random())]));
//        MediaController controller = new MediaController(this);
//        videoView.setMediaController(controller);
//        controller.setMediaPlayer(videoView);
        videoView.setOnPreparedListener(mp -> {
            if (needMute) {
                Log.d(TAG, "OnPreparedListener 当前VideoView id:" + videoView.getId());
                mp.setVolume(0f, 0f);
            }
            mp.setOnInfoListener((mp1, what, extra) -> {
                Log.d(TAG, "OnInfoListener 当前VideoView id:" + videoView.getId());
                return true;
            });
        });
        if (!videoView.isPlaying()) {
            videoView.start();
        }
        videoView.setOnErrorListener((mp, what, extra) -> {
            Log.d(TAG, "OnErrorListener 当前VideoView id:" + videoView.getId());
//            videoView.suspend();
//            configVideo(videoView, needMute);
            return true;
        });
        videoView.setOnCompletionListener(mp -> {
//            videoView.suspend();
            configVideo(videoView, needMute);
        });
    }
}
