package com.example.picturebook;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.picturebook.view.exo.GSYExo2PlayerView;
import com.example.picturebook.view.exo.GSYExoVideoManager;
import com.shuyu.gsyvideoplayer.GSYVideoManager;
import com.shuyu.gsyvideoplayer.listener.GSYSampleCallBack;
import com.shuyu.gsyvideoplayer.model.GSYVideoModel;
import com.shuyu.gsyvideoplayer.model.VideoOptionModel;
import com.shuyu.gsyvideoplayer.player.PlayerFactory;
import com.shuyu.gsyvideoplayer.utils.GSYVideoType;
import com.shuyu.gsyvideoplayer.video.base.GSYVideoView;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import tv.danmaku.ijk.media.exo2.Exo2PlayerManager;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

public class MultiGSYVideoActivity extends AppCompatActivity {

    private static final String TAG = "MultiVideoActivity";
    private static final int CLICK_PLAY_ACTION = 0x123;

    private GSYExo2PlayerView video1, video2, video3, video4, video5, video6;
    private RelativeLayout rootView;
    private Timer timer;
    private List<GSYVideoModel> urls = new ArrayList<>();
    private List<GSYExo2PlayerView> videos = new ArrayList<>();
    private Handler mHandler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == CLICK_PLAY_ACTION) {
                if (rootView != null) {
                    if (!videos.isEmpty()) {
                        for (int i = 0; i < videos.size(); i++) {
                            GSYExo2PlayerView video = videos.get(i);
                            if (video != null) {
                                //如果视频不是播放状态,那就点一下播放按钮
                                if (video.getCurrentState() != GSYVideoView.CURRENT_STATE_PLAYING) {
                                    video.clickPlay();
                                }
                            }
                        }
                    }
                }
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_gsyvideo);
        initView();
        //根据链接数组随机生成200个播放数据
        for (int i = 0; i < 200; i++) {
            urls.add(new GSYVideoModel(MyConfig.GDYD_WJ_VIDEO_URL[(int) (MyConfig.GDYD_WJ_VIDEO_URL.length * Math.random())]
                    , "标题" + i));
//            urls.add(new GSYVideoModel(MyConfig.GDYD_DJ_VIDEO_URL[(int) (MyConfig.GDYD_DJ_VIDEO_URL.length * Math.random())]
//                    , "标题" + i));
//            urls.add(new GSYVideoModel(MyConfig.PATHS[(int) (MyConfig.PATHS.length * Math.random())]
//                    , "标题" + i));
        }

        playRandomVideo(video1, 1);
        playRandomVideo(video2, 2);
        playRandomVideo(video3, 3);
        playRandomVideo(video4, 4);
//        playRandomVideo(video5,5);
//        playRandomVideo(video6,6);

        //定时器用于点下播放,防止出现网络波动使视频暂停的情况
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Log.d(TAG, "计时器呢!!!!!!!!!");
                mHandler.sendEmptyMessage(CLICK_PLAY_ACTION);
            }
        }, 5000, 5000);
    }

    private void initView() {
        rootView = findViewById(R.id.root_view);
        video1 = findViewById(R.id.gsy_video1);
        video2 = findViewById(R.id.gsy_video2);
        video3 = findViewById(R.id.gsy_video3);
        video4 = findViewById(R.id.gsy_video4);
//        video5 = findViewById(R.id.gsy_video5);
//        video6 = findViewById(R.id.gsy_video6);
        videos.add(video1);
        videos.add(video2);
        videos.add(video3);
        videos.add(video4);
//        videos.add(video5);
//        videos.add(video6);
    }

    private void playRandomVideo(GSYExo2PlayerView gsyVideoPlayer, int position) {
        gsyVideoPlayer.setPlayTag(TAG);
        gsyVideoPlayer.setPlayPosition(position);
        gsyVideoPlayer.setUp(urls, position);
        gsyVideoPlayer.startPlayLogic();
        //视频title设置隐藏
        gsyVideoPlayer.getTitleTextView().setVisibility(View.GONE);
        //设置返回键
        gsyVideoPlayer.getBackButton().setVisibility(View.GONE);
    }

    @Override
    protected void onPause() {
        super.onPause();
        GSYExoVideoManager.onPauseAll();
    }

    @Override
    protected void onResume() {
        super.onResume();
        GSYExoVideoManager.onResumeAll();
    }

    @Override
    protected void onDestroy() {
        if (timer != null) {
            timer.cancel();
        }
        GSYExoVideoManager.clearAllVideo();
        super.onDestroy();
    }
}
