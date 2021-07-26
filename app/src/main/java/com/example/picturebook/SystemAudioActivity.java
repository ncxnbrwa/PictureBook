package com.example.picturebook;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

/**
 * 测试音频播放的页面,预期实现那种播放一段BGM,中间会根据不同操作有其它提示音播出,音源全部来自网络
 * 目前的情况:可以结合MediaPlayer和SoundPool叠加播放,但是SoundPool不支持网络播放
 */
public class SystemAudioActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "SystemAudioActivity";

    private Button btnMediaPlayerAudio, btnPauseOrResume, btnStop, btnSoundPoolAudio;
    private MediaPlayer mp;
    private SoundPool sp;
    //音频数组
//    private int[] soundArray = {R.raw.audio1, R.raw.audio2, R.raw.audio3};
    private int[] soundArray = {R.raw.music6, R.raw.audio2, R.raw.audio3};
    private String shiMianFeiXing = "http://m10.music.126.net/20200413153849/a646db0e46df91a7e46ac0feb3db17c1/ymusic/075e/0f52/045c/0431c434b788a0a8a90f3658c6c0fd5f.mp3";
    private String senorita = "http://m10.music.126.net/20200413155642/42d5163a112dc5a109638b9b48e7ee40/ymusic/5153/070f/0608/f9d7cf2bf80eaefde9cc1b3b88a4ff72.mp3";
    private String testRing = "https://jsyd.gzhaochuan.com:8090/h5v2/activity/zt20200408/images/change.m4a";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");
        setContentView(R.layout.activity_system_audio);
        btnMediaPlayerAudio = findViewById(R.id.btn_mediaplayer_audio);
        btnPauseOrResume = findViewById(R.id.btn_pause_resume_audio);
        btnStop = findViewById(R.id.btn_stop_audio);
        btnSoundPoolAudio = findViewById(R.id.btn_soundpool_audio);
        btnMediaPlayerAudio.setOnClickListener(this);
        btnPauseOrResume.setOnClickListener(this);
        btnStop.setOnClickListener(this);
        btnSoundPoolAudio.setOnClickListener(this);

        try {
            //加载网络地址则先实例化对象
//            mp = new MediaPlayer();
            //加载本地资源直接用create方法
            mp = MediaPlayer.create(this, R.raw.audio1);
            mp.setOnErrorListener((mp, what, extra) -> {
                Log.d(TAG, "onError,what:" + what + " extra:" + extra);
                return false;
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop");
    }

    @Override
    public void onClick(View v) {
        if (mp == null) return;
        switch (v.getId()) {
            case R.id.btn_mediaplayer_audio:
                try {
                    //设置网络资源地址
                    mp.setDataSource(this, Uri.parse(senorita));
                    //如果不是create初始化对象的话,必须先调用prepare方法再调用start方法
                    mp.prepare();
                    //如果是create方法初始化的对象,那可以直接调用start方法
                    mp.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.btn_pause_resume_audio:
                if (mp.isPlaying()) {
                    mp.pause();
                    btnPauseOrResume.setText("恢复");
                } else {
                    mp.start();
                    btnPauseOrResume.setText("暂停");
                }
                break;
            case R.id.btn_stop_audio:
                //停止音乐播放,调用该方法后调用start方法就会报错onError,what:-38 extra:0
//                mp.stop();
                //这样写不会报类似错误,取巧
                mp.seekTo(0);
                mp.pause();
                break;
            case R.id.btn_soundpool_audio:
                //利用SoundPool播放音频,可以和MediaPlayer叠加播放
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    AudioAttributes attributes = new AudioAttributes.Builder()
                            .setUsage(AudioAttributes.USAGE_MEDIA)  //音频使用场景
                            .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)  //音频使用类型
                            .build();
                    sp = new SoundPool.Builder()
                            .setMaxStreams(5) //设置最多容纳的音频流个数
                            .setAudioAttributes(attributes) //设置音频属性
                            .build();
                } else {
                    sp = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
                }
//                int soundId = sp.load(this, soundArray[0], 1);
                String folder = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "123.mp3";
//                String folder = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "audio1.wav";
                int soundId = sp.load(folder, 1);
//                int soundId2 = sp.load(this, soundArray[1], 1);
//                int soundId3 = sp.load(this, soundArray[2], 1);
                //需要监听加载完成后再播放资源,否则播放不了
                sp.setOnLoadCompleteListener((soundPool, sampleId, status) -> {
//                    sp.play(sampleId, 1, 1, 0, 0, 1);
                    delayPlay(soundId, 1000);
//                    delayPlay(soundId2, 2000);
//                    delayPlay(soundId3, 3000);
                });
                break;
        }
    }

    private void delayPlay(int sampleId, long time) {
        new Handler().postDelayed(() ->
                sp.play(sampleId, 1, 1, 0, 0, 1), time);
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, "onDestroy");
        if (mp != null) {
            mp.stop();
            mp.release();
        }
        if (sp != null) {
            sp.release();
        }
        super.onDestroy();
    }
}
